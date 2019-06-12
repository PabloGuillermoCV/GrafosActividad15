package ColasConPrioridad;

public interface Entry<K,V> {
	//getters
	public K getKey();
	public V getValue();
	public void setKey(K key);
	public void setValue(V value);
	
}