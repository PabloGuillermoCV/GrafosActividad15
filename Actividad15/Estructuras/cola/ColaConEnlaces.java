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
	 /**
	  * Dado un elemento, lo encola en la ED Cola
	  */
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
	 /**
	  * Saca un elemento de la cola, eliminandolo en el proceso
	  */
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
	 /**
	  * devuelve el tamaño actual de la cola, en elementos
	  */
	 public int size(){
		 return size;
	 }
	 /**
	  * devuelve el elemento al frente de la cola, sin eliminarlo
	  */
	 public T front() throws EmptyQueueException{
		 T aux = null;
		 if(size == 0)
			 throw new EmptyQueueException("Cola Vacia");
		 else{
			 aux = head.element();
		 }
		return aux;	 
	 }
	 /**
	  * Verifica si la cola esta vacia
	  */
	 public boolean isEmpty(){
		 return size == 0;
	 }
	 
	 
	}