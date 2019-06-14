package disjointSet;

import grafo.Nodo;

public class NodoDS {
	 private int representante;
     private NodoDS padre;
     private int rango = 0;

     
     /**
      * Constructor de NodoDS
      * @param r entero que sera el representante del cjto
      */
     public NodoDS(int r, Nodo n){
         representante = r;
         padre = this;

     }

     //Setters y Gettters para los atributos del Nodo
     public int getRepresentante(){
         return representante;
     }
     public void setRepresentante(int r){
         representante = r;
     }
     
     public void setPadre(NodoDS p){
         padre = p;
     }

     public NodoDS getPadre(){
         return padre;
     }

     public void setRango(int n){
         rango = n;
     }

     public int getRango(){
         return rango;
     }
}
