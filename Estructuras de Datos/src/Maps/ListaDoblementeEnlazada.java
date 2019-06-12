package Maps;
import java.util.Iterator;

public class ListaDoblementeEnlazada<E> implements PositionList<E>{
	protected DNode<E> cabeza;
	protected DNode<E> cola;
	protected int cant;
	public ListaDoblementeEnlazada(){
		//creo una lista vacia, con Cabeza y Cola siendo los Nodos "Dummy"
		cabeza = new DNode<E>();
		cola = new DNode<E>();
		cabeza.setNext(cola);
		cola.setPrev(cabeza);
	}
	//Metodos comunes
	public int size(){
		return cant;
	}
	public boolean isEmpty(){
		return cant == 0;
	}
	//Metodos de lectura,iteracion
	public Position<E> first() throws EmptyListException{
		if(cant == 0)
			throw new EmptyListException("Lista Vacia, agregue algo");
		return cabeza.getNext();
	}
	public Position<E> last() throws EmptyListException{
		if(cant == 0)
			throw new EmptyListException("Lista Vacia");
		return cola.getPrev();
	}
	public Position<E> next(Position<E> p) throws InvalidPositionException,BoundaryViolationException{
		DNode<E> aux = checkPosition(p);
		if(aux.element == null && aux != cabeza)
			throw new BoundaryViolationException ("No se puede pedir el siguiente de la ultima posicion");
		return aux.getNext();
	}
	public Position<E> prev(Position<E> p) throws InvalidPositionException,BoundaryViolationException{
		DNode<E> aux = checkPosition(p);
		if(aux.element() == null && aux != cola)
			throw new BoundaryViolationException("No se puede pedir el elemento previo al primer elemento");
		return aux.getPrev();
	}
	//Metodos de Modificacion
	public void addFirst(E element){
		DNode<E> aux = new DNode<E>();
		aux.setElement(element);
		aux.setNext(cabeza.getNext());
		cabeza.getNext().setPrev(aux);
		cabeza.setNext(aux);
		aux.setPrev(cabeza);
		cant++;
	}
	public void addLast(E element){
		DNode<E> aux = new DNode<E>();
		aux.setElement(element);
		cola.getPrev().setNext(aux);
		aux.setPrev(cola.getPrev());
		cola.setPrev(aux);
		aux.setNext(cola);
		cant++;
	}
	public void addBefore(Position<E> p, E element) throws InvalidPositionException{
		DNode<E> toAdd = new DNode<E>();
		DNode<E> aux = checkPosition(p);
		toAdd.setElement(element);
		aux.getPrev().setNext(toAdd);
		aux.setPrev(toAdd);
		toAdd.setNext(aux);
		toAdd.setPrev(aux.getPrev());
		cant++;
	}
	public void addAfter(Position<E> p, E element) throws InvalidPositionException{
		DNode<E> toAdd = new DNode<E>();
		toAdd.setElement(element);
		DNode<E> aux = checkPosition(p);
		aux.getNext().setPrev(toAdd);
		aux.setNext(toAdd);
		toAdd.setPrev(aux);
		cant++;
	}
	public E remove(Position<E> p) throws InvalidPositionException{
		E toRet;
		if(p == null)
			throw new InvalidPositionException("No se puede elminar una posicion vacia");
		else{
			if( p == cabeza.getNext()){
				DNode<E> N = cabeza.getNext();
				toRet = N.getPrev().element();
				N.setPrev(cabeza);
				cabeza.setNext(N);
			}
			else{
				DNode<E> aux = checkPosition(p); 
				toRet = aux.element();
				aux.getPrev().setNext(aux.getNext());
				aux.getNext().setPrev(aux.getNext());
			}
		}
		cant--;
		return toRet;
	}
	public E set(Position<E> p, E element) throws InvalidPositionException{
		E toRet;
		if(p == null)
			throw new InvalidPositionException("No se puede modificar una posicion Vacia");
		else{
			DNode<E> aux = checkPosition(p);
			toRet = aux.element();
			aux.setElement(element);
		}
		return toRet;
	}
	//metodos de Iterator
	public Iterator<E> iterator(){
		return new ElementIterator<E> (this);
	}
	public String toString(){
		String ret = "[ ";
		try{
		Position<E> p1 = first();
			while(p1 != null){
				ret += p1.element();
				if(p1 == last()){
					p1 = null;
					ret += " ]";
				}
				else
					p1 = next(p1);
			}
		}
		catch(EmptyListException e){ System.out.println("Lista Vacia");}
		catch(BoundaryViolationException e){ System.out.println("Posicion no valida");}
		catch(InvalidPositionException e){System.out.println("Nos caimos de la Lista");}
		return ret;
	}
	public PositionList<Position<E>> positions(){
		PositionList<Position<E>> ret = new ListaDoblementeEnlazada<Position<E>>();
		try{
			Position<E> it = first();
			while(it != null){
				ret.addLast(it);
				if(it != last())
					it = next(it);
				else
					it = null;
			}
		}
		catch(EmptyListException e){System.out.println("Lista Vacia");}
		catch(InvalidPositionException e){System.out.println("Posicion no valida");}
		catch(BoundaryViolationException e){System.out.println("Posicion fuera de rango");}
		return ret;
	}
	//Método auxiliar para desenmascarar Position como DNode
	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
				try {
					  if( p == null ) throw new InvalidPositionException( "Pos nula" );
					  DNode<E> aux = (DNode<E>) p;
					    if(aux.getNext() == null || aux.getPrev() == null)
					    	throw new InvalidPositionException("Se brindó el Dummy, esto es incorrecto");
					    else
					    	return aux;
					  } catch( ClassCastException e ) {
						  throw new InvalidPositionException( "Posicion invalida" );
					  }
	}
	

}
