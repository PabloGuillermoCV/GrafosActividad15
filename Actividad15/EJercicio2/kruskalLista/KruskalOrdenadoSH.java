package kruskalLista;

import java.util.ArrayList;
import java.util.ListIterator;

import disjointSet.*;
import grafo.*;

public class KruskalOrdenadoSH{
	 
    private ArrayList<ArcoED> arcos;
    private int cantNodos;
    private EDDisjointSetSH DS;
    
    public KruskalOrdenadoSH(EDGrafoListaAdyacencias grafo){
    	//inicializar todo aqui
        arcos = grafo.getArcos();
        arcos = mergeSort (arcos);
        DS = new EDDisjointSetSH(grafo.getNodos());

    }
	
    /**
     * Metodo que ejecuta el algoritmo de Kruskal sobre el grafo obtenido
     * @return una lista de arcos que construyen el arbol minimal de cubrimiento para ese grafo
     */
	 public ArrayList<ArcoED> Kruskal(){
        ArrayList<ArcoED> T = new ArrayList<ArcoED>();
    
   
        ListIterator <ArcoED> iterator = arcos.listIterator();
        while((T.size() != (cantNodos-1)) && iterator.hasNext () ){
        	
        	ArcoED uv = iterator.next ();
            Nodo compu = DS.findSet(uv.getSource());
            Nodo compv = DS.findSet(uv.getTarget());
            if(!(compu.equals(compv) )){
                DS.union(uv.getSource(), uv.getTarget());
                
                T.add(uv);
                   
            }  
        }
        return T;
	}
	
	 /**
	  * Metodo de ordenamiento para ordenar los arcos seg�n el algoritmo mergeSort
	  * @param A ArrayList de Arcos Pesados a ordenar
	  * @return ArrayList de arcos ordenados por su peso
	  */
	public ArrayList <ArcoED> mergeSort (ArrayList <ArcoED> A) {
		if (A.size () == 1) {
    		return A;
    	}
    	
    	else {
    		int mitad = A.size ()/2;
    		ArrayList <ArcoED> AIzquierdo = new ArrayList <ArcoED> ();
    		ArrayList <ArcoED> ADerecho = new ArrayList <ArcoED> ();
    		
    		for (int I = 0; I < mitad; I++) {
    			AIzquierdo.add (A.get (I));
    		}
    		for (int I = mitad; I < A.size (); I++) {
    			ADerecho.add (A.get (I));
    		}
    		
    		AIzquierdo = mergeSort (AIzquierdo);
    		ADerecho = mergeSort (ADerecho);
    		
    		ArrayList <ArcoED> AOrdenado = new ArrayList <ArcoED> ();
    		merge (AIzquierdo, ADerecho, AOrdenado);
    		return AOrdenado;
    	}
    }
    
	/**
	 * Metodo de combinaci�n del mergeSort
	 * @param A1 arraylist izqueirda a combinar
	 * @param A2 arraylist derecha a combinar
	 * @param A arraylist que surge de combinar y ordenar los arreglos A1 y A2
	 */
    private void merge (ArrayList <ArcoED> A1, ArrayList <ArcoED> A2, ArrayList <ArcoED> A) {
    	int IndiceA1 = 0;
    	int IndiceA2 = 0;
    
    	while (IndiceA1 < A1.size () && IndiceA2 < A2.size ()) {
    		if (A1.get (IndiceA1).getPeso () < A2.get (IndiceA2).getPeso ()) {
                A.add(A1.get (IndiceA1));
                IndiceA1++;
            }
    		else {
                A.add (A2.get (IndiceA2));
                IndiceA2++;
            }  
    	}
    	
    	while (IndiceA1 < A1.size ()) {
    		A.add ( A1.get (IndiceA1));
    		IndiceA1++;
    	
    	}
    	while (IndiceA2 < A2.size ()) {
    		A.add ( A2.get (IndiceA2));
    		IndiceA2++;
    	}
    }
}