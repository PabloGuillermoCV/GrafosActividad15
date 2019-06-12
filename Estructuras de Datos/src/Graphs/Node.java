package Graphs;


public class Node<E> implements Position<E> {
private E element;
private Node<E> next;
//constructores
public Node(){
	//crea un nodo vacio
	element = null;
	next = null;
}
public Node(E e, Node<E> n){
	element = e;
	next = n;
}
//consultas
public E element(){
	return element;
}
public Node<E> getNext(){
	return next;
}
//comandos
public void setElement(E e){
	element = e;
}
public void setNext(Node<E> newNext){
	next = newNext;
}
}
