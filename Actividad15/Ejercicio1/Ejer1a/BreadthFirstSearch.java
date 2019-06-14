package Ejercicio1a;

public class BreadthFirstSearch<V,E> implements BFS {
	
	protected Graph<V,E> graph;
	protected Queue<V> cola;
	
	public BreadthFirstSearch(Graph<V,E> g){
		graph = g;
		cola=new ColaConEnlaces();
	}
	
	public void doBFS() {
		
		for(Vertex<V> v: graph.vertices()) {
			v.setColor("blanco");
		}
		
		
		for(Vertex<V> v: graph.vertices()) {
			
			if (v.getColor() == "blanco") {
				v.setColor("gris");
				cola.enqueue(v);
				visitarBFS();
			}
		}
		
	}
	
	private void visitarBFS() {
		Vertex<V> u; //Habria que inicializarlo en null? por si nunca entra al while?
		while(!cola.isEmpty()) {
			u=cola.front();
			for(Edge<E> e: graph.incidentEdges(u)){ //ver metodos
				Vertex<V> z = graph.opposite(u, e); //ver metodos
				if( z.getColor == "blanco") {
					z.setColor("gris");
					cola.enqueue(z);
				}
				else
				{
					u.setColor("negro");
					cola.dequeue();
				}
					
			
			}
		
	}

	
	}
}
