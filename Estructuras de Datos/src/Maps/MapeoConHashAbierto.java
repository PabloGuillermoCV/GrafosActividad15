package Maps;

import java.util.Iterator;
import java.util.Vector;

public class MapeoConHashAbierto<K,V> implements Map<K, V> {
	
	protected Vector<PositionList<Entry<K,V>>> Tabla;
	int size; //cantidad de elementos guardados
	int maxElems; //DEBE ser un Nro PRIMO (Menor chance de generar Colisiones), cantidad de lugares en el arreglo
	
	public MapeoConHashAbierto(int max){
		maxElems = max;
		Tabla = new Vector<PositionList<Entry<K,V>>>(maxElems);
		size = 0; //hay 0 componenetes en la Tabla
		for(int i = 0; i < maxElems; i++){
			Tabla.add(new ListaDoblementeEnlazada<Entry<K,V>>());
		}
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
		Iterator<Entry<K,V>> it = Tabla.elementAt(hash(key)).iterator();
		boolean found = false;
		while(it.hasNext() && !found){
			Entry<K,V> aux = it.next();
			if(aux.getKey().equals(key)){
				found = true;
				ret = aux.getValue();
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
		if(size/maxElems >= 0.5) //el factor de carga excedió el máximo ideal para que las operaciones tengan T(n) = O(1)
			reHash();
		V ret = null;
		Entry<K,V> ins = (Entry<K, V>) new Entrada<K,V>(key,value);
		Iterator<Entry<K,V>> L = Tabla.elementAt(hash(key)).iterator();
		boolean found = false;
		while(L.hasNext() && !found){
			Entry<K,V> aux = L.next();
			if(aux.getKey().equals(key)){
				ret = aux.getValue();
				aux.setValue(value);
				found = true;
			}
		}
		if(!found){
			size++;
			Tabla.elementAt(hash(key)).addLast(ins);
		}
		
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
		V ret = null;
		boolean found = false;
		PositionList<Position<Entry<K,V>>> P = Tabla.elementAt(hash(key)).positions(); 
		try{
			Position<Position<Entry<K,V>>> it = P.first();
			while(it != null && !found){
				if(it.element().element().getKey().equals(key)){
					ret = it.element().element().getValue();
					found = true;
					Tabla.elementAt(hash(key)).remove(it.element());
					size--;
				}
				if(it != P.last())
					it = P.next(it);
				else
					it = null;
			}
		}
		catch(InvalidPositionException t){}
		catch(BoundaryViolationException t){}
		catch(EmptyListException t){}
		return ret;
	}
	
	
	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys(){
		PositionList<K> ret = new ListaDoblementeEnlazada<K>();
		for(int i = 0; i < maxElems; i++){
			if(!Tabla.elementAt(i).isEmpty()){
				for(Entry<K,V> P : Tabla.elementAt(i)){
					ret.addLast(P.getKey());
				}
			}
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
			if(!Tabla.elementAt(i).isEmpty()){
				for(Entry<K,V> P : Tabla.elementAt(i)){
					ret.addLast(P.getValue());
				}
			}
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
			if(!Tabla.elementAt(i).isEmpty()){
				for(Entry<K,V> P : Tabla.elementAt(i)){
					ret.addLast(P);
				}
			}
		}
		return ret;
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
	/**
	 * Metodo auxiliar que calcula el HashCode de la clave que entra
	 * @param key la clave a transformar
	 * @return el HashCode obtenido por pasaje a entero y Metodo de Division
	 */
	private int hash(K key){
		return key.hashCode() % maxElems;
	}
	
	/**
	 * Metodo auxiliar que agranda el arreglo de buckets si el factor de carga es excedido
	 * el método delega el rehashing de las claves que encuentra en el método put(K,V), ya que antes de esto ya creó el nuevo arreglo de Mapeos
	 */
	private void reHash(){
		int maxN = maxElems*2;
		Vector<PositionList<Entry<K,V>>> nuevo = new Vector<PositionList<Entry<K,V>>>(maxN);
		for(int i = 0; i < maxN; i++){
			nuevo.add(new ListaDoblementeEnlazada<Entry<K,V>>());
		}
		for(Entry<K,V> P : this.entries()){
			nuevo.elementAt(P.getKey().hashCode() % maxN).addLast(P);
		}
		Tabla = nuevo;
	}
			
}
