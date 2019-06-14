package Ejer1a;


import cola.*;
import grafo.*;

public class BreadthFirstSearch<V,E> implements BFS<V,E> {
	
	protected EDGrafoListaAdyacencias graph;
	protected Queue<Nodo> cola;
	
	public BreadthFirstSearch(EDGrafoListaAdyacencias g){
		graph = g;
		cola= new ColaConEnlaces<Nodo>();
	}
	
	public void doBFS() {
		
		for(Nodo v: graph.getNodos()) {
			v.setColor("blanco");
		}
		
		
		for(Nodo v: graph.getNodos()) {
			
			if (v.getColor() == "blanco") {
				v.setColor("gris");
				try {
					cola.enqueue(v);
				} catch (FullQueueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				visitarBFS();
			}
		}
		
	}
	
	private void visitarBFS() {
		Nodo u; //Habria que inicializarlo en null? por si nunca entra al while?
		while(!cola.isEmpty()) {
			try {
				u=cola.front();
				for(ArcoED e: graph.incidentes(u)){ //ver metodos
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
			catch(EmptyQueueException e) {
				
			}
			catch(FullQueueException e) {
				
			}
		
	}

	
	}
}
