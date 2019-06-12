package ColasConPrioridad;

public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K, V> {

	/**
	 * Elimina una entrada de al Cola con Prioridades
	 * @param e entrada a eliminar
	 * @return la entrada eliminada
	 */
	public Entry<K,V> remove(Entry<K,V> e)  throws InvalidEntryException;
	
	/**
	 * Modifica la clave de una entrada dada y devuelve la clave vieja
	 * @param e entrada a modificar
	 * @param key clave nueva
	 * @return clave vieja de e
	 * @throws InvalidKeyException si la calve no es Comparable
	 */
	public K replaceKey(Entry<K,V> e, K key) throws InvalidKeyException;
	/**
	 * Modifica el valor de una entrada y devuelve el valor viejo
	 * @param e entrada a Modificar
	 * @param value nuevo valor
	 * @return valor viejo
	 */
	public V replaceValue(Entry<K,V> e, V value) throws InvalidEntryException;
}
