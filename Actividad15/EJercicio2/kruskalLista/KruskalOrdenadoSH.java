package kruskalLista;

import java.util.ArrayList;

import disjointSet.*;
import grafo.*;


public class KruskalOrdenadoSH{
	 
    private ArrayList<ArcoED> arcos;
    private ArrayList<Nodo> nodos;
    private EDDisjointSetSH DS;
    
    public KruskalOrdenadoSH(EDGrafoListaAdyacencias grafo){
        //inicializar todo aqui
        arcos = grafo.getArcos();
        nodos = grafo.getNodos();
        DS = new EDDisjointSetSH();
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