package kruskalHeap;

import java.util.ArrayList;

import disjointSet.*;
import grafo.*;
import heap.*;

public class KruskalHeapSH{
    private PriorityQueue<ArcoED,Integer> arcos;
    private ArrayList<Nodo> nodos;
    private EDDisjointSetSH DS;
    
    public KruskalHeapSH(EDGrafoListaAdyacencias g) {
    	nodos = g.getNodos();
    	arcos = new Heap<ArcoED,Integer>(g.getArcos().size(), new Comparator<ArcoED>() );
    	DS = new EDDisjointSetSH();
    }
    
    public ArrayList<ArcoED> minimumSpanningTree() {
    	 ArrayList<ArcoED> T = new ArrayList<ArcoED>();
         do{
             try {
				ArcoED uv = arcos.removeMin().getKey();
				Nodo compu = DS.findSet(uv.getSource());
	             Nodo compv = DS.findSet(uv.getTarget());
	             if(!(compu.equals(compv))){
	                 DS.union(uv.getSource(), uv.getTarget());
	                 T.add(uv);
	                 
	                 
	             }
	             
			} catch (EmptyPriorityQueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
             

         }
         while(T.size() < nodos.size()-1);
         return T;
    }
    
}