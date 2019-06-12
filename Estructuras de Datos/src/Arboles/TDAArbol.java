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
	 * Consulta la cantidad de nodos en el árbol.
	 * @return Cantidad de nodos en el árbol.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Consulta si el árbol está vacío.
	 * @return Verdadero si el árbol está vacío, falso en caso contrario.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el árbol en preorden.
	 * @return Iterador de los elementos almacenados en el árbol.
	 */
	public Iterator<E> iterator(){
		PositionList<E> L = new ListaDoblementeEnlazada<E>();
		for(Position<E> p : positions())
			L.addLast(p.element());
		return L.iterator();
	}
	
	/**
	 * Devuelve una colección iterable de las posiciones de los nodos del árbol.
	 * @return Colección iterable de las posiciones de los nodos del árbol.
	 */
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> L = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty()) pre(L,Root);
		return L;
	}
	
	/**
	 * Reemplaza el elemento almacenado en la posición dada por el elemento pasado por parámetro. Devuelve el elemento reemplazado.
	 * @param v Posición de un nodo.
	 * @param e Elemento a reemplazar en la posición pasada por parámetro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		E ret = aux.element();
		aux.setElement(e);
		return ret;
	}
	
	/**
	 * Devuelve la posición de la raíz del árbol.
	 * @return Posición de la raíz del árbol.
	 * @throws EmptyTreeException si el árbol está vacío.
	 */
	public Position<E> root() throws EmptyTreeException{
		if(isEmpty())
			throw new EmptyTreeException("Arbol Vacio");
		else return Root;
	}
	
	/**
	 * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		TNode<E> aux = checkPosition(v);
		if(isRoot(v))
			throw new BoundaryViolationException("El Nodo Padre no tiene Padre");
		else
			return aux.getFather();
	}
	
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return (Iterable<Position<E>>) aux.getCh().iterator();
	}
	
	/**
	 * Consulta si una posición corresponde a un nodo interno.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return aux.getCh().isEmpty();
	}
	
	/**
	 * Consulta si una posición dada corresponde a un nodo externo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return aux.getCh().isEmpty();
	}
	
	/**
	 * Consulta si una posición dada corresponde a la raíz del árbol.
	 * @param v Posición de un nodo.
	 * @return Verdadero, si la posición pasada por parámetro corresponde a la raíz del árbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
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
		catch(ClassCastException e){System.out.println("Falló el Casteo");
	}
		return ret;
	}
	/**
	 * Devuelve los nodos del arbol organizados según el metodo "PreOrden"
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
