package disjointSet;



import java.util.ArrayList;

import grafo.Nodo;

public class EDDisjointSetCH{
   

    private Nodo[] cjtos;
    private int ultimaPos = 0;
    
    public EDDisjointSetCH(ArrayList<Nodo> nodos) {
    	cjtos = new Nodo[nodos.size()];
    	for(Nodo n: nodos) {
    		makeSet(n);
    	}
    }
    
    /**
     * Se encarga de crear un cjto e insertarlo en la estructura
     * @param n un entero que representara al elemento representante del cjto creado
     */

    public void makeSet(Nodo n){
        cjtos[ultimaPos] = n;
        n.setPosEnDS(ultimaPos);
        n.setPadre(n);
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
        if(!(padreN.getRotulo() == n.getRotulo())) {
        	n.setPadre(findSet(padreN));
        }
        return n.getPadre();
    }

    /**
     * Se encarga de unir dos cjtos presentes en el arbol
     * @param x nodo perteneciente a algun cojto
     * @param y nodo perteneciente a algun otro cjto
     */
    public void union(Nodo x, Nodo y){
        Link(findSet(x), findSet(y)); 
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
            y.setPosEnDS(x.getPosEnDS());
            ultimaPos--;
      
        }
        else{
            x.setPadre(y);
            cjtos[x.getPosEnDS()] = null;
            x.setPosEnDS(y.getPosEnDS());
            ultimaPos--;
            if(rankX == rankY)
                y.setRango(rankY + 1);
        }
    }
	
    /**
     * devuelve la cantidad de cjtos presentes en el Cjto-Disjunto
     */
	public int size(){
		//devolver Length estaba mal porque length me devuelve la longitud del arreglo
		//NO cuantos cjtos hay efectivamente en la ED 
		int i = 0;
		//Busco cuantos elementos existen realmente en la ED
		for(Nodo n : cjtos) {
			if(n != null)
				i++;
		}
		return i;
	}
}