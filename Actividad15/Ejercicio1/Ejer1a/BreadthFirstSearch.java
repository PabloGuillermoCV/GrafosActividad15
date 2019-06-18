package Ejer1a;


import java.util.ArrayList;

import cola.*;
import grafo.*;

public class BreadthFirstSearch<V,E> implements BFS<V,E> {
	
	protected EDGrafoListaAdyacencias graph;
	protected Queue<Nodo> cola;

	
	public BreadthFirstSearch(EDGrafoListaAdyacencias g){
		graph = g;
		cola= new ColaConEnlaces<Nodo>();
		
	}
	
	/**
	 * Metodo principal que se encarga de recorrer el grafo segun el algoritmo BFS
	 */
	//El ciclo infinito se genera acá a veces con el grafo9
	public int doBFS() {
	
		
		ArrayList<Nodo> arregloNodos=graph.getNodos();
		Nodo nodo_origen=arregloNodos.get(0);
		nodo_origen.setColor("gris");
		try {
		cola.enqueue(nodo_origen);
		}
		catch(FullQueueException e){
			System.out.println("La cola esta llena y no puede incorporar nuevo elemento");			
		}
		int visitados = visitarBFS();
		return visitados;
		
	}
	
	/**
	 * Metodo auxiliar que realiza la visita a los nodos encontrados por el algoritmo BFS
	 */
	private int visitarBFS() {
		Nodo u = null; 
		int posicion=1;
		while(!cola.isEmpty() && posicion < graph.getArcos().size()) {
			try {
				u=cola.front();
				//Si el nodo es una hoja sin nodos incidentes, directamente lo desencolo
				if(graph.incidentes(u).isEmpty()) {
					u.setColor("negro");
					cola.dequeue();
				}
				else {
					//Para cada arco incidente al nodo obtenido de la cola
					for(ArcoED e: graph.incidentes(u)){ 
						//Obtengo el nodo opuesto según el arco que estoy considerando en este momento
						Nodo z = graph.getOpuesto(u, e);
						//Verifico si el nodo obtenido es Blanco, si lo es, lo agrego a la cola y sumo 1 al contador de nodos visitados
						if( z.getColor().equals("blanco") && !(z.equals(u))) {
							z.setColor("gris");
							cola.enqueue(z);
							posicion++;
						}
						
						else
						{
							u.setColor("negro");
							cola.dequeue();
							break;
						}
					}
				}
			
				
			}
			catch(EmptyQueueException e) {
				System.out.println("Empty Queue");
			}
			catch(FullQueueException e) {
				System.out.println("Full Queue");
			}
			
	}

		return posicion;
	}
	
	/**
	 * determina si un grafo es Conexo o No
	 */
	public boolean esConexo() throws FullQueueException{
		
		//Realizo el BFS y cuento cuantos nodos visité
		int visitados = doBFS();
		
		//si con un solo BFS recorrí todo el grafo y marqué todos los nodos, entonces, el grafo es conexo
		return visitados == graph.getNodos().size();
	}
	
	
}
