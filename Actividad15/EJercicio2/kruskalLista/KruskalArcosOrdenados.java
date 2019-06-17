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
        arcos = mergeSort (arcos);
        nodos = grafo.getNodos();
        DS = new EDDisjointSetCH(nodos.size());
    }
    
    /**
     * Metodoq ue se encarga de ejecutar el algoritmo de Kruskal sobre el grafo ingresado
     * @return un arraylist de arcos que generan el arbol de cubrimiento minimal sobre el grafo ingresado
     */
    public ArrayList<ArcoED> Kruskal(){
        ArrayList<ArcoED> T = new ArrayList<ArcoED>();
        int i = 0;
        //Inicializo la estructura DS
        for(Nodo n : nodos) {
        	DS.makeSet(n);
        }
        do{
        	
        	//Cuidado, puede entrar en ciclo infinito, ver
            ArcoED uv = arcos.get(i);
            System.out.println("Iteracion: " + i);
            Nodo compu = DS.findSet(uv.getSource());
            Nodo compv = DS.findSet(uv.getTarget());
            if(!(compu.equals(compv) )){
                DS.union(uv.getSource(), uv.getTarget());
                T.add(uv);
                i++;
                
            }
            

        }
        while(T.size() < nodos.size()-1 | i == arcos.size() );
        return T;
    }
    
	 /**
	  * Metodo de ordenamiento para ordenar los arcos según el algoritmo mergeSort
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
	 * Metodo de combinación del mergeSort
	 * @param A1 arraylist izqueirda a combinar
	 * @param A2 arraylist derecha a combinar
	 * @param A arraylist que surge de combinar y ordenar los arreglos A1 y A2
	 */
    private void merge (ArrayList <ArcoED> A1, ArrayList <ArcoED> A2, ArrayList <ArcoED> A) {
    	int IndiceA1 = 0;
    	int IndiceA2 = 0;
    	int IndiceA = 0;
    	while (IndiceA1 < A1.size () && IndiceA2 < A2.size ()) {
    		if (A1.get (IndiceA1).getPeso () < A2.get (IndiceA2).getPeso ()) {
                A.add(A1.get (IndiceA1));
                IndiceA1++;
            }
    		else {
                A.add (A2.get (IndiceA2));
                IndiceA2++;
            }
            IndiceA++;
    	}
    	
    	while (IndiceA1 < A1.size ()) {
    		A.add(A1.get (IndiceA1));
    		IndiceA1++;
    		IndiceA++;
    	}
    	while (IndiceA2 < A2.size ()) {
    		A.add (A2.get (IndiceA2));
    		IndiceA2++;
    		IndiceA++;
    	}
    }
}