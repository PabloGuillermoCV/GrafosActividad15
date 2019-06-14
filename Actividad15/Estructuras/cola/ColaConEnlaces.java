package cola;

public class ColaConEnlaces<T> implements Queue<T>{
	 protected Node<T> head;
	 protected Node<T> tail;
	 protected int size;
	 
	 public ColaConEnlaces(){
		 head = new Node<T>();
		 tail = new Node<T>();
		 size = 0;
	 }
	 public void enqueue(T e){
		Node<T> aux = new Node<T>();
		aux.setElement(e);
		aux.setNext(null);
		 if(size == 0){
			 head = aux;
		 }
		 else{
			 tail.setNext(aux);
		 }
		 tail = aux;
		 size++;
	 }
	 public T dequeue() throws EmptyQueueException{
		 T aux = null;
		 if (size == 0)
			 throw new EmptyQueueException("Cola Vacia");
		 aux = head.element();
		 head = head.getNext();
		 size--;
		 if(size == 0)
			 tail = null;
		 return aux;
	 }
	 public int size(){
		 return size;
	 }
	 public T front() throws EmptyQueueException{
		 T aux = null;
		 if(size == 0)
			 throw new EmptyQueueException("Cola Vacia");
		 else{
			 aux = head.element();
		 }
		return aux;	 
	 }
	 public boolean isEmpty(){
		 return size == 0;
	 }
	 
	 
	}