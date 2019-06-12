package Graphs;

import java.util.Iterator;

public class MapeoConLista<K,V> implements Map<K, V> {
	
	protected int size;
	protected PositionList<Entry<K,V>> entradas; //porqué siempre uso entry (la interfaz) y NUNCA uso "Entrada" (la clase que implementa "Entry") ???
	
	
	public MapeoConLista(){
		entradas = new ListaDoblementeEnlazada<Entry<K,V>>();
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
		checkKey(key);
		V ret = null;
		Iterator<Entry<K,V>> it = entradas.iterator();
		boolean found = false;
		while(it.hasNext() && !found){
			Entry<K,V> aux = it.next();
			if(aux.getKey().equals(key)){
				ret = aux.getValue();
				found = true;
			}
		}
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
		boolean found = false;
		Iterator<Entry<K,V>> it = entradas.iterator();
		while(it.hasNext() && !found){
			Entry<K,V> aux = it.next();
			if(aux.getKey().equals(key)){
				ret = aux.getValue();
				aux.setValue(value);
				found = true;
			}
		}
		if(found == false){
			Entry<K,V> Add = new Entrada<K,V>(key,value);
			entradas.addLast(Add);
		}
		return ret;
	}

	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param key Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V remove(K key) throws InvalidKeyException{
		checkKey(key);
		V ret = null;
		boolean found = false;
		try{
		Position<Entry<K,V>> it = entradas.first();
		while(it != null && !found){
			if(it.element().getKey().equals(key)){
				Position<Entry<K,V>> aux = it;
				found = true;
				ret = aux.element().getValue();
				entradas.remove(aux);
			}
			if(it != entradas.last()){
				it = entradas.next(it);
			}
			else{
				it = null;
			}
		}
		}
		catch(EmptyListException e){}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys(){
		PositionList<K> ret = new ListaDoblementeEnlazada<K>();
		Iterator<Entry<K,V>> it = entradas.iterator();
		while(it.hasNext()){
			ret.addLast(it.next().getKey());
		}
		return ret;
		
	}
	
	/**
	 * Retorna una colección iterable con todas los valores del mapeo.
	 * @return Colección iterable con todas los valores del mapeo.
	 */
	public Iterable<V> values(){
		PositionList<V> ret = new ListaDoblementeEnlazada<V>();
		Iterator<Entry<K,V>> it = entradas.iterator();
		while(it.hasNext()){
			ret.addLast(it.next().getValue());
		}
		return ret;
	}
	
	/**
	 * Retorna una colección iterable con todas las entradas del mapeo.
	 * @return Colección iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> ret = new ListaDoblementeEnlazada<Entry<K,V>>();
		Iterator<Entry<K,V>> it = entradas.iterator();
		while(it.hasNext()){
			ret.addLast(it.next());
		}
		return ret;
	}
	
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Llave  Vacia");
	}
}
