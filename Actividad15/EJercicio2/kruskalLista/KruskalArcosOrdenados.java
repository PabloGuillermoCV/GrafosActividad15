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
        DS = new EDDisjointSetCH();
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
    
    private void merge (ArrayList <ArcoED> A1, ArrayList <ArcoED> A2, ArrayList <ArcoED> A) {
    	int IndiceA1 = 0;
    	int IndiceA2 = 0;
    	int IndiceA = 0;
    	
    	while (IndiceA1 < A1.size () && IndiceA2 < A2.size ()) {
    		if (A1.get (IndiceA1).getPeso () < A2.get (IndiceA2).getPeso ()) {
                A.set (IndiceA, A1.get (IndiceA1));
                IndiceA1++;
            }
    		else {
                A.set (IndiceA, A2.get (IndiceA2));
                IndiceA2++;
            }
            IndiceA++;
    	}
    	
    	while (IndiceA1 < A1.size ()) {
    		A.set (IndiceA, A1.get (IndiceA1));
    		IndiceA1++;
    		IndiceA++;
    	}
    	while (IndiceA2 < A2.size ()) {
    		A.set (IndiceA, A2.get (IndiceA2));
    		IndiceA2++;
    		IndiceA++;
    	}
    }
}