package Ejer1a;

import cola.FullQueueException;

public interface BFS<V,E> {
	
	public void doBFS();
	
	public boolean esConexo() throws FullQueueException;
}
