package Arboles;

public class BTNode<E> implements BTPosition<E> {
 protected BTPosition<E> parent;
 protected BTPosition<E> left;
 protected BTPosition<E> right;
 protected E elem;
 public BTNode(E el){
	 elem = el;
 }
 /**
  * devuelve el elemento asociado a la posicion
  * @return el elemento guardado en la posicion
  */
	public E element(){
		return elem;
	}
	/**
	 * modifica el elemento pasado por parametro en la posicion
	 * @param elemento E
	 */
	public void  setElement(E el){
		elem = el;
	}
	/**devuelve el hijo izquierdo del Nodo
	 * @return la posicion del hijo izquierdo del Nodo Padre
	 */
	public BTPosition<E> getLeft(){
		return left;
	}
	/**modifica el hijo Izquierdo del Nodo Padre
	 * @param v nuevo hijo izquierdo del Nodo Padre
	 */
	public void setLeft(BTPosition<E> v){
		left = v;
	}
	/**
	 * devuelve el hijo derecho del Nodo Padre
	 * @return  la posicion del hijo derecho del nodo Padre
	 */
	public BTPosition<E> getRight(){
		return right;
	}
	/**
	 * modifica el hijo derecho del Nodo Padre
	 * @param v nuevo hijo derecho del padre
	 */
	public void setRight(BTPosition<E> v){
		right =  v;
	}
	/**
	 * devuelve el nodo padre del nodo pedido
	 * @return la posición del padre del nodo en cuestion
	 */
	public BTPosition<E> getParent(){
		return parent;
	}
	/**
	 * modifica el padre de la posicion dada
	 * @param v la posicion del nuevo padre del nodo en cuestión
	 */
	public void setParent(BTPosition<E> v){
		parent = v;
	}
}
