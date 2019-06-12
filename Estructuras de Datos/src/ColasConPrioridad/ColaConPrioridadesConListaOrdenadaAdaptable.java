package ColasConPrioridad;
import Listas.*;
public class ColaConPrioridadesConListaOrdenadaAdaptable<K,V> extends ColaConPrioridadListaOrdenada<K,V> implements AdaptablePriorityQueue<K, V> {

	public ColaConPrioridadesConListaOrdenadaAdaptable(Comparator<K> c){
		super(c);
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
		EntradaPosicionada<K,V> entry = new EntradaPosicionada<K,V>(key,value);
		insertaux(key,entry);
		entry.setPos(actionPos);
		return entry;
	}
	
	/**
	 * Elimina una entrada de la Cola con Prioridades
	 * @param e entrada a eliminar
	 * @return la entrada eliminada
	 */
	public Entry<K,V> remove(Entry<K,V> e)  throws InvalidEntryException {
		EntradaPosicionada<K,V> entry = checkEntry(e);
		Position<Entry<K,V>> p = entry.getPosicion();
		try{
			entries.remove(p);
		}
		catch(InvalidPositionException t){return null;}
		entry.setPos(null);
		return entry;
	}
	
	/**
	 * Modifica la clave de una entrada dada y devuelve la clave vieja
	 * @param e entrada a modificar
	 * @param key clave nueva
	 * @return clave vieja de e
	 * @throws InvalidKeyException si la calve no es Comparable
	 */
	public K replaceKey(Entry<K,V> e, K key)throws InvalidKeyException{
		EntradaPosicionada<K, V> entry = null;
		try {
			entry = checkEntry(e);
		} catch (InvalidEntryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		K oldKey = entry.getKey();
		entry.setKey(key);
		try{
			entries.set(entry.getPosicion(),entry);
		}
		catch(InvalidPositionException t){return null;}
		return oldKey;
	}
	/**
	 * Modifica el valor de una entrada y devuelve el valor viejo
	 * @param e entrada a Modificar
	 * @param value nuevo valor
	 * @return valor viejo
	 */
	public V replaceValue(Entry<K,V> e, V value) throws InvalidEntryException{
		EntradaPosicionada<K,V> entry = checkEntry(e);
		V oldValue = entry.getValue();
		entry.setValue(value);
		try {
			entries.set(entry.getPosicion(), entry);
		} catch (InvalidPositionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return oldValue;
	}
	
	private EntradaPosicionada<K,V> checkEntry(Entry<K,V> e) throws InvalidEntryException{
		if(e == null)
			throw new InvalidEntryException("Entrada invalida");
		else{
			try{
				return (EntradaPosicionada<K,V>) e;
			}
			catch(ClassCastException t){throw new InvalidEntryException("Entrada Invalida");}
		}
			
	}
}
