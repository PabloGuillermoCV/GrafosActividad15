package Ejer1a;
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
        //TODO: chequear el tema de la parametrizacion
        BFS<Nodo,ArcoED> ejercicio = new BreadthFirstSearch<Nodo,ArcoED>(grafo);
        boolean esGrafoConexo=ejercicio.esConexo();
        return esGrafoConexo;
    }

}