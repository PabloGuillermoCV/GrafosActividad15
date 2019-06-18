package Ejer1a;

import cola.FullQueueException;

public interface BFS<V,E> {
	
	public int doBFS();
	
	public boolean esConexo() throws FullQueueException;
}
