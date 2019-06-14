package disjointSet;
import java.util.ArrayList;

import grafo.Nodo;

public class EDDisjointSetCH{
    /*
        NOTA: Simular un arbol con un arreglo, como si estuvieramos usando un heap, sin ser un heap
    */

    private NodoDS[] cjtos;
    private int ultimaPos = 0;

    /**
     * Se encarga de crear un cjto e insertarlo en la estructura
     * @param n un entero que representará al elemento representante del cjto creado
     */
    public NodoDS makeSet(int n){
        NodoDS nodo = new NodoDS(n);
        //TODO: Verificar tema de como meter el nuevo nodo en la estructura en si, ver posicionamiento en el arreglo, o si es realmente necesario el arreglo
        cjtos[ultimaPos] = nodo;
        ultimaPos++;
        return nodo;
    }

    /**
     * Se encarga de encontrar un cjto en la estructura
     * @param n un nodo pertenenciente a la estructura
     * @return el nodo que es representante del conjunto buscado 
     */
    public NodoDS findSet(NodoDS n){
        NodoDS padreN = n.getPadre();
        //Realizo la compresion de caminos al asignar recursivamente padres a los cjtos
        if(padreN != n)
            n.setPadre(findSet(padreN));
        return padreN;
    }

    /**
     * Se encarga de unir dos cjtos presentes en el arbol
     * @param x nodo perteneciente a algun cojto
     * @param y nodo perteneciente a algun otro cjto
     */
    public void union(NodoDS x, NodoDS y){
        Link(findSet(x), findSet(y)); //Ojo, estoy pasando los nodos, esto hay que revisar por el findSet
    }

    /**
     * Se encarga de unir los conjuntos, aplicando la heuristica de Union por rango
     * @param x representante del primer cjto
     * @param y representante del segundo cjto
     */
    private void Link(NodoDS x, NodoDS y){
        int rankX = x.getRango();
        int rankY = y.getRango();
        if(rankX > rankY)
            y.setPadre(x);
        else{
            x.setPadre(y);
            if(rankX == rankY)
                y.setRango(rankY + 1);
        }
    }
}