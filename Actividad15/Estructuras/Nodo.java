import java.util.ArrayList;

public class Nodo{
    
    private int rotulo;
    private ArrayList<ArcoED> adyacentes;
    private int posEnListaNodos;

    public Nodo(int r){
        rotulo = r;
        adyacentes = new ArrayList<ArcoED>();
    }

    public int getRotulo(){
        return rotulo;
    }

    public int getPosEnNodos(){
        return posEnListaNodos;
    }

    public ArrayList<ArcoED> getAdyacentes(){
        return adyacentes;
    }

    public void setPosEnNodos(int pos){
        posEnListaNodos = pos;
    }
}
