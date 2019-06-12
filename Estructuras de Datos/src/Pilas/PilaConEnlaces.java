package Pilas;
import Colas.Node;

public class PilaConEnlaces<E> implements Stack<E> {
  protected Node<E> top;
  protected int size;
  public PilaConEnlaces(){
	  top = null;
	  size = 0;
  }
	 public void push(E elemento){
		 Node <E> aux = new Node<E>(elemento,top);
		 top = aux;
		 size++;
	 }
	 public E pop() throws EmptyStackException{
		 E aux = null;
		 if(size == 0){
			 throw new EmptyStackException("Pila Vacia");
		 }
		 else{
			 aux = top.element();
			 top = top.getNext();
			 size--;
		 }
		 return aux;
	 }
	 public int size(){
		 return size;
	 }
	 public boolean isEmpty(){
		 return size == 0;
	 }
	 public E top() throws EmptyStackException{
		 if(size == 0)
			 throw new EmptyStackException("Pila Vacia");
		 else
			 return top.element();
	 }
	 public Node<E> getTop(){
		 return top;
	 }
}
