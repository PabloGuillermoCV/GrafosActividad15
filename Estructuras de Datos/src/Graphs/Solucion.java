package Graphs;

public class Solucion {

	float costo;
	public Solucion(float c){
		costo = c;
	}
	public void setCosto(float c){
		costo = c;
	}
	public float getCosto(){
		return costo;
	}
	public boolean menor(Solucion comp){
		return costo < comp.getCosto(); 
	}
}
