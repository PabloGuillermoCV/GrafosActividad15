

public class ConexoBFS{
    
    private EDGrafoListaAdyacencias<V,E> grafo;

    public ConexoBFS(EDGrafoListaAdyacencias<V,E> g){
        grafo = g;
    }

    public int checkConexo(){
        //TODO: chequear el tema de la parametrizacion
        BFS ejercicio = new BreadthFirstSearch<nodo,Arco>(grafo);
        ejercicio.doBFS();
    }

}