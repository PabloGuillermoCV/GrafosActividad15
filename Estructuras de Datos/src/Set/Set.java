package Set;

import java.util.Iterator;

public interface Set<E> {

	/**
	 * inserta un elemento nuevo en el conjunto
	 * @param x elemento a insertar
	 */
	public void insert(E x);
	
	/**
	 * elimina un elemento del conjunto
	 * @param x elemento a eliminar
	 */
	public void remove(E x);
	
	/**
	 * determina si un elemento pertenece al conjunto
	 * @param x elemento a considerar
	 * @return true si x pertenece, false en caso contrario
	 */
	public boolean member(E x);
	
	/**
	 * intersecta el conjunto con otro conjunto pasado por parámetro
	 * @param S conjunto con el que intersectar
	 * @return interseccion de ambos conjuntos
	 */
	public Set<E> intersect(Set<E> S);
	
	/**
	 * Une al conjunto con otro conjunto pasado por parámetro
	 * @param S conjunto con el que unir
	 * @return union de ambos conjuntos
	 */
	public Set<E> union(Set<E> S);
	
	/**
	 * calcula el complemento del conjunto (el dominio debe ser finito)
	 * @return el complemento del conjunto
	 */
	public Set<E> complement();
	
	/**
	 * crea un iteraodrm del conjunto
	 * @return iterador para el conjunto
	 */
	public Iterator<E> iterator();
}
