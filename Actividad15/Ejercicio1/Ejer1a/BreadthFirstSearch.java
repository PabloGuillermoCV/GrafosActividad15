package Ejercicio1a;

public class BreadthFirstSearch<V,E> implements BFS {
	
	protected EDGrafoListaAdyacencias graph;
	protected Queue<V> cola;
	
	public BreadthFirstSearch(EDGrafoListaAdyacencias g){
		graph = g;
		cola=new ColaConEnlaces<Nodo>();
	}
	
	public void doBFS() {
		
		for(Nodo v: graph.getNodos()) {
			v.setColor("blanco");
		}
		
		
		for(Nodo v: graph.getNodos()) {
			
			if (v.getColor() == "blanco") {
				v.setColor("gris");
				cola.enqueue(v);
				visitarBFS();
			}
		}
		
	}
	
	private void visitarBFS() {
		Nodo u; //Habria que inicializarlo en null? por si nunca entra al while?
		while(!cola.isEmpty()) {
			u=cola.front();
			for(ArcoED e: graph.getAdyacentes(u)){ //ver metodos
				Nodo z = graph.getOpuesto(u, e); //ver metodos
				if( z.getColor().equals( "blanco")) {
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
