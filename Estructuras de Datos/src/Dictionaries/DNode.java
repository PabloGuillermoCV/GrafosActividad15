package Dictionaries;

public class DNode<E> implements Position<E> {
  /*Conocido como Nodo Doble o Nodo doblemente Enlazado, esta clase genera un Nodo
   * 	que ademas de guardar un elemento genérico, conoce quien lo procede y antecede, lo usaremos en Listas Doblemente Enlazadas
   */
  protected E element;
  protected DNode<E> prev;
  protected DNode<E> next;
  public DNode(){
	  //crea un Nodo Doble vacio
	  element = null;
	  prev = null;
	  next = null;
  }
  public DNode (E el, DNode<E> ant, DNode<E> sig){
	  //crea un Nodo Doble con todos los campos llenos
	  element = el;
	  prev = ant;
	  next = sig;
  }
  //Setters
  public void setElement(E el){
	  element = el;
  }
  public void setNext(DNode<E> sig){
	  next = sig;
  }
  public void setPrev(DNode<E> pre){
	  prev = pre;
  }
  //Getters
  public E element(){
	  return element;
  }
  public DNode<E> getNext(){
	  return next;
  }
  public DNode<E> getPrev(){
	  return prev;
  }
}
