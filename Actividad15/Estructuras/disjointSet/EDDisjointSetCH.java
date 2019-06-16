package disjointSet;



import grafo.Nodo;

public class EDDisjointSetCH{
    /*
        NOTA: Simular un arbol con un arreglo, como si estuvieramos usando un heap, sin ser un heap
    */

    private Nodo[] cjtos;
    private int ultimaPos = 0;

    /**
     * Se encarga de crear un cjto e insertarlo en la estructura
     * @param n un entero que representará al elemento representante del cjto creado
     */

    public void makeSet(Nodo n){
   
        //TODO: Verificar tema de como meter el nuevo nodo en la estructura en si, ver posicionamiento en el arreglo, o si es realmente necesario el arreglo
        cjtos[ultimaPos] = n;
        n.setPosEnDS(ultimaPos);
        ultimaPos++;
        
    }

    /**
     * Se encarga de encontrar un cjto en la estructura
     * @param n un nodo pertenenciente a la estructura
     * @return el nodo que es representante del conjunto buscado 
     */
    public Nodo findSet(Nodo n){
        Nodo padreN = n.getPadre();
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
    public void union(Nodo x, Nodo y){
        Link(findSet(x), findSet(y)); //Ojo, estoy pasando los nodos, esto hay que revisar por el findSet
    }

    /**
     * Se encarga de unir los conjuntos, aplicando la heuristica de Union por rango
     * @param x representante del primer cjto
     * @param y representante del segundo cjto
     */
    private void Link(Nodo x, Nodo y){
        int rankX = x.getRango();
        int rankY = y.getRango();
        if(rankX > rankY) {
            y.setPadre(x);
            cjtos[y.getPosEnDS()] = null;
      
        }
        else{
            x.setPadre(y);
            cjtos[x.getPosEnDS()] = null;
            if(rankX == rankY)
                y.setRango(rankY + 1);
        }
    }
}