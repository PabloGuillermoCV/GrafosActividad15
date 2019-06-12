package Dictionaries;

public class DiccionarioABB<K,V> implements Dictionary<K, V> {
	
	private NodoABB<K,V> raiz;
	private int size;
	private Comparator<K> comp;
	
	public DiccionarioABB(Comparator<K> com){
		size = 0;
		raiz = new NodoABB<K,V>();
		comp = com;
	}
	/**
	 * Consulta el número de entradas del diccionario.
	 * @return Número de entradas del diccionario.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Consulta si el diccionario está vacío.
	 * @return Verdadero si el diccionario está vacío, falso en caso contrario.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Entry<K,V> find(K key) throws InvalidKeyException{
		checkKey(key);
		Entry<K,V> ret = null;
		NodoABB<K,V> b = hallar(raiz, key);
		if(b != null)
			ret = new Entrada<K,V>(b.getKey(),b.getValue());
		return ret;
	}
	
	/**
	 * Retorna una colección iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colección iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		PositionList<Entry<K,V>> ret = new ListaDoblementeEnlazada<Entry<K,V>>();
		NodoABB<K,V> n = hallar(raiz,key);
		if(n != null){
			ret.addLast(new Entrada<K,V>(n.getKey(),n.getValue()));
			if(!n.getValores().isEmpty()){
				for(V v: n.getValores())
					ret.addLast(new Entrada<K,V>(key,v));
			}
		}
		return ret;
	}
	
	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		checkKey(key);
		Entry<K,V> n = new Entrada<K,V>(key,value);
		if(size == 0){
			NodoABB<K,V> h1 = new NodoABB<K,V>(raiz);
			NodoABB<K,V> h2 = new NodoABB<K,V>(raiz);
			raiz = new NodoABB<K,V>(key,value,null,null,null);
			raiz.setHijoI(h1);
			raiz.setHijoD(h2);
		}
		insertar(key,value,raiz);
		size++;
		return n;
	}
	
	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la clave es inválida.
	 */
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		Entry<K,V> ret = null;
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas las entradas en el diccionario.
	 * @return Colección iterable de todas las entradas.
	 */
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> ret = new ListaDoblementeEnlazada<Entry<K,V>>();
		pasarInorder(raiz,ret);
		return ret;
	}
	public String toString() {
		return inorder( raiz );
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
			
		}
		else{
			int c = comp.compare(key, PA.getKey());
			if(c < 0)
				insertar(key,value,PA.getIz());
			else
				if(c > 0)
					insertar(key,value,PA.getDer());
				else{
					PA.getValores().addLast(value);
				}
		}
	}
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Clave vacia");
		
	}
	private void pasarInorder(NodoABB<K,V>PA,PositionList<Entry<K,V>> l){
		if(PA != null){
			pasarInorder(PA.getIz(),l);
			Entry<K,V> in = new Entrada<K,V>(PA.getKey(),PA.getValue());
			l.addLast(in);
			pasarInorder(PA.getDer(),l);
		}
	}
	
	private String inorder( NodoABB<K,V> nodov ) {
		if( nodov.getKey() != null ) {
		return "(" + inorder( nodov.getIz()) + " " + nodov.getKey() + " " +  inorder( nodov.getDer() ) + ")";
		} else return "";
		}
	
}
