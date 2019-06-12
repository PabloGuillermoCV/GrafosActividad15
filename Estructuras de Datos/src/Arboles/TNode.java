package Arboles;

public class TNode<E> implements Position<E> {
 //variables de instancia
	protected E elem;
	protected TNode<E> Father;
	protected PositionList<TNode<E>> ch;
	//Constructor
	public TNode(E el){
		elem = el;
		Father = null;
		ch = null;
	}
	//setters
	public void setElement(E el){
		elem = el;
	}
	public void setFather(TNode<E> P){
		Father = P;
	}
	public void setChildren(PositionList<TNode<E>> c){
		ch = c;
	}
	
	//getters
	public E element(){
		return elem;
	}
	public TNode<E> getFather(){
		return Father;
	}
	public PositionList<TNode<E>> getCh(){
		return ch;
	}
}
