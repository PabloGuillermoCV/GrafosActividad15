
public class ConexoDisjointSet{

    private EDDisjointSetCH DS;
    private EDGrafoListaAdyacencias graph;

    public ConexoDisjointSet(EDGrafoListaAdyacencias g){
        graph = g;
        DS = new EDDisjointSetCH();
    }

    public checkConexo(){
        for(ArcoED e: g.getArcos()){
            Nodo v1 = e.getSource();
            Nodo v2 = e.gettarget();
            if(! (DS.findSet(v1).equals(DS.findSet(v2)) ));
        }
    }
    
}