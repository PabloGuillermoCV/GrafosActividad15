package Ejer1a;
import disjointSet.EDDisjointSetCH;
import disjointSet.NodoDS;
import grafo.ArcoED;
import grafo.EDGrafoListaAdyacencias;
import grafo.Nodo;

public class ConexoDisjointSet{

    private EDDisjointSetCH DS;
    private EDGrafoListaAdyacencias graph;

    public ConexoDisjointSet(EDGrafoListaAdyacencias g){
        graph = g;
        DS = new EDDisjointSetCH();
    }

    public boolean checkConexo(){
        NodoDS[] nodos = new NodoDS[graph.getNodos().size()];
        int i = 0;
        for(Nodo n: graph.getNodos()) {
        	nodos[i] = DS.makeSet(n.getRotulo(), n);
        }
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