package Maps;

public class NodoABB<K,V> {
	private K rot;
	private V value; //value principal
	private NodoABB<K,V> Padre;
	private NodoABB<K,V> HijoI;
	private NodoABB<K,V> HijoD;
	public NodoABB(K key, V value, NodoABB<K,V>P, NodoABB<K,V>I, NodoABB<K,V>D){
		rot = key;
		this.value = value;
		Padre = P;
		HijoI = I;
		HijoD = D;
	}
	public NodoABB(NodoABB<K,V> P){
		rot = null;
		value = null;
		Padre = P;
		HijoI = null;
		HijoD = null;
	}
	public NodoABB(){
		rot = null;
		value = null;
		Padre = null;
		HijoI = null;
		HijoD = null;
	}
	public K getKey(){
		return rot;
	}
	public V getValue(){
		return value;
	}
	public NodoABB<K,V> getPadre(){
		return Padre;
	}
	public NodoABB<K,V> getIz(){
		return HijoI;
	}
	public NodoABB<K,V> getDer(){
		return HijoD;
	}
	public void setKey(K key){
		rot = key;
	}
	public void setValue(V value){
		this.value = value;
	}
	public void setPadre(NodoABB<K,V> P){
		Padre = P;
	}
	public void setHijoI(NodoABB<K,V> I){
		HijoI = I;
	}
	public void setHijoD(NodoABB<K,V> D){
		HijoD = D;
	}
	
}
