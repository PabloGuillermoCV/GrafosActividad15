import java.util.ArrayList;

public class KruskalArcosOrdenados{
    
    private ArrayList<ArcoED> arcos;
    private ArrayList<Nodo> nodos;
    EDDisjointSetCH DS;



    public KruskalArcosOrdenados(EDGrafoListaAdyacencias grafo){
        //inicializar todo aquí
        arcos = grafo.getArcos();
        nodos = grafo.getNodos();
    }

    public ArrayList<Pesado> Kruskal(){
        ArrayList<Integer> T = new ArrayList<Integer>();
        do{
            Pesado uv = arcos.first();
            /*compu = DS.find(uv.source());
            compv = DS.find(uv.target());
            if(compu != compv){
                DS.union(uv.source(), uv.target())
                T.add(uv)
            }
            */

        }
        while(T.size() < nodos.size()-1);
        return T;
    }
}