package kruskalLista;
import java.util.ArrayList;

import disjointSet.EDDisjointSetCH;
import grafo.ArcoED;
import grafo.EDGrafoListaAdyacencias;
import grafo.Nodo;
import grafoJSON.Pesado;

public class KruskalArcosOrdenados{
    
    private ArrayList<ArcoED> arcos;
    private ArrayList<Nodo> nodos;
    EDDisjointSetCH DS;



    public KruskalArcosOrdenados(EDGrafoListaAdyacencias grafo){
        //inicializar todo aqui
        arcos = grafo.getArcos();
        nodos = grafo.getNodos();
        DS = new EDDisjointSetCH();
    }

    public ArrayList<Integer> Kruskal(){
        ArrayList<Integer> T = new ArrayList<Integer>();
        do{
        	//Cuidado, puede entrar en ciclo infinito, ver
            ArcoED uv = arcos.get(0);
            
            /*compu = DS.findSet(uv.source());
            compv = DS.findSet(uv.target());
            if(compu != compv){
                DS.union(uv.source(), uv.target())
                T.add(uv)
                arcos.remove(uv);
                
            }
            */

        }
        while(T.size() < nodos.size()-1);
        return T;
    }
}