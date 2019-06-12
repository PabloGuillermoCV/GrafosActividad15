import java.util.ArrayList;

public class KruskalArcosOrdenados{
    
    private ArrayList<Pesado> arcos;
    private ArrayList<Integer> nodos;
    ConjuntoDisjuntoHeuristica DS;


    public KruskalArcosOrdenados(Grafo grafo){
        //inicializar todo aquí
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