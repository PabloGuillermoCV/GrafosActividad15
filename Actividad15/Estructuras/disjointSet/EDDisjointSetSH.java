package disjointSet;


public class EDDisjointSetSH{

    private NodoDS[] cjtos;
    private int ultimaPos = 0;

    /**
     * Se encarga de crear un cjto e insertarlo en la estructura
     * @param n un entero que representarÃ¡ al elemento representante del cjto creado
     */
    public void makeSet(int n){
        NodoDS nodo = new NodoDS(n);
        //TODO: Verificar tema de como meter el nuevo nodo en la estructura en si, ver posicionamiento en el arreglo
        cjtos[ultimaPos] = nodo;
        nodo.setPosEnDS(ultimaPos);
        ultimaPos++;
    }

    /**
     * Se encarga de encontrar un cjto en la estructura
     * @param n un nodo pertenenciente a la estructura
     * @return el nodo que es representante del conjunto buscado o NULL en caso de no encontrarlo
     */
    public NodoDS findSet(NodoDS n){
        NodoDS padreN = n.getPadre();
        //SIN HEURISTICA, solo devuelvo o el padre del cjto, asumiendo que el elemento pasado NO es el representante, o el nodo en si
        if(padreN != n)
           return padreN;

        return n;
    }

    /**
     * Se encarga de unir dos cjtos presentes en el arbol
     * @param x nodo perteneciente a algun cojto
     * @param y nodo perteneciente a algun otro cjto
     */
    public void union(NodoDS x, NodoDS y){
       NodoDS RepresentanteX = findSet(x);
       NodoDS RepresentanteY = findSet(y);
       //SIN HEURISTICA! adoso el elemento y a x SIEMPRE
       RepresentanteY.setPadre(RepresentanteX);
       cjtos[RepresentanteY.getPosEnDS()] = null;
       int dif = RepresentanteX.getRango() - RepresentanteY.getRango();
       if(dif < 0)
        RepresentanteX.setRango(RepresentanteX.getRango() + (-1*dif));
       else
        RepresentanteX.setRango(RepresentanteX.getRango() + dif);
    }

}