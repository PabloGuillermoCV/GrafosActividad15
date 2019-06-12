package ColasConPrioridad;
import Listas.*;

public class EntradaPosicionada<K,V> extends Entrada<K,V> implements Entry<K,V> {

	private Position<Entry<K,V>> pos;
	public EntradaPosicionada(K k, V v){
		super(k,v);
	}
	
	//setters
	public void setPos(Position<Entry<K,V>> p){
		pos = p;
	}
	//getters
	public Position<Entry<K,V>> getPosicion(){
		return pos;
	}
}
