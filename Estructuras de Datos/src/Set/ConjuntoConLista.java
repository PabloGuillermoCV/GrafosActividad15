package Set;
import java.util.Iterator;

import Listas.*;
import Maps.Comparator;
import Maps.InvalidKeyException;
import Maps.MapeoABB;

public class ConjuntoConLista<E> implements Set<E> {

	protected PositionList<E> conjunto;
	
	public ConjuntoConLista(){
		conjunto = new ListaDoblementeEnlazada<E>();
	}
	
	/**
	 * inserta un elemento nuevo en el conjunto
	 * @param x elemento a insertar
	 */
	public void insert(E x){
		conjunto.addFirst(x);
	}
	
	/**
	 * elimina un elemento del conjunto
	 * @param x elemento a eliminar
	 */
	public void remove(E x){
		boolean found = false;
		try{
			Position<E> it = conjunto.first();
			while(it != null && !found){
				if(it.element() == x){
					found = true;
					conjunto.remove(it);
					it = conjunto.last();
				}
				if(it == conjunto.last())
					it = null;
				else
					it = conjunto.next(it);
			}
		}
		catch(EmptyListException e){}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
	}
	
	/**
	 * determina si un elemento pertenece al conjunto
	 * @param x elemento a considerar
	 * @return true si x pertenece, false en caso contrario
	 */
	public boolean member(E x){
		boolean found = false;
		Iterator<E> it = conjunto.iterator();
		while(it.hasNext() && !found){
			if(it.next() == x)
				found = true;
		}
		return found;
	}
	
	/**
	 * intersecta el conjunto con otro conjunto pasado por parámetro
	 * @param S conjunto con el que intersectar
	 * @return interseccion de ambos conjuntos
	 */
	public Set<E> intersect(Set<E> S){
		Set<E> ret = new ConjuntoConLista<E>();
		for(E e: conjunto){
			if(S.member(e))
				ret.insert(e);
		}
		return ret;
	}
	
	/**
	 * Une al conjunto con otro conjunto pasado por parámetro
	 * @param S conjunto con el que unir
	 * @return union de ambos conjuntos
	 */
	public Set<E> union(Set<E> S){
		Set<E> ret = new ConjuntoConLista<E>();
		MapeoABB<E,Boolean> aux = new MapeoABB<E,Boolean>(new Comparator<E>());
		for(E e: conjunto){
			ret.insert(e);
			try{
				aux.put(e, true);
			}
			catch(InvalidKeyException t){}
		}
		Iterator<E> it = S.iterator();
		while(it.hasNext()){
			E in = it.next();
			try{
				if(!aux.get(in))
					ret.insert(in);
			}
			catch(InvalidKeyException t){}
		}
		return ret;
	}
	
	/**
	 * calcula el complemento del conjunto (el dominio debe ser finito)
	 * @return el complemento del conjunto
	 */
	public Set<E> complement(){
		return null;
	}
	
	/**
	 * crea un iteraodrm del conjunto
	 * @return iterador para el conjunto
	 */
	public Iterator<E> iterator(){
		return conjunto.iterator();
	}
}
