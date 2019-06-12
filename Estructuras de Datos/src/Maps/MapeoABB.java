package Maps;



public class MapeoABB<K,V> implements Map<K, V> {
	NodoABB<K,V> raiz;
	int size;
	Comparator<K> comp;
	
	public MapeoABB(Comparator<K> c){
		comp = c;
		size = 0;
		raiz = new NodoABB<K,V>();
	}
	
	/**
	 * Consulta el número de entradas del mapeo.
	 * @return Número de entradas del mapeo.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Consulta si el mapeo está vacío.
	 * @return Verdadero si el mapeo está vacío, falso en caso contrario.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * Busca una entrada con clave igual a una clave dada y devuelve el valor asociado, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Valor de la entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V get(K key)throws InvalidKeyException{
		checkKey(key);
		V ret = null;
		NodoABB<K,V> n = hallar(raiz,key);
		if(n.getKey() != null)
			ret = n.getValue();
		return ret;
	}
	
	/**
	 * Si el mapeo no tiene una entrada con clave key, inserta una entrada con clave key y valor value en el mapeo y devuelve null. 
	 * Si el mapeo ya tiene una entrada con clave key, entonces remplaza su valor y retorna el viejo valor.
	 * @param key Clave de la entrada a crear.
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V put(K key, V value) throws InvalidKeyException{
		checkKey(key);
		V ret = null;
		if(size == 0){
			raiz = new NodoABB<K,V>(key,value,null,null,null);
			raiz.setHijoI(new NodoABB<K,V>(raiz));
			raiz.setHijoD(new NodoABB<K,V>(raiz));
			ret = value;
			size++;
		}
		insertar(key,value,raiz);
		return ret;
	}
	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V remove(K key) throws InvalidKeyException{
		checkKey(key);
		V ret;
		NodoABB<K,V> N = hallar(raiz, key);
		if(N.getKey() == null) //si el metodo auxiliar devolvió una hoja, significa que NO encontré lo que buscaba
			ret = null;
		else{ //si encontré lo que buscaba, debo reemplazar la raiz del subarbol por su Hijo Extremo izquierdo
			ret = N.getValue();
			NodoABB<K,V> HI = buscarReemplazo(N);
			N.setKey(HI.getKey());
			N.setValue(HI.getValue());
			int c = comp.compare(HI.getKey(), HI.getPadre().getKey());
			if(c < 0){ //el reemplazo que encontré era hijo Izquierdo de su padre
				HI.getPadre().setHijoI(null);
				HI.setPadre(null);
				HI = new NodoABB<K,V>();
			}
			else{ //sino, era Hijo Derecho
				HI.getPadre().setHijoD(null);
				HI.setPadre(null);
				HI = new NodoABB<K,V>();
			}
		}
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys(){
		PositionList<K> ret = new ListaDoblementeEnlazada<K>();
		PositionList<V> dum = null;
		PositionList<Entry<K,V>> dum2 = null; 
		pasar(1,ret,dum,dum2,raiz);
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas los valores del mapeo.
	 * @return Colección iterable con todas los valores del mapeo.
	 */
	public Iterable<V> values(){
		PositionList<V> ret = new ListaDoblementeEnlazada<V>();
		PositionList<K> dum = null;
		PositionList<Entry<K,V>> dum2 = null; 
		pasar(2,dum,ret,dum2,raiz);
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas las entradas del mapeo.
	 * @return Colección iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> ret = new ListaDoblementeEnlazada<Entry<K,V>>();
		PositionList<V> dum = null;
		PositionList<K> dum2 = null; 
		pasar(3,dum2,dum,ret,raiz);
		return ret;
	}
	/**
	 * metodo auxiliar que chequea que una clave no sea invalid
	 * @param key clave a examinar
	 * @throws InvalidKeyException si al clave era invalida
	 */
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Clave vacia");

	}
	private NodoABB<K,V> hallar(NodoABB<K,V> PA, K key){
		NodoABB<K,V> ret;
		if(PA.getKey() == null)
			ret = null;
		else{
			if(key == PA.getKey())
				ret = PA;
			else{
				int c = comp.compare(key,PA.getKey());
				if(c < 0)
					ret = hallar(PA.getIz(),key);
				else
					ret = hallar(PA.getDer(),key);
			}
		}
		return ret;
	}
	private void insertar(K key,V value, NodoABB<K,V> PA){
		if(PA.getKey() == null){
			PA.setKey(key);
			PA.setValue(value);
			PA.setHijoI(new NodoABB<K,V>(PA));
			PA.setHijoD(new NodoABB<K,V>(PA));
			size++;
		}
		else{
			int c = comp.compare(key, PA.getKey());
			if(c < 0)
				insertar(key,value,PA.getIz());
			else
				if(c > 0)
					insertar(key,value,PA.getDer());
				else{
					PA.setValue(value);
				}
		}
	}
	/**
	 * metodo auxiliar que busca un nodo reemplazante para el metodo Remove
	 * @param PA posicion actual de busqueda
	 * @return ultimo nodo interno lleno del subarbol
	 */
	private NodoABB<K,V> buscarReemplazo(NodoABB<K,V> PA){
		if(PA.getIz().getKey() == null)
			return PA;
		else{
			if(PA.getDer().getKey() != null)
				PA = buscarReemplazo(PA.getDer());
			else
				PA = buscarReemplazo(PA.getIz());
		}
		return PA;
	}
	/**
	 * multi-procedimiento para los metodos que devuelven iterables
	 * @param D numero determinante de la lista que quiero llenar
	 * @param keys lista de claves
	 * @param values lista de valores
	 * @param entries lista de Entradas
	 * @param PA posicion actual de busqueda en el árbol
	 */
	private void pasar(int D, PositionList<K> keys,PositionList<V> values, PositionList<Entry<K,V>> entries,NodoABB<K,V> PA){
		if(D == 1){
			keys.addLast(PA.getKey());
			if(PA.getIz().getKey() != null)
				pasar(1,keys,values,entries,PA.getIz());
			if(PA.getDer().getKey() != null)
				pasar(1,keys,values,entries,PA.getDer());
		}
		if(D == 2){
			values.addLast(PA.getValue());
			if(PA.getIz().getKey() != null)
				pasar(2,keys,values,entries,PA.getIz());
			if(PA.getDer().getKey() != null)
				pasar(2,keys,values,entries,PA.getDer());
		}
		if(D == 3){
			entries.addLast(new Entrada<K,V>(PA.getKey(),PA.getValue()));
			if(PA.getIz().getKey() != null)
				pasar(3,keys,values,entries,PA.getIz());
			if(PA.getDer().getKey() != null)
				pasar(3,keys,values,entries,PA.getDer());
		}
	}
}
