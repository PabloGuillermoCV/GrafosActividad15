package Dictionaries;

public interface BTPosition<E> extends Position<E> {
  /**
   * devuelve el elemento asociado a la posicion
   * @return el elemento guardado en la posicion
   */
	public E element();
	/**
	 * modifica el elemento pasado por parametro en la posicion
	 * @param elemento E
	 */
	public void  setElement(E el);
	/**devuelve el hijo izquierdo del Nodo
	 * @return la posicion del hijo izquierdo del Nodo Padre
	 */
	public BTPosition<E> getLeft();
	/**modifica el hijo Izquierdo del Nodo Padre
	 * @param v nuevo hijo izquierdo del Nodo Padre
	 */
	public void setLeft(BTPosition<E> v);
	/**
	 * devuelve el hijo derecho del Nodo Padre
	 * @return  la posicion del hijo derecho del nodo Padre
	 */
	public BTPosition<E> getRight();
	/**
	 * modifica el hijo derecho del Nodo Padre
	 * @param v nuevo hijo derecho del padre
	 */
	public void setRight(BTPosition<E> v);
	/**
	 * devuelve el nodo padre del nodo pedido
	 * @return la posición del padre del nodo en cuestion
	 */
	public BTPosition<E> getParent();
	/**
	 * modifica el padre de la posicion dada
	 * @param v la posicion del nuevo padre del nodo en cuestión
	 */
	public void setParent(BTPosition<E> v);
}
