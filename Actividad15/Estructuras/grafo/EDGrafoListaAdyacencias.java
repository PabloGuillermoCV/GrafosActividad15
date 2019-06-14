package grafo;

import java.util.ArrayList;
import grafoJSON.Grafo;
import grafoJSON.Pesado;



public class EDGrafoListaAdyacencias{
  
    private ArrayList<ArcoED> arcos;
    private ArrayList<Nodo> nodos;

    public EDGrafoListaAdyacencias(Grafo g){
        arcos = new ArrayList<ArcoED>();
        nodos = new ArrayList<Nodo>();
        int[] nodosEntrada = g.getNodos();
        ArrayList<Pesado> arcosEntrada = g.getArcos();

        
        //Para cada nodo en el grafo pasado como entrada, lo paso a mi arreglo de nodos, insertandolo en el grafo
        for(int i = 0; i < g.getNodosCount(); i++){
            this.insertarVertice(nodosEntrada[i]);
        }
        //para cada Arco del grafo pasado como entrada, lo paso a mi arraylist de arcos, insertandolo en el grafo, cuidando la correspondencia
        //con los nodos inicio y fin
        for(Pesado p : arcosEntrada){
            Nodo n1 = nodos.get(nodos.indexOf(p.getArco().getNodoSource())); 
            Nodo n2 = nodos.get(nodos.indexOf(p.getArco().getNodoTarget()));
            int peso = p.getPeso();
            this.insertarArco(n1, n2, peso);
        }
    }

    public ArrayList<ArcoED> getArcos(){
        return arcos;
    }

    public ArrayList<Nodo> getNodos(){
        return nodos;
    }

    public ArrayList<ArcoED> incidentes(Nodo n){
        return n.getAdyacentes();
    }

    public Nodo getOpuesto(Nodo n, ArcoED a){
        if(a.getTarget().equals(n)){
            return a.getSource();
        }
        else{
            return a.getTarget();
        }
    }

    public Nodo[] endVertices(ArcoED a){
        Nodo[] nodos = new Nodo[2];
        nodos[0] = a.getSource();
        nodos[1] = a.getTarget();
        return nodos;
    }

    public boolean sonAdyacentes(Nodo a, Nodo b){
        boolean is = false;
        ArrayList<ArcoED> arcos = a.getAdyacentes();
        for(ArcoED e: arcos){
            if(e.getSource() == b || e.getTarget() == b){
                is = true;
                break;
            }
        }
        return is;
    }

    public void insertarVertice(int rot){
        Nodo n = new Nodo(rot);
        nodos.add(n);
        n.setPosEnNodos(nodos.indexOf(n));
    }

    public void insertarArco(Nodo s, Nodo t, int peso){
        ArcoED a = new ArcoED(peso,s,t);
        arcos.add(a);
        s.getAdyacentes().add(a);
        t.getAdyacentes().add(a);
        a.setPosArcos(arcos.indexOf(a));
        a.setPosArcoPredecesor(s.getAdyacentes().indexOf(a));
        a.setPosArcoSucesor(t.getAdyacentes().indexOf(a));
    }

}