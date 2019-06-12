package Graphs;

public class SolucionGen<A> {

	protected A costo;
	
	public SolucionGen(){
		costo = null;
	}
	
	public A getCosto(){
		return costo;
	}
	public void setCosto(A Cost){
		costo = Cost;
	}
}
