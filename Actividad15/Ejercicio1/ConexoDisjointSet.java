
public class ConexoDisjointSet{

    private EDDisjointSetCH DS;
    private EDGrafoListaAdyacencias graph;

    public ConexoDisjointSet(EDGrafoListaAdyacencias g){
        graph = g;
        DS = new EDDisjointSetCH();
    }

    public boolean checkConexo(){
        
        for(ArcoED e: graph.getArcos()){
            Nodo v1 = e.getSource();
            Nodo v2 = e.getTarget();
            //TODO: Corregir esto para poder usar los nodos del grafo
            if(! (DS.findSet(v1).equals(DS.findSet(v2)) ));
                DS.union();
        }
        
        return true;
    }
    
}