package grafo;
import java.util.ArrayList;

public class Nodo{
    
    private int rotulo;
    private ArrayList<ArcoED> adyacentes;
    private int posEnListaNodos;
    private int posEnDS;
    private String color;
    private Nodo PadreDS;
    private int rango = 0;

    public Nodo(int r){
        rotulo = r;
        adyacentes = new ArrayList<ArcoED>();
        color = "blanco";
    }
    
    public int getRango() {
    	return rango;
    }
    
    public Nodo getPadre() {
    	return PadreDS;
    }
    
    public int getPosEnDS() {
    	return posEnDS;
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
    
    public void setPosEnDS(int n) {
    	posEnDS = n;
    }
    
    public void setRango(int n) {
    	rango = n;
    }
    
    public void setPadre(Nodo p) {
    	PadreDS = p;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public void setPosEnNodos(int pos){
        posEnListaNodos = pos;
    }
}
