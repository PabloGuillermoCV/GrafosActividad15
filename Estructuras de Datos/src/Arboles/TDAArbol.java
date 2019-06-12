package Arboles;

import java.util.Iterator;

public class TDAArbol<E> implements GTTree<E> {
	//variables de Instancia
	protected TNode<E> Root;
	protected int size;
	//constructor
	public TDAArbol(TNode<E> r){
		Root = r;
		size = 1;
	}
	
	/**
	 * Consulta la cantidad de nodos en el �rbol.
	 * @return Cantidad de nodos en el �rbol.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Consulta si el �rbol est� vac�o.
	 * @return Verdadero si el �rbol est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el �rbol en preorden.
	 * @return Iterador de los elementos almacenados en el �rbol.
	 */
	public Iterator<E> iterator(){
		PositionList<E> L = new ListaDoblementeEnlazada<E>();
		for(Position<E> p : positions())
			L.addLast(p.element());
		return L.iterator();
	}
	
	/**
	 * Devuelve una colecci�n iterable de las posiciones de los nodos del �rbol.
	 * @return Colecci�n iterable de las posiciones de los nodos del �rbol.
	 */
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> L = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty()) pre(L,Root);
		return L;
	}
	
	/**
	 * Reemplaza el elemento almacenado en la posici�n dada por el elemento pasado por par�metro. Devuelve el elemento reemplazado.
	 * @param v Posici�n de un nodo.
	 * @param e Elemento a reemplazar en la posici�n pasada por par�metro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		E ret = aux.element();
		aux.setElement(e);
		return ret;
	}
	
	/**
	 * Devuelve la posici�n de la ra�z del �rbol.
	 * @return Posici�n de la ra�z del �rbol.
	 * @throws EmptyTreeException si el �rbol est� vac�o.
	 */
	public Position<E> root() throws EmptyTreeException{
		if(isEmpty())
			throw new EmptyTreeException("Arbol Vacio");
		else return Root;
	}
	
	/**
	 * Devuelve la posici�n del nodo padre del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del nodo padre del nodo correspondiente a la posici�n dada.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde a la ra�z del �rbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		TNode<E> aux = checkPosition(v);
		if(isRoot(v))
			throw new BoundaryViolationException("El Nodo Padre no tiene Padre");
		else
			return aux.getFather();
	}
	
	/**
	 * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return (Iterable<Position<E>>) aux.getCh().iterator();
	}
	
	/**
	 * Consulta si una posici�n corresponde a un nodo interno.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return aux.getCh().isEmpty();
	}
	
	/**
	 * Consulta si una posici�n dada corresponde a un nodo externo.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return aux.getCh().isEmpty();
	}
	
	/**
	 * Consulta si una posici�n dada corresponde a la ra�z del �rbol.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero, si la posici�n pasada por par�metro corresponde a la ra�z del �rbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return aux == Root;
	}
	private TNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		TNode<E> ret = null;
		if(p.element() == null || p == null)
			throw new InvalidPositionException("Posicion invalida");
		else
			try{
				ret = (TNode<E>) p;
				return ret;
			}
		catch(ClassCastException e){System.out.println("Fall� el Casteo");
	}
		return ret;
	}
	/**
	 * Devuelve los nodos del arbol organizados seg�n el metodo "PreOrden"
	 * @param l Lista almacenadora de los nodos
	 * @param p Nodo donde debe comenzar el recorrido
	 */
	private void pre(PositionList<Position<E>> l, TNode<E> c){
		l.addLast(c);
		for(TNode<E> h : c.getCh())
			pre(l,h);
	}
	private void Pos(PositionList<Position<E>>l, TNode<E> c){
		for(TNode<E> h: c.getCh()){
			Pos(l, h);
		}
		l.addLast(c);
	}
	/**
	 * metodo auxiliar de recorrido "Por Niveles"
	 */
	private String RPN(){
		Queue<Position<E>> Cola = new ColaConEnlaces<Position<E>>();
		String ret = "";
		try{
			Cola.enqueue(root());
			Cola.enqueue(null); //para salto de Linea
			while(!Cola.isEmpty()){
				Position<E> v = Cola.dequeue();
				if(v != null){
					ret += v.element() + " ";
					for(Position<E> W: children(v)){
						Cola.enqueue(W);
					}
				}
				else{
					ret +=  "\n";
					if(!Cola.isEmpty()){
						Cola.enqueue(null);
					}
				}
			}
		}
		catch(EmptyTreeException e){}
		catch(FullQueueException e){}
		catch(EmptyQueueException e){}
		catch(InvalidPositionException e){}
		return ret;
	}
}
