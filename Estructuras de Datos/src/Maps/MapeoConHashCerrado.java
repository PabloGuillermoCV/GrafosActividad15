package Maps;

public class MapeoConHashCerrado<K,V> implements Map<K, V> {

	protected int size;
	protected Entry<K,V>[] TablaC;
	protected int maxElems = 1023; //DEBE ser un Nro PRIMO (Menor chance de generar Colisiones), cantidad máxmia de elementos en el arreglo
	private Entry<K,V> DISPONIBLE = new Entrada<K,V>(null,null); 
	
	public MapeoConHashCerrado(){
		TablaC = (Entry<K,V>[]) new Entry[maxElems];
		size = 0;
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
		V ret;
		int i = findEntry(key);
		if(i < 0) //no se encontró un valor para esta clave
			ret = null;
		else
			ret = TablaC[i].getValue();
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
		V ret = null;
		int i = findEntry(key);
		if(i >= 0){ //se encontró que la clave ya existia
			ret = TablaC[i].getValue();
			TablaC[i].setValue(value);
		}
		if(size >= maxElems/2){
			reHash();
			i = findEntry(key);
		}
		TablaC[Math.abs(i)-1] = new Entrada<K,V>(key,value);
		size++;
		return ret;
			
	}
	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V remove(K key) throws InvalidKeyException{
		V ret;
		int i = findEntry(key); //intentamos encontrar la clave en el arreglo
		if(i < 0) //si no la clave estaba 
			ret = null;
		else{ //se encontró la clave
			ret = TablaC[i].getValue();
			TablaC[i] = DISPONIBLE; //marco el slot como Disponible
			size--;
		}
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys(){
		PositionList<K> ret = new ListaDoblementeEnlazada<K>();
		for(int i = 0; i < maxElems; i++){
			if(TablaC[i] != null && TablaC[i] != DISPONIBLE)
				ret.addLast(TablaC[i].getKey());
		}
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas los valores del mapeo.
	 * @return Colección iterable con todas los valores del mapeo.
	 */
	public Iterable<V> values(){
		PositionList<V> ret = new ListaDoblementeEnlazada<V>();
		for(int i = 0; i < maxElems; i++){
			if(TablaC[i] != null && TablaC[i] != DISPONIBLE)
				ret.addLast(TablaC[i].getValue());
		}
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas las entradas del mapeo.
	 * @return Colección iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> ret = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i = 0; i < maxElems; i++){
			if(TablaC[i] != null && TablaC[i] != DISPONIBLE)
				ret.addLast(TablaC[i]);
		}
		return ret;
	}
	/**
	 * metodo auxiliar que busca una posición en el arreglo
	 * @param key llave a buscar
	 * @return posición de la llave
	 * @throws InvalidKeyException si la llave era inválida
	 */
	private int findEntry(K key) throws InvalidKeyException{ //quiero mejorar esto... el break me jode...
		int avail = -1;
		int i = hash(key);
		checkKey(key);
		int j = i;
		do{
			Entry<K,V> e = TablaC[i];
			if(e == null){
				if(avail < 0)
					avail = i; //Key is not in the Table
				break;
			}
			if(key.equals(e.getKey()))
				return i; //key found
			if(e == DISPONIBLE){ // en el slot SOLIA HABER un elemento, pero fue removido
				if(avail < 0)
					avail = i;
			}
			i = (i + 1) % maxElems;
		}while(i != j);
		return -(avail + 1); //first empty or available slot
	}
	/**
	 * Metodo auxiliar que agranda el arreglo de buckets si el factor de carga es excedido
	 * el método delega el rehashing de las claves que encuentra en el método put(K,V), ya que antes de esto ya creó el nuevo arreglo de Entradas
	 */
	private void reHash(){
		PositionList<Entry<K,V>> e = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(Entry<K,V> E: entries()){
			e.addLast(E);
		}
		int maxN = maxElems*2;
		TablaC = (Entry<K,V>[]) new Entry[maxN];
		try{
			for(Entry<K,V> E: e){
				put(E.getKey(),E.getValue());
			}
		}
		catch(InvalidKeyException t){}
	}
	/**
	 * Metodo auxiliar que calcula el HashCode de la clave que entra
	 * @param key la clave a transformar
	 * @return el HashCode obtenido por pasaje a entero y Metodo de Division
	 */
	private int hash(K key){
		return key.hashCode() % maxElems;
	}
	/**
	 * metodo auxiliar que chequea que la llave sea válida
	 * @param key la llave a verificar
	 * @throws InvalidKeyException si la llave era nula
	 */
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Llave vacia");
	}
}
