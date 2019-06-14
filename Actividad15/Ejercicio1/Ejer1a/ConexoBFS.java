package Ejer1a;
import grafo.ArcoED;

import grafo.EDGrafoListaAdyacencias;
import grafo.Nodo;

public class ConexoBFS{
    
    private EDGrafoListaAdyacencias grafo;

    public ConexoBFS(EDGrafoListaAdyacencias g){
        grafo = g;
    }

    public boolean checkConexo(){
        //TODO: chequear el tema de la parametrizacion
        BFS<Nodo,ArcoED> ejercicio = new BreadthFirstSearch<Nodo,ArcoED>(grafo);
        ejercicio.doBFS();
        return true;
    }

}