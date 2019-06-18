package disjointSet;

import grafo.Nodo;

public class EDDisjointSetSH{

    private Nodo[] cjtos;
    private int ultimaPos = 0;
    private Nodo raiz;
    
    public EDDisjointSetSH(int numeroNodos) {
    	cjtos = new Nodo[numeroNodos];
    }
    
    /**
     * Se encarga de crear un cjto e insertarlo en la estructura
     * @param n un entero que representarÃ¡ al elemento representante del cjto creado
     */
    public void makeSet(Nodo n){
    	if(ultimaPos == 0)
    		raiz = n;
        //TODO: Verificar tema de como meter el nuevo nodo en la estructura en si, ver posicionamiento en el arreglo
        cjtos[ultimaPos] = n;
        n.setPosEnDS(ultimaPos);
        n.setPadre(n);
        ultimaPos++;
    }

    /**
     * Se encarga de encontrar un cjto en la estructura
     * @param n un nodo pertenenciente a la estructura
     * @return el nodo que es representante del conjunto buscado o NULL en caso de no encontrarlo
     */
    public Nodo findSet(Nodo n){
   
        Nodo padreN = n.getPadre();
        //SIN HEURISTICA, solo devuelvo el representante del cjto, sin comprimir los caminos hasta la raiz
        if(padreN.getRotulo() != n.getRotulo())
           return findSet(padreN);
        else
        	return n;
    }

    /**
     * Se encarga de unir dos cjtos presentes en el arbol
     * @param x nodo perteneciente a algun cojto
     * @param y nodo perteneciente a algun otro cjto
     */
    public void union(Nodo x, Nodo y){
       Nodo RepresentanteX = findSet(x);
       Nodo RepresentanteY = findSet(y);
       //SIN HEURISTICA! adoso el cjto x a y SIEMPRE
       RepresentanteX.setPadre(RepresentanteY);
       cjtos[RepresentanteY.getPosEnDS()] = null;
       int dif = RepresentanteX.getRango() - RepresentanteY.getRango();
       if(dif < 0)
        RepresentanteX.setRango(RepresentanteX.getRango() + (-1*dif));
       else
        RepresentanteX.setRango(RepresentanteX.getRango() + dif);
    }

}