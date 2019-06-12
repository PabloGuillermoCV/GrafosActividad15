package Dictionaries;

import java.util.Iterator;
import java.util.Vector;

import Maps.InvalidKeyException;

public class DiccionarioConHashAbierto<K,V> implements Dictionary<K, V> {

	protected int size;
	protected int maxElems;
	protected Vector<PositionList<Entry<K,V>>> Tabla;
	
	public DiccionarioConHashAbierto(int max){
		size = 0;
		maxElems = max;
		Tabla = new Vector<PositionList<Entry<K,V>>>(maxElems);
		for(int i = 0; i < maxElems; i++){
			Tabla.add(new ListaDoblementeEnlazada<Entry<K,V>>());
		}
		
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
	public Entry<K,V> find(K key) throws Dictionaries.InvalidKeyException{
		checkKey(key);
		Entry<K,V> ret = new Entrada<K,V>(null,null);
		PositionList<Entry<K,V>> es = Tabla.elementAt(hashValue(key));
		Iterator<Entry<K,V>> it = es.iterator();
		boolean found = false;
		while(it.hasNext() && !found){
			Entry<K,V> aux = it.next();
			if(aux.getKey().equals(key)){
				ret = aux;
				found = true;
			}
		}
		
		return ret;
		
	}
	
	/**
	 * Retorna una colección iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colección iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Iterable<Entry<K,V>> findAll(K key) throws Dictionaries.InvalidKeyException{
		checkKey(key);
		PositionList<Entry<K,V>> ret = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(Entry<K,V> P : Tabla.elementAt(hashValue(key))){
			if(P.getKey().equals(key))
				ret.addLast(P);
		}
		return ret;
	}
	
	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Entry<K,V> insert(K key, V value) throws Dictionaries.InvalidKeyException{
		checkKey(key);
		Entry<K,V> ret = new Entrada<K,V>(key,value);
		if(size/maxElems > 0.5)
			reHash();
		Tabla.elementAt(hashValue(key)).addLast(ret);
		size++;
		return ret;
	}
	
	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la entrada es inválida.
	 */
	public Entry<K,V> remove(Entry<K,V> e) throws Dictionaries.InvalidEntryException{
		if(e == null)
			throw new InvalidEntryException("Entrada Invalida");
		Entry<K,V> aux = new Entrada<K,V>(null,null);
		boolean found = false;
		PositionList<Position<Entry<K,V>>> P = (PositionList<Position<Entry<K, V>>>) Tabla.elementAt(hashValue(e.getKey())).positions(); //Porqué el cast? que tengo mal???
		try{
			Position<Position<Entry<K,V>>> it = P.first();
			while(it != null && !found){
				if(it.element().element().equals(e)){
					aux = it.element().element();
					found = true;
					Tabla.elementAt(hashValue(e.getKey())).remove(it.element());
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
		return aux;
	}
	
	/**
	 * Retorna una colección iterable con todas las entradas en el diccionario.
	 * @return Colección iterable de todas las entradas.
	 */
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> ret = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i = 0; i < maxElems; i++){
			if(!Tabla.elementAt(i).isEmpty()){
				for(Entry<K,V> T:Tabla.elementAt(i)){
					ret.addLast(T);
				}
			}
		}
		return ret;
	}
	
	private int hashValue(K key){
		return (int) key.hashCode();
	}
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
	private void checkKey(K key) throws Dictionaries.InvalidKeyException{
		if(key == null)
			throw new Dictionaries.InvalidKeyException("Llave vacia");
	}
}
