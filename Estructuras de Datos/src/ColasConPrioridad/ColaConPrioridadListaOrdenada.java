package ColasConPrioridad;
import Listas.*;
public class ColaConPrioridadListaOrdenada<K,V> implements PriorityQueue<K, V> {

	protected PositionList<Entry<K,V>> entries;
	protected Comparator<K> comp;
	protected Position<Entry<K,V>> actionPos;
	
	public ColaConPrioridadListaOrdenada(Comparator<K> c){
		entries = new ListaDoblementeEnlazada<Entry<K,V>>();
		comp = c;
	}
	/**
	 * Consulta la cantidad de elementos de la cola.
	 * @return Cantidad de elementos de la cola.
	 */	
	public int size(){
		return entries.size();
	}

	/**
	 * Consulta si la cola está vacía.
	 * @return Verdadero si la cola está vacía, falso en caso contrario.
	 */
	public boolean isEmpty(){
		return entries.isEmpty();
	}

	/**
	 * Devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola está vacía.
	 */
	public Entry<K,V> min()throws EmptyPriorityQueueException{
		Entry<K,V> ret = null;
		if(isEmpty())
			throw new EmptyPriorityQueueException("Cola Vacia");
		else{
			try{
				ret = entries.first().element();
			}
			catch(EmptyListException e){}
		}
		return ret;
	}

	/**
	 * Inserta un par clave-valor y devuelve la entrada creada.
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * @return Entrada creada.
	 * @throws InvalidKeyException si la clave es inválida.
	 */
	public Entry<K,V> insert(K key,V value)throws InvalidKeyException{
		checkKey(key);
		Entry<K,V> ret = new Entrada<K,V>(key,value);
		insertaux(key,ret);
		return ret;		
	}

	/**
	 * Remueve y devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola está vacía.
	 */
	public Entry<K,V> removeMin()throws EmptyPriorityQueueException{
		Entry<K,V> ret = null;
		if(entries.isEmpty()){
			throw new EmptyPriorityQueueException("Cola vacia");
		}
		else{
			try{
				ret = entries.remove(entries.first());
			}
			catch(InvalidPositionException e){}
			catch(EmptyListException e){}
		}
		return ret;
			
	}
	
	protected void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Clave invalida");
	}
	
	protected void insertaux(K key, Entry<K,V> in){
		try{
			int c = comp.compare(key,entries.first().element().getKey());
			if(c < 0){
				entries.addFirst(in);
				actionPos = entries.first();
			}
			else{
				c = comp.compare(key, entries.last().element().getKey());
				if(c > 0){
					entries.addLast(in);
					actionPos = entries.last();
				}
				else{
					Position<Entry<K,V>> curr = entries.first();
					while(comp.compare(key,curr.element().getKey()) > 0){
						curr = entries.next(curr);
					}
					entries.addBefore(curr,in);
					actionPos = entries.prev(curr);
				}
			}
		}
		catch(EmptyListException e){}
		catch(BoundaryViolationException e){}
		catch(InvalidPositionException e){}
	}

}
