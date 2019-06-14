package Ejercicio1a;

public class Node<E> implements Position<E> {
private E element;
private Node<E> next;

public Node(){
	
	element = null;
	next = null;
}
public Node(E e, Node<E> n){
	element = e;
	next = n;
}

public E element(){
	return element;
}
public Node<E> getNext(){
	return next;
}

public void setElement(E e){
	element = e;
}
public void setNext(Node<E> newNext){
	next = newNext;
}
}