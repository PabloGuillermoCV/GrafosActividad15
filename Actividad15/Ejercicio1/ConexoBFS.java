

public class ConexoBFS{
    
    private EDGrafoListaAdyacencias grafo;

    public ConexoBFS(EDGrafoListaAdyacencias g){
        grafo = g;
    }

    public int checkConexo(){
        //TODO: chequear el tema de la parametrizacion
        BFS ejercicio = new BreadthFirstSearch<Nodo,ArcoED>(grafo);
        ejercicio.doBFS();
    }

}