package Arboles;

import java.util.Iterator;

import Pilas.PilaConEnlaces;
import Pilas.Stack;


public class LinkedTree<E> implements Tree<E> {
	protected int size;
	protected TNode<E> Root;
	
	
	/**
	 * Constructor de la clase Arbol Enlazado, crea un Arbol vacio
	 */
	public LinkedTree(){
		size = 0;
		Root = null;
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
		if(!isEmpty()) 
			pre(L,Root);
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
		if(Root == null)
			throw new EmptyTreeException("Arbol vacio");
		else
			return Root;
	}
	
	/**
	 * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		if(isRoot(v))
			throw new BoundaryViolationException("Nodo pasado es la Raiz, no tiene padre");
		else{
		TNode<E> aux = checkPosition(v);
		return aux.getFather();
		}
	}
	
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		PositionList<Position<E>> ret = new ListaDoblementeEnlazada<Position<E>>();
		for(Position<E> p : aux.getCh()){
			ret.addLast(p);
		}
		return ret;
	}
	
	/**
	 * Consulta si una posición corresponde a un nodo interno.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return aux.getCh().isEmpty() == false;
	}
	
	/**
	 * Consulta si una posición dada corresponde a un nodo externo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		TNode<E> aux = checkPosition(v);
		return aux.getCh().isEmpty() == true;
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
	
	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param E Rótulo que se asignará a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
	public void createRoot(E e) throws InvalidOperationException{
		if(Root != null)
			throw new InvalidOperationException("Arbol ya tiene Raiz");
		else{
			TNode<E> p = new TNode<E> (e);
			Root = p;
		}
	}
	
	/**
	 * Agrega un nodo con rótulo e como primer hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param padre Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws	InvalidPositionException{
		TNode<E> aux = checkPosition(p);
		TNode<E> ret = new TNode<E>(e);
		aux.getCh().addFirst(ret);
		return ret;
	}
	
	/**
	 * Agrega un nodo con rótulo e como útimo hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException{
		TNode<E> aux = checkPosition(p);
		TNode<E> ret = new TNode<E>(e);
		aux.getCh().addLast(ret);
		return ret;
	}
	
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará delante de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param rb Posición del nodo que será el hermano derecho del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException{
		TNode<E> padre = checkPosition(p);
		TNode<E> hijo = checkPosition(rb);
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNode<E> add = new TNode<E> (e);
		PositionList<TNode<E>> chi = padre.getCh();
		try{
			Position<TNode<E>> it = chi.first();
			boolean encontre = false;
			while(it != null && !encontre){
				if(it.element() == hijo){
					encontre = true;
					chi.addBefore(it, add);
				}
				if(it != chi.last())
					it = chi.next(it);
				else
					it = null;
			}
			if(it == null)
				throw new InvalidPositionException("rb NO era hijo de p");
		}
		catch(EmptyListException t){System.out.println("Lista de hijos vacia");}
		catch(BoundaryViolationException t){System.out.println("Posicion fuera de rango");
		}
		return add;
	}
	
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará a continuación de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param lb Posición del nodo que será el hermano izquierdo del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException{
		TNode<E> padre = checkPosition(p);
		TNode<E> hijo = checkPosition(lb);
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNode<E> add = new TNode<E> (e);
		PositionList<TNode<E>> chi = padre.getCh();
		try{
			Position<TNode<E>> it = chi.first();
			boolean encontre = false;
			while(it != null && !encontre){
				if(it.element() == hijo){
					encontre = true;
					chi.addAfter(it, add); //delego en addAfter de listas
				}
				if(it != chi.last()) //itero manualmente porque tengo que hacer el positions()
					it = chi.next(it);
				else
					it = null;
			}
			if(it == null)
				throw new InvalidPositionException("rb NO era hijo de p");
		}
		catch(EmptyListException t){System.out.println("Lista de hijos vacia");}
		catch(BoundaryViolationException t){System.out.println("Posicion fuera de rango");
		}
		return add;
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo externo. 
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo externo, o el árbol está vacío.
	 */
	public void removeExternalNode (Position<E> p) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		else{
			if(!isExternal(p))
				throw new InvalidPositionException("Posicion NO es hoja");
			else{
				TNode<E> aux = checkPosition(p);
				aux.getFather().getCh().remove((Position<TNode<E>>) p); //dudo de esto
				aux.setFather(null);
			}
		}
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol,  únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo interno o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeInternalNode (Position<E> p) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		else{
			if(!isInternal(p))
				throw new InvalidPositionException("La posicion no corresponde a un Nodo Interno");
			else{
				TNode<E> aux = checkPosition(p);
				if(isRoot(p) && aux.getCh().size() > 1)
					throw new InvalidPositionException("El Nodo entrante es la Raiz y tiene más de un hijo, no puedo eliminar esto");
				else{
					if(!isRoot(p)){
						try{
							PositionList<TNode<E>> c = aux.getCh();
							Position<TNode<E>> it = c.first();
							TNode<E> f = c.first().element();
							TNode<E> pp = aux.getFather();
							aux.setFather(null);
							pp.getCh().remove((Position<TNode<E>>) p);
							c.remove(it);
							pp.getCh().addFirst(f);
							f.setFather(pp);
						}
						catch(EmptyListException e){System.out.println("Lista vacia");}}
						else{ //p es la raiz
							try{
								Position<TNode<E>> it = aux.getCh().first();
								TNode<E> f = it.element();
								f.setFather(null);
								aux.getCh().remove(it);
							}
							catch(EmptyListException e){System.out.println("lista Vacia");}
						}
				}
			}
		}
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada. Si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol,  únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeNode (Position<E> p) throws InvalidPositionException{
		if(isInternal(p))
			removeInternalNode(p);
		else
			removeExternalNode(p);
	}
	
	/**
	 * metodo auxiliar para desenmascarar las posiciones como Nodos de Arbol
	 * @param v posicion a desenmascarar
	 * @return Nodo de Arbol
	 * @throws InvalidPositionException si la posición dada Es vacia o nula, o si fallo el casteo
	 */
	private TNode<E> checkPosition(Position<E> v) throws InvalidPositionException{
		if(v == null){
			throw new InvalidPositionException("Posicion vacia");
		}
		else
			try{
			return (TNode<E>) v;
			}
		catch(ClassCastException e){ throw new InvalidPositionException("Posición no era un Nodo de Arbol");}
	}
	/**
	 * metodo auxiliar para clonar arboles
	 * @return un clon del arbol
	 */
	public LinkedTree<E> clone(){
		LinkedTree<E> ret = new LinkedTree<E>();
		if(!isEmpty()){
			try{
				ret.createRoot(Root.element());
				recorrerYCopiar(ret,Root,ret.root());
			}
			catch(EmptyTreeException e){System.out.println("Arbol vacio");}
			catch(InvalidOperationException e){System.out.println("Error en la operacion con el arbol");}
		}
		return ret;
	}
	/**
	 * metodo recursivo auxiliar que recorre el arbol en preorden e inserta las posiciones en el arbol clon
	 * @param clon arbol en donde se insertaran las posiciones
	 * @param posThis posicion actual del recorrido del arbol original
	 * @param posClon posicion actual del recorrido del arbol clon
	 */
	private void recorrerYCopiar(LinkedTree<E> clon, TNode<E> posThis, Position<E> posClon){
		try{
			for(TNode<E> child : posThis.getCh()){
				Position<E> nueva = clon.addLastChild(posClon, child.element());
				recorrerYCopiar(clon,child,nueva);
			}
		}
		catch(InvalidPositionException e){System.out.println("Posicion Invalida");}	
	} //para clon espejado, cambiar en la linea 381 "addLastChild()" por "addFirstChild()". 
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
	/**
	 * calcula la profundidad de un nodo dado en el arbol
	 * @param v Posicion del nodo en cuestion
	 * @return la profundidad del nodo en el arbol
	 */
	public int depth(Position<E> v){
		int dep = 0;
		try{
		if(isRoot(v))
			dep = 0;
		else
			dep = 1 + depth(parent(v));
		}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
		return dep;
	}
	/**
	 * calcula la altura de un nodo dado en el arbol
	 * @param v la posicion del nodo en cuestion
	 * @return la altura del nodo v en el arbol
	 */
	public int height(Position<E> v){
		int h = 0;
		try{
			if(isExternal(v))
				h = 0;
			else{
				for(Position<E> w : children(v))
					h = Math.max(h,  height(w));
			}
		}
		catch(InvalidPositionException e){}
		return h + 1;
	}
	/**
	 * Devuelve una oracion con los elementos del arbol visitados en PreOrden
	 * @param v la posición del nodo donde comienza el recorrido
	 * @return un String con los nodos visitados en PreOrden
	 */
	public String toStringPreorder(Position<E> v){
		String s = v.element().toString();
		try{
			for(Position<E> w : children(v))
				s += "," + toStringPreorder(w);
			}
		catch(InvalidPositionException e){}
		return s;
	}
	
	public void ej8(E el, int h){
		try{
			preorderej8(this, root(),h,el);
		}
		catch(EmptyTreeException e){}
	}
	public void ej9(E el){
		try{
			preorderj9(this,this.root(),el);
		}
		catch(EmptyTreeException e){}
	}
	private void preorderej8(LinkedTree<E> a, Position<E> v, int j, E el){
		try{
			for(Position<E> w : a.children(v)){
				if(a.height(v) == j){
					a.addLastChild(v,el);
				}
				preorderej8(a,w,j,el);
			}
		}
		catch(InvalidPositionException e){}
	}

	private void preorderj9(LinkedTree<E> a, Position<E> v, E el){
		try{
			for(Position<E> w : a.children(v)){
				if(a.isInternal(w) && w.element().equals(el)){
					TNode<E> aux = checkPosition(w);
					PositionList<TNode<E>> l = aux.getCh();
					invertir(l);
				}
				preorderj9(a,w,el);
			}
		}
		catch(InvalidPositionException e){}
	}
	private void invertir(PositionList<TNode<E>> k){
		try{
			int s = k.size();
			for(int i = 0; i < s; i++){
				k.addLast(k.first().element());
				k.remove(k.first());
			}
		}
		catch(EmptyListException e){} 
		catch (InvalidPositionException e) {e.printStackTrace();}
	}
}
	
