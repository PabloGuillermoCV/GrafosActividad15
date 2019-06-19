package kruskalHeap;

import java.util.ArrayList;

import disjointSet.*;
import grafo.*;
import heap.*;

public class KruskalHeapSH{
    private PriorityQueue<ArcoED,Integer> arcos;
    private int cantNodos;
    private EDDisjointSetSH DS;
    
    public KruskalHeapSH(EDGrafoListaAdyacencias g) {
    	cantNodos = g.getNodos().size();
    	arcos = new Heap<ArcoED,Integer>(g.getArcos().size()+1, new Comparator<ArcoED>() );
    	for(ArcoED a : g.getArcos()) {
	    	try {
				arcos.insert(a, a.getPeso());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    	DS = new EDDisjointSetSH(g.getNodos());
    }
    
    public ArrayList<ArcoED> minimumSpanningTree() {
    	 ArrayList<ArcoED> T = new ArrayList<ArcoED>();
    	 while(T.size() != (cantNodos-1)){
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
         
         return T;
    }
    
}