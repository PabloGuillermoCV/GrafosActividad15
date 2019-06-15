package Ejer1a;
import disjointSet.EDDisjointSetCH;

import grafo.*;


public class ConexoDisjointSet{

    private EDDisjointSetCH DS;
    private EDGrafoListaAdyacencias graph;

    public ConexoDisjointSet(EDGrafoListaAdyacencias g){
        graph = g;
        DS = new EDDisjointSetCH();
    }

    public boolean checkConexo(){
       
        int i = 0;
        for(Nodo n: graph.getNodos()) {
        	DS.makeSet(n);
        }
        for(ArcoED e: graph.getArcos()){
            Nodo v1 = e.getSource();
            Nodo v2 = e.getTarget();
            if(! (DS.findSet(v1).equals(DS.findSet(v2)) ));
                DS.union(v1,v2);
        }
        
        return true;
    }
    
}