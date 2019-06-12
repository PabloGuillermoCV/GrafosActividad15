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
	 * Devuelve la posici�n del hijo izquierdo de v.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del hijo izquierdo de v.
	 * @throws InvalidPositionException si la posici�n pasada por par�etro es inv�lida.
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
	 * Devuelve la posici�n del hijo derecho de v.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del hijo derecho de v.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
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
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si v tiene un hijo izquierdo y falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.	
	 */
	public boolean hasLeft(Position<E> v) throws InvalidPositionException{
		BTNode<E> n = checkPosition(v);
		return n.getLeft() != null;
	}	
	/**
	 * Testea si v tiene un hijo derecho.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si v tiene un hijo derecho y falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.	
	 */
	public boolean hasRight(Position<E> v) throws InvalidPositionException{
		BTNode<E> n = checkPosition(v);
		return n.getRight() != null;		
	}	
	/**
	 * Crea un nodo con r�tulo e como ra�z del �rbol.
	 * @param E R�tulo que se asignar�a a la ra�z del �rbol.
	 * @throws InvalidOperationException si el �rbol ya tiene un nodo ra�z.
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
	 * Agrega un nodo con r�tulo r como hijo izquierdo de un nodo dado.
	 * @param r R�tulo del nuevo nodo.
	 * @param v Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
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
	 * Agrega un nodo con r�tulo r como hijo derecho de un nodo dado.
	 * @param r R�tulo del nuevo nodo.
	 * @param v Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
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
	 * Elimina el nodo referenciado por una posici�n dada. Si el nodo tiene un �nico hijo, el nodo eliminado ser�a reemplazado por su �nico hijo.
	 * @param v Posici�n del nodo a eliminar.
	 * @return el r�tulo del nodo eliminado.
     * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
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
	 * Inserta a los �rboles T1 y T2 como sub�rboles hijos de la hoja v (izquierdo y derecho respectivamente).
	 * @param v Posici�n de una hoja del �rbol.
	 * @param T1 �rbol binario a insertar como hijo izquierdo de v.
	 * @param T2 �rbol binario a insertar como hijo derecho de v. 
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o, o v no corresponde a una hoja.
	   Pone a T1 y a T2 como sub�rboles de la hoja v, izquierdo y derecho respectivamente, si v no era hoja da InvalidPositionException.
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
		  Iterable<Position<E>> l = positions();
		  PositionList<E> elements = new ListaDoblementeEnlazada<E>();
		  for (Position<E> pos: l)
		  {
			  elements.addLast(pos.element());
		  }
		  return elements.iterator();
	  }	
	  /**
	   * Devuelve una colecci�n iterable de las posiciones de los nodos del �rbol.
	   * @return Colecci�n iterable de las posiciones de los nodos del �rbol.
	   */
	public Iterable<Position<E>> positions(){
		  PositionList<Position<E>> L = new ListaDoblementeEnlazada<Position<E>>();
		  if(!isEmpty()) preorderPositions(root, L);
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
			BTNode<E> n = checkPosition(v);
			E temp = n.element();
			n.setElement(e);
			return temp;
	  }
	  /**
	   * Devuelve la posici�n de la ra�z del �rbol.
	   * @return Posici�n de la ra�z del �rbol.
	   * @throws EmptyTreeException si el �rbol est� vac�o.
	   */
	public Position<E> root() throws EmptyTreeException{
		  return root;
	  }
	  /**
	   * Devuelve la posici�n del nodo padre del nodo correspondiente a una posici�n dada.
	   * @param v Posici�n de un nodo.
	   * @return Posici�n del nodo padre del nodo correspondiente a la posici�n dada.
	   * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	   * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde a la ra�z del �rbol.
	   */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
			BTNode<E> n = checkPosition(v);
			if (n == root) throw new BoundaryViolationException("Posicion fuera de Rango");
			return n.getParent();
	  }
	  /**
	   * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n dada.
	   * @param v Posici�n de un nodo.
	   * @return Colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por par�metro.
	   * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	   */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		  PositionList<Position<E>> l = new ListaDoblementeEnlazada<Position<E>>();
		  BTNode<E> n = checkPosition(v);
		  l.addLast(n.getRight());
		  l.addLast(n.getLeft());
		  return l;
	  }
	  /**
	   * Consulta si una posici�n corresponde a un nodo interno.
	   * @param v Posici�n de un nodo.
	   * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo interno, falso en caso contrario.
	   * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	   */
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		  BTNode<E> n = checkPosition(v);
		  return n.getLeft() != null || n.getRight() != null;
	  }
	  /**
	   * Consulta si una posici�n dada corresponde a un nodo externo.
	   * @param v Posici�n de un nodo.
	   * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo externo, falso en caso contrario.
	   * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	   */
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		  BTNode<E> n = checkPosition(v);
		  return n.getLeft() == null && n.getRight() == null;
	  }
	  /**
	   * Consulta si una posici�n dada corresponde a la ra�z del �rbol.
	   * @param v Posici�n de un nodo.
	   * @return Verdadero, si la posici�n pasada por par�metro corresponde a la ra�z del �rbol,falso en caso contrario.
	   * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	   */
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		  BTNode<E> n =(BTNode<E>) checkPosition(v);
		  return root == n;
	  }
	/**
	 * @param c posisicion a chequear
	 * @return Nodo de arbol binario de c
	 * @throws InvalidPositionException si la posici�n es nula o no tiene elemento adentro
	 */
	private BTNode<E> checkPosition(Position<E> c) throws InvalidPositionException{
		  if(c == null || c.element() == null)
			  throw new InvalidPositionException("Posicion invalida");
		  else
			  return (BTNode<E>) c;
	  }
	  /**
	   * Devuelve los nodos del arbol organizados seg�n el metodo "PreOrden"
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
