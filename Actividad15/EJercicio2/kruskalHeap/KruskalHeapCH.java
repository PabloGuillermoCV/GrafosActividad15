package kruskalHeap;

import java.util.ArrayList;

import disjointSet.*;
import grafo.*;
import heap.*;

public class KruskalHeapCH{
	
	private PriorityQueue<ArcoED,Integer> arcos;
    private EDDisjointSetCH DS;
    private int cantNodos;
    
    public KruskalHeapCH(EDGrafoListaAdyacencias g) {
    
    	arcos = new Heap<ArcoED,Integer>(g.getArcos().size()+1, new Comparator<ArcoED>() );
      	for(ArcoED a : g.getArcos()) {
	    	try {
				arcos.insert(a, a.getPeso());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    	DS = new EDDisjointSetCH(g.getNodos());
    	cantNodos = g.getNodos().size();
    }
	
	/**
	 * Metodo que se encarga de obtener el arbol de cubrimiento minimal segun Kruskal	
	 * @return arraylist de arcos que generan el arbol de cubrimiento minimal para el grafo ingresado
	 */
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