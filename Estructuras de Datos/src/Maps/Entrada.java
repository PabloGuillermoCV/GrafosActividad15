package Maps;

public class Entrada<K,V> implements Maps.Entry<K,V> {
	  protected K Key; //Usado por el comparador
	  protected V Value; //Cosa que guardo, "Etiqueta"
	  public Entrada(K k, V v){
		  Key = k;
		  Value = v;
	  }
	  //setters
	  /**
	   * modifica la componenete Key de la Entrada
	   * @param k la nueva clave
	   */
	  public void setKey(K k){
		  Key = k;
	  }
	  /**
	   * modifica la componente Value de la Entrada
	   * @param v el nuevo value
	   */
	  public void setValue(V v){
		  Value = v;
	  }
	  //getters
	  /**
	   * devuelve la clave de la Entrada
	   * @return la clave de la Entrada
	   */
	  public K getKey(){
		  return Key;
	  }
	  /**
	   * devuelve el Value de la Entrada
	   * @return el Value de la Entrada 
	   */
	  public V getValue(){
		  return Value;
	  }
	  public String toString(){
		  return ""+ Key + " " + Value;
	  }
	  
	}
