package Listas;
import java.util.Iterator;

import Colas.Node;

public class ListaSimplementeEnlazada<E> implements PositionList<E> {
	protected Node<E> cabeza;
	private int cant;
	/* InvalidPositionException == Posición NO válida en la Lista
	 * BoundaryViolationException == "Si hago esto, me caigo de la Lista"
	 */
	public ListaSimplementeEnlazada(){
		//crea una lista vacia
		cabeza = null;
		cant = 0;
	}
	public int size(){
		//devuelve la cantidad de elementos de la lista
		return cant;
	}
	 public boolean isEmpty(){
		 //devuelve si la lista esta vacia o no
		 return cant == 0;
	 }
	 public Position<E> first() throws EmptyListException{
		 //devuelve la PRIMERA POSICION de la lista
		 if(cabeza == null)
			 throw new EmptyListException("Lista Vacia");
		 return (Position<E>) cabeza;
	 }
	 public Position<E> last() throws EmptyListException{
		 //devuelve la ULTIMA POSICION de a Lista
		 if(isEmpty())
			 throw new EmptyListException("Lista Vacia");
		 else{
			 Node<E> p = cabeza;
			 while(p.getNext() != null){
				 p = p.getNext();
			 }
			 return (Position<E>) p;
		 }
	 }
	 public Position<E> prev( Position<E> p )throws InvalidPositionException, BoundaryViolationException{
		 //Devuelve la POSICION PREVIA a la POSICION dada
		 if( p == cabeza)
			 throw new BoundaryViolationException("No existe elemento previo al primero");
		 Node<E> i = cabeza;
		 while(!(i.getNext() != checkPosition(p)) && i.getNext() != null){
			 i = i.getNext();
		 }
		 if( i.getNext() == null)
			 throw new InvalidPositionException("La posicion dada NO pertenece a la Lista");
		 return (Position<E>) i;
	 }
	 public Position<E> next( Position<E> p )throws InvalidPositionException, BoundaryViolationException {
		//devuelve la POSICION SIGUINETE a la POSICION dada	 
		 Node<E> n = checkPosition(p);
			 if( n.getNext() == null )
			throw new BoundaryViolationException( "No existe posicion siguiente a la posicion nula" );
			 return (Position<E>) n.getNext(); 
	 }
	 public void addFirst(E e){ //similar a Enqueue() de Colas
		 //Añade el elemento e como primer elemento de la lista
		 cabeza = new Node<E>(e, cabeza);
		 cant++;
	 }
	 public void addLast(E e){
		//agrega el elemento e al final de la lista (Como ultimo elemento)
		 if(isEmpty())
			addFirst(e);
		else{
			Node<E> i = cabeza;
			Node<E> a = new Node<E>(e,null);
			while(i.getNext() != null){
				i = i.getNext();
			}
			i.setNext(a);
			cant++;
		}
	 }
	 public void addAfter( Position<E> p, E e ) throws InvalidPositionException{
		 //añade delante de P un NODO de Rótulo e
		 Node<E> n = checkPosition(p);
		 Node<E> nuevo = new Node<E>(e,null);
		 nuevo.setNext(n.getNext());
		 n.setNext(nuevo);
		 cant++;
	 }
	 public void addBefore( Position<E> p, E e )throws InvalidPositionException{
		 //añade el elemento e ANTES DE LA POSICION P
		 Node<E>n = checkPosition(p);
		 try{ //Metí try/Catch porque Eclipse se ponia pesado :v
			 if(p == first())
				 addFirst(e);
		 			else{
		 				Node<E> ant = (Node<E>) prev(p);
		 				Node<E> nuevo = new Node<E> (e,n);
		 				ant.setNext(nuevo);
		 				cant++;
		 	}
		 }
		 catch(EmptyListException t){ System.out.println("La lista estaba Vacia");}
		 catch(BoundaryViolationException t){ System.out.println("Se intentó acceder a una posición fuera de la Lista, o que es de otra lista");}
	 }
	 public E remove( Position<E> p ) throws InvalidPositionException{
		 //Borra la POSICION de la lista  y devuelve el elemento asociado a la posicion eliminada (se debe contraer la lista una vez hecha la eliminación)
		 /* existen dos casos para el Remove:
		  * 1) Caso general (p es una posicion en el medio o al final de la lista, ahi removemos sin miramientos)
		  * 2) Caso particular: p es la primera posicion de la lista, ahi eliminamos el primero, pero debemos poner al siguiente del que era el primero como nueva cabeza de la lista*/
		 E aux = p.element();
		 try{ //Lo mismo acá, Eclipse se puso pesado :v
			 if(p == first()){
				 cabeza = checkPosition(p).getNext();
			 }
			 	else{
			 		Node<E> ant = checkPosition(prev(p));
			 		ant.setNext(checkPosition(p).getNext());
			 	}
		 }
		 catch(BoundaryViolationException e){System.out.println("Se intentó acceder a una posicion fuera de la lista, o de otra lista");}
		 catch(EmptyListException e){System.out.print("La lista estaba vacia");}
		 cant--;
		 return aux;
	 }
	 public E set(Position<E> p, E e ) throws InvalidPositionException{
		 //Reemplaza el elemento de la posicion p y devuelve el elemento que estaba antes del reemplazo
		 E aux = p.element();
		 checkPosition(p).setElement(e);
		 return aux;
	 }
	 public Iterator<E> iterator(){
		 return new ElementIterator<E>(this);
	 }
	 private Node<E> checkPosition( Position<E> p )throws InvalidPositionException {
		//metodo auxiliar para desenmascarar una posicion como un Nodo, ya que la Lista utiliza Nodos como forma de posicionamiento y guardado de elementos	  
		 
		 try { //uso try/catch ya que la posición dada puede ser un Nodo, como puede ser CUALQUIER OTRA COSA, hacer este Cast es peligroso
			  if( p == null ) throw new InvalidPositionException( "Pos nula" );
			  return (Node<E>) p; //esto es algo llamado "DownCast", Casteo algo de tipo genérico a tipo Nodo
			  } catch( ClassCastException e ) {
			 throw new InvalidPositionException( "Posicion invalida" );
			  }
		 /*Existe un error con este método que NO vamos a arreglar,
		  *  el cual ocurre si el cliente tiene dos listas del mismo ADT y llama a un método de una con una posicion de la otra
		  *   el método en si no reportará error, pero lo que ocurrirá es que devolverá la posición de la Lista de donde se sacó el parámetro
		  *    (Ver ejemplo en cuaderno y/o diapositiva)
		  *  Si yo llamo a este checkPosition con un parametro de un ADT distinto (si llamo con un Nodo Doble, por ejemplo)
		  *   el método reportará error ya que fallará el casting
		  */
			 } 
}
