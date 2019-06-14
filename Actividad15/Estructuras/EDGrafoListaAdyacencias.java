
import java.util.ArrayList;

public class EDGrafoListaAdyacencias{
    /* 
       TODO: implementar Nodo y Arco
    */
    private ArrayList<Arco> arcos;
    private ArrayList<Nodo> nodos;

    private class Arco{
        private Nodo source;
        private Nodo target;
        private int peso;
        private String color;
        private int posArcos,posArcoSucesor,posArcoPredecesor;
        
        public Arco(int p, Nodo s, Nodo t){
            peso = p;
            source = s;
            target = t;
        }

        public int getPeso(){
            return peso;
        }

        public Nodo getSource(){
            return source;
        }

        public Nodo getTarget(){
            return target;
        }

        public String getColor(){
            return color;
        }

        public int getPosArcos(){
            return posArcos;
        }
        
        public int getPosArcoSucesor(){
            return posArcoSucesor;
        }

        public int getPosArcoPredecesor(){
            return posArcoPredecesor;
        }

        public void setColor(String color){
            this.color = color;
        }

        public void setPosArcos(int pos){
            posArcos = pos;
        }

        public void setPosArcoSucesor(int pos){
            posArcoSucesor = pos;
        }

        public void setPosArcoPredecesor(int pos){
            posArcoPredecesor = pos;
        }

    }

    private class Nodo{
        private int rotulo;
        private ArrayList<Arco> adyacentes;
        private int posEnListaNodos;

        public Nodo(int r){
            rotulo = r;
            adyacentes = new ArrayList<Arco>();
        }

        public int getRotulo(){
            return rotulo;
        }

        public int getPosEnNodos(){
            return posEnListaNodos;
        }

        public ArrayList<Arco> getAdyacentes(){
            return adyacentes;
        }

        public void setPosEnNodos(int pos){
            posEnListaNodos = pos;
        }
    }

    public EDGrafoListaAdyacencias(Grafo g){
        arcos = new ArrayList<Arco>();
        nodos = new ArraList<Nodo>();
        //TODO: hacer la traducción aquí!
    }

    public ArrayList<Arco> getArcos(){
        return arcos;
    }

    public ArrayList<Nodo> getNodos(){
        return nodos;
    }

    public ArrayList<Arco> incidentes(Nodo n){
        return n.getAdyacentes();
    }

}