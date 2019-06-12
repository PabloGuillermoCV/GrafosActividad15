package Arboles;

import java.util.Iterator;

public class ArbolBinario<E> implements BinaryTree<E> {
	protected BTNode<E> root;
	protected int size;
	
	/**
	 * Constructor
	 */
	public ArbolBinario(){
		root = null;
		size = 0;
	}
	/**
	 * Devuelve la posición del hijo izquierdo de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo izquierdo de v.
	 * @throws InvalidPositionException si la posición pasada por paráetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo izquierdo.
	 */
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		BTNode<E> n = checkPosition(v);
		if (n.getLeft() == null) throw new BoundaryViolationException("V no posee hijo izquierdo");
		else{
			return n.getLeft();
		}
	}
	/**
	 * Devuelve la posición del hijo derecho de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo derecho de v.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo derecho.
	 */
	public Position<E> right(Position<E> v)throws InvalidPositionException, BoundaryViolationException{
		BTNode<E> n = checkPosition(v);
		if (n.getRight() == null) throw new BoundaryViolationException("v no posee hijo derecho");
		else{
			return n.getRight();
		}
	}
	/**
	 * Testea si v tiene un hijo izquierdo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo izquierdo y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasLeft(Position<E> v) throws InvalidPositionException{
		BTNode<E> n = checkPosition(v);
		return n.getLeft() != null;
	}	
	/**
	 * Testea si v tiene un hijo derecho.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo derecho y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasRight(Position<E> v) throws InvalidPositionException{
		BTNode<E> n = checkPosition(v);
		return n.getRight() != null;		
	}	
	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param E Rótulo que se asignaría a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
	public Position<E> createRoot(E r) throws InvalidOperationException{
		BTNode<E> n;
		if (root != null) throw new InvalidOperationException("El arbol ya posee una raiz");
		else{
			n = new BTNode<E>(r);
			root =  n;
		}
		return n;
	}	
	/**
	 * Agrega un nodo con rótulo r como hijo izquierdo de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo izquierdo.
	 */
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException{
		BTNode<E> aux;
		BTNode<E> n = checkPosition(v);
		if (n.getLeft() != null) throw new InvalidOperationException("V ya tiene hijo izquierdo");
		else{
			aux = new BTNode<E>(r);
			n.setLeft(aux);
		}
		return aux;
	}
	/**
	 * Agrega un nodo con rótulo r como hijo derecho de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo derecho.
	 */
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException{
		BTNode<E> aux;
		BTNode<E> n = checkPosition(v);
		if (n.getRight() != null) throw new InvalidOperationException("v ya tiene hijo derecho");
		else{
			aux = new BTNode<E>(r);
			n.setRight(aux);
		}
		return aux;
	}
	/**
	 * Elimina el nodo referenciado por una posición dada. Si el nodo tiene un único hijo, el nodo eliminado sería reemplazado por su único hijo.
	 * @param v Posición del nodo a eliminar.
	 * @return el rótulo del nodo eliminado.
     * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si el nodo a eliminar tiene mas de un hijo.
     */
	public E remove(Position<E> v) throws InvalidPositionException, InvalidOperationException{
		BTNode<E> aux, lc, rc;
		aux = checkPosition(v);
		lc =(BTNode<E>) aux.getLeft();
		rc =(BTNode<E>) aux.getRight();
		if (lc != null && rc != null) throw new InvalidOperationException("EL nodo teine mas de un hijo");
		BTNode<E> aux4;
		if (lc != null) aux4 = lc;
		else if( rc != null) aux4 = rc;
			else aux4 = null;
		if (aux == root){
			if(aux4 != null)
				aux4.setParent(null);
			root = aux4;
		}
		else{
			BTNode<E> n =(BTNode<E>) aux.getParent();
			if (aux == n.getLeft())
				n.setLeft(aux4);
			else
				n.setRight(aux4);
			if (aux4 != null)
				aux4.setParent(n);
		}
		size--;
		return aux.element();
	}
	/**
	 * Inserta a los árboles T1 y T2 como subárboles hijos de la hoja v (izquierdo y derecho respectivamente).
	 * @param v Posición de una hoja del árbol.
	 * @param T1 árbol binario a insertar como hijo izquierdo de v.
	 * @param T2 árbol binario a insertar como hijo derecho de v. 
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío, o v no corresponde a una hoja.
	   Pone a T1 y a T2 como subárboles de la hoja v, izquierdo y derecho respectivamente, si v no era hoja da InvalidPositionException.
	 */
	public void Attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException{
		  BTNode<E> n = checkPosition(r);
		  if (isInternal(n))
			  throw new InvalidPositionException("Posicion invalida");
		  if (!T1.isEmpty()){
			  try{
				  BTNode<E> r1 = checkPosition(T1.root());
				  n.setLeft(r1);
				  r1.setParent(n);
			  }
			  catch (EmptyTreeException e)
			  {
				  System.out.println(e.getMessage());
			  }
		  }
		  if (!T2.isEmpty()){
			  try{
				  BTNode<E> r2 = checkPosition(T2.root());
				  n.setRight(r2);
				  r2.setParent(n);
			  }
			  catch(EmptyTreeException e){
				  System.out.println(e.getMessage());
			  }
		  }
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
		  Iterable<Position<E>> l = positions();
		  PositionList<E> elements = new ListaDoblementeEnlazada<E>();
		  for (Position<E> pos: l)
		  {
			  elements.addLast(pos.element());
		  }
		  return elements.iterator();
	  }	
	  /**
	   * Devuelve una colección iterable de las posiciones de los nodos del árbol.
	   * @return Colección iterable de las posiciones de los nodos del árbol.
	   */
	public Iterable<Position<E>> positions(){
		  PositionList<Position<E>> L = new ListaDoblementeEnlazada<Position<E>>();
		  if(!isEmpty()) preorderPositions(root, L);
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
			BTNode<E> n = checkPosition(v);
			E temp = n.element();
			n.setElement(e);
			return temp;
	  }
	  /**
	   * Devuelve la posición de la raíz del árbol.
	   * @return Posición de la raíz del árbol.
	   * @throws EmptyTreeException si el árbol está vacío.
	   */
	public Position<E> root() throws EmptyTreeException{
		  return root;
	  }
	  /**
	   * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	   * @param v Posición de un nodo.
	   * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	   * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	   * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	   */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
			BTNode<E> n = checkPosition(v);
			if (n == root) throw new BoundaryViolationException("Posicion fuera de Rango");
			return n.getParent();
	  }
	  /**
	   * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	   * @param v Posición de un nodo.
	   * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	   * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	   */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		  PositionList<Position<E>> l = new ListaDoblementeEnlazada<Position<E>>();
		  BTNode<E> n = checkPosition(v);
		  l.addLast(n.getRight());
		  l.addLast(n.getLeft());
		  return l;
	  }
	  /**
	   * Consulta si una posición corresponde a un nodo interno.
	   * @param v Posición de un nodo.
	   * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	   * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	   */
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		  BTNode<E> n = checkPosition(v);
		  return n.getLeft() != null || n.getRight() != null;
	  }
	  /**
	   * Consulta si una posición dada corresponde a un nodo externo.
	   * @param v Posición de un nodo.
	   * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	   * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	   */
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		  BTNode<E> n = checkPosition(v);
		  return n.getLeft() == null && n.getRight() == null;
	  }
	  /**
	   * Consulta si una posición dada corresponde a la raíz del árbol.
	   * @param v Posición de un nodo.
	   * @return Verdadero, si la posición pasada por parámetro corresponde a la raíz del árbol,falso en caso contrario.
	   * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	   */
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		  BTNode<E> n =(BTNode<E>) checkPosition(v);
		  return root == n;
	  }
	/**
	 * @param c posisicion a chequear
	 * @return Nodo de arbol binario de c
	 * @throws InvalidPositionException si la posición es nula o no tiene elemento adentro
	 */
	private BTNode<E> checkPosition(Position<E> c) throws InvalidPositionException{
		  if(c == null || c.element() == null)
			  throw new InvalidPositionException("Posicion invalida");
		  else
			  return (BTNode<E>) c;
	  }
	  /**
	   * Devuelve los nodos del arbol organizados según el metodo "PreOrden"
	   * @param l Lista almacenadora de los nodos
	   * @param p Nodo donde debe comenzar el recorrido
	   */
	private void preorderPositions(Position<E> v, PositionList<Position<E>> l){
			l.addLast(v);
			try{
			if (hasLeft(v))
				preorderPositions(left(v), l);
			if (hasRight(v))
				preorderPositions(right(v), l);
			}
			catch (InvalidPositionException e){
				System.out.println(e.getMessage());
			}
			catch (BoundaryViolationException e){
				System.out.println(e.getMessage());
			}
		}
}
