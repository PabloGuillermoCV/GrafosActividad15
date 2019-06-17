package Ejer1a;


import java.util.ArrayList;

import cola.*;
import grafo.*;

public class BreadthFirstSearch<V,E> implements BFS<V,E> {
	
	protected EDGrafoListaAdyacencias graph;
	protected Queue<Nodo> cola;
	protected boolean[] hayDistancia;
	
	public BreadthFirstSearch(EDGrafoListaAdyacencias g){
		graph = g;
		cola= new ColaConEnlaces<Nodo>();
		hayDistancia= new boolean[g.getNodosCount()-1]; 
	}
	
	/**
	 * Metodo principal que se encarga de recorrer el grafo segun el algoritmo BFS
	 * @throws FullQueueException 
	 */
	public void doBFS() throws FullQueueException {
		// ojo no se si dejar el metodo publico
		
		for(Nodo v: graph.getNodos()) {
			v.setColor("blanco");
		}
		
		for(int i=0; i <= hayDistancia.length; i++)
		{
			hayDistancia[i]=false;
		}
		/*
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
		}*/
		
		ArrayList<Nodo> arregloNodos=graph.getNodos();
		Nodo nodo_origen=arregloNodos.get(0);
		nodo_origen.setColor("gris");
		try {
		cola.enqueue(nodo_origen);
		}
		catch(FullQueueException e){
			System.out.println("La cola esta llena y no puede incorporar nuevo elemento");			
		}
		visitarBFS();
		
	}
	
	/**
	 * Metodo auxiliar que realiza la visita a los nodos encontrados por el algoritmo BFS
	 */
	private void visitarBFS() {
		Nodo u; //Habria que inicializarlo en null? por si nunca entra al while?
		int posicion=0;
		while(!cola.isEmpty()) {
			try {
				u=cola.front();
				for(ArcoED e: graph.incidentes(u)){ //ver metodos
					Nodo z = graph.getOpuesto(u, e); //ver metodos
					if( z.getColor().equals( "blanco")) {
						hayDistancia[posicion]=true;
						z.setColor("gris");
						cola.enqueue(z);
						posicion++;
						//revisar bien  si aca aumenta la posicion y lo nuevo que
						//se agrego
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
	
	public boolean esConexo() throws FullQueueException{
		
		try{
			doBFS();
		}
		catch(FullQueueException e) {
			System.out.println("La cola esta llena y no puede incorporar nuevo elemento");
		}
		int i=0;
		boolean esGrafoConexo=true;
		while ( esGrafoConexo ){
			if(hayDistancia[i] == false)
			{
				esGrafoConexo=false;
			}
			i++;
		}
		
		return esGrafoConexo;
	}
	
	
}
