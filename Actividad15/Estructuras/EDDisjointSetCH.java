import java.util.ArrayList;

public class EDDisjointSetCH{
    /*
        NOTA: Simular un arbol con un arreglo, como si estuvieramos usando un heap, sin ser un heap
    */

    private class NodoDS{
        private int representante;
        private int[] cjto;
        private ArrayList<NodoDS> next;

        public NodoDS(int r){
            representante = r;
            next = new ArrayList<NodoDS>();
        }

        public int getRepresentante(){
            return representante;
        }
        public void setRepresentante(int r){
            representante = r;
        }
        public void setNext(NodoDS n){
            next.add(n);
        }
        public void getNext(int pos){
            return next.get(pos);
        }


    }
    private NodoDS[] cjtos;

    public EDDisjointSetCH(){

    }

    public void makeSet(int n){
        NodoDS nodo = new NodoDS(n);
    }

    public int findSet(NodoDS n){
        return n.getRepresentante();
        //CompresionDeCaminos
    }

    public void union(NodoDS x, NodoDS y){
        //Union por Rank
    }
}