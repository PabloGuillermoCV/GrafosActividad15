package grafo;
import java.util.ArrayList;

public class Nodo{
    
    private int rotulo;
    private ArrayList<ArcoED> adyacentes;
    private int posEnListaNodos;
    private String color;

    public Nodo(int r){
        rotulo = r;
        adyacentes = new ArrayList<ArcoED>();
    }
    
    public String getColor(){
        return color;
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

    public void setColor(String color){
        this.color = color;
    }
    
    public void setPosEnNodos(int pos){
        posEnListaNodos = pos;
    }
}
