package Ejer1a;
import cola.FullQueueException;
import grafo.ArcoED;

import grafo.EDGrafoListaAdyacencias;
import grafo.Nodo;

public class ConexoBFS{
    
    private EDGrafoListaAdyacencias grafo;

    public ConexoBFS(EDGrafoListaAdyacencias g){
        grafo = g;
    }
    
    /**
     * metodo que se encarga de verificar si el grafo ingresado es conexo o no
     *  por medio de un BFS
     * @return si el grafo es conexo o no
     */
    public boolean checkConexo(){
        BFS<Nodo,ArcoED> ejercicio = new BreadthFirstSearch<Nodo,ArcoED>(grafo);
        boolean esGrafoConexo = false;
		try {
			esGrafoConexo = ejercicio.esConexo();
		} catch (FullQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return esGrafoConexo;
    }

}