package Tries;

public class NodoTrie<E> {
	protected E imagen;
	protected NodoTrie<E> [] hijos;
	protected NodoTrie<E> padre;
	public NodoTrie(NodoTrie<E> p) {
		hijos = new NodoTrie[26];
		imagen = null; 
		padre = p; 
	}
	public void setImagen(E imagen){ 
		this.imagen = imagen; 
	}
	public E getImagen(){ 
		return imagen; 
	}
	public void setHijo(int i, NodoTrie<E> hijo ){ 
		hijos[i] = hijo; 
	}
	public NodoTrie<E> getHijo(int i){ 
		return hijos[i]; 
	}
	public void setPadre( NodoTrie<E> padre ){ 
		this.padre = padre; 
	}
	public NodoTrie<E> getPadre(){ 
		return padre; 
	}
}
