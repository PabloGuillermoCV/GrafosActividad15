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
    	arcos = new Heap<ArcoED,Integer>(g.getArcos().size()+1, new Comparator<ArcoED>() );
    	for(ArcoED a : g.getArcos()) {
	    	try {
				arcos.insert(a, a.getPeso());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    	DS = new EDDisjointSetSH(nodos.size());
    }
    
    public ArrayList<ArcoED> minimumSpanningTree() {
    	 ArrayList<ArcoED> T = new ArrayList<ArcoED>();
    	 for(Nodo n : nodos) {
         	DS.makeSet(n);
         }
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
         while(!arcos.isEmpty());
         return T;
    }
    
}