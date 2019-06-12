package Dictionaries;

public class Nodo23<K,V> {
	protected Dictionary<K,V> Entradas;
	protected Nodo23<K,V> Padre;
	protected Nodo23<K,V>[] hijos;
	
	public Nodo23(Nodo23<K,V> p){
		Entradas = new DiccionarioConHashAbierto<K,V>(2);
		Padre = p;
		hijos = (Nodo23<K,V>[]) new Object[4];
		hijos[0] = new Nodo23<K,V>(this);
		hijos[2] = new Nodo23<K,V>(this);
	}
	//setters
	public void addEntry(K key, V value){
		try{
			Entradas.insert(key, value);
			if(Entradas.size() == 2)
				hijos[1] = new Nodo23<K,V>(this);
			if(Entradas.size() == 3)
				hijos[3] = new Nodo23<K,V>(this);
		}
		catch(InvalidKeyException r){}
	}
	public void setFather(Nodo23<K,V> P){
		Padre = P;
	}
	
	//getters
	
	public Nodo23<K,V> getHijo(int index){
		return hijos[index];
	}
	public Nodo23<K,V>[] getAll(){
		return hijos;
	}
	public Dictionary<K,V> getEntradas(){
		return Entradas;
	}
	public Nodo23<K,V> getPadre(){
		return Padre;
	}
}
