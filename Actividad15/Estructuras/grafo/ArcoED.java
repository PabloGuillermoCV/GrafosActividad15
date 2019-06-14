package grafo;

public class ArcoED{
    private Nodo source;
    private Nodo target;
    private int peso;
    
    private int posArcos,posArcoSucesor,posArcoPredecesor;
    
    public ArcoED(int p, Nodo s, Nodo t){
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

  

    public int getPosArcos(){
        return posArcos;
    }
    
    public int getPosArcoSucesor(){
        return posArcoSucesor;
    }

    public int getPosArcoPredecesor(){
        return posArcoPredecesor;
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