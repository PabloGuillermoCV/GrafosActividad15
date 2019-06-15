package kruskalHeap;

import java.util.ArrayList;

import disjointSet.*;
import grafo.*;
import heap.*;

public class KruskalHeapSH{
    private PriorityQueue<ArcoED,Integer> arcos;
    private EDGrafoListaAdyacencias grafo;
    private EDDisjointSetSH DS;
    private ArrayList<Nodo> nodos;
    
    public KruskalHeapSH(EDGrafoListaAdyacencias g) {
    	grafo = g;
    	arcos = new Heap<ArcoED,Integer>(g.getArcos().size(), new Comparator<ArcoED>() );
    	DS = new EDDisjointSetSH();
    }
    
    public ArrayList<Integer> minimumSpanningTree() {
    	 ArrayList<Integer> T = new ArrayList<Integer>();
         do{
             try {
				ArcoED uv = arcos.removeMin().getKey();
				/*compu = DS.find(uv.source());
	             compv = DS.find(uv.target());
	             if(compu != compv){
	                 DS.union(uv.source(), uv.target())
	                 T.add(uv)
	                 
	                 
	             }
	             */
			} catch (EmptyPriorityQueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
             

         }
         while(T.size() < nodos.size()-1);
         return T;
    }
    
}