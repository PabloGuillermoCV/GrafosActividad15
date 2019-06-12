package Dictionaries;

public class AVLTree<K,V> extends BinarySearchTree<K,V> {

	public AVLTree(Comparator<K> c){
		super(c);
	}
	private static class AVLNode<K,V> extends BTNode<Entry<K,V>>{
		private int height;
		public AVLNode(Entry<K,V> element, BTPosition<Entry<K,V>> parent,BTPosition<Entry<K,V>> left, BTPosition<Entry<K,V>> right){
			super(element,parent,left,right);
			height = 0;
			if(left != null)
				height = Math.max(height,  1 + ((AVLNode<K,V>) left).getHeight());
			if(right != null)
				height = Math.max(height,  1 + ((AVLNode<K,V>) right).getHeight());
		}
		public void setHeight(int h){height = h;}
		public int getHeight(){return height;}
		
	}
	
	protected BTPosition<Entry<K,V>> createNode(Entry<K,V> element, BTPosition<Entry<K,V>> parent,BTPosition<Entry<K,V>> left, BTPosition<Entry<K,V>> right){
		return new AVLNode<K,V>(element,parent,left,right);
	}
	protected int height(Position<Entry<K,V>> p){
		return ((AVLNode<K,V>) p).getHeight();
	}
	protected void setHeight(Position<Entry<K,V>>p){
		try{
			((AVLNode<K,V>)p).setHeight(1+Math.max(height(left(p)), height(right(p))));
		}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
	}
	
	protected boolean isBalanced(Position<Entry<K,V>> p){
		int bf = 0;
		try{
			 bf = (height(left(p)) - height(right(p)));
		}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
		return  -1 <= bf && bf <= 1;
	}
	
	protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>>p){
		Position<Entry<K,V>> ret = null;
		try{
			if(height(left(p)) > height(right(p))) ret = left(p);
			else if(height(left(p)) < height(right(p))) ret = right(p);
			 // IF equal heights
			if(isRoot(p)) ret = left(p);
			else ret = right(p);
		}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
		return ret;
	}
	
	protected void rebalance(Position<Entry<K,V>> zPos){
		try{
			if(isInternal(zPos))
				setHeight(zPos);
			while(!isRoot(zPos)){
				zPos = parent(zPos);
				setHeight(zPos);
				if(!isBalanced(zPos)){
					Position<Entry<K,V>> yPos = tallerChild(zPos);
					Position<Entry<K,V>> xPos = tallerChild(tallerChild(zPos));
					zPos = restructure(xPos,yPos,zPos);
					setHeight(left(zPos));
					setHeight(right(zPos));
					setHeight(zPos);
				}
			}
		}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
	}
	
	@SuppressWarnings("unchecked")
	private Position<Entry<K,V>> restructure(Position<Entry<K,V>> xPos,Position<Entry<K,V>>yPos,Position<Entry<K,V>>zPos){
		//le tengo miedo a los casteos que estoy haciendo... Tampoco estoy muy seguro sobre la parte donde dice "Redundante?"
		BTPosition<Entry<K,V>> a;
		BTPosition<Entry<K,V>> b;
		BTPosition<Entry<K,V>> c;
		BTPosition<Entry<K,V>> T0;
		BTPosition<Entry<K,V>> T1;
		BTPosition<Entry<K,V>> T2;
		BTPosition<Entry<K,V>> T3;
		//determino que rotacion hay que hacer
		try{
			//yPos es HI
			if(left(zPos) == yPos){
				if(left(yPos) == xPos){
					//rotacion simple Izquierda
					a = (BTPosition<Entry<K,V>>)(xPos);
					b = (BTPosition<Entry<K,V>>)yPos;
					c = (BTPosition<Entry<K,V>>)zPos;
					T0 = (BTPosition<Entry<K,V>>)left(a);
					T1 = (BTPosition<Entry<K,V>>)right(a);
					T2 = (BTPosition<Entry<K,V>>)right(b);
					T3 = (BTPosition<Entry<K,V>>)right(c);
					T2.setParent(c);
					b.setParent(c.getParent());
					c.getParent().setLeft(b);
					c.setParent(b);
					b.setRight(c);
					c.setLeft(T2);
					zPos = c; //Redundante?
				}
				else{
					//rotacion doble izquierda, xPos es HD de yPos
					a = (BTPosition<Entry<K,V>>)(yPos);
					c = (BTPosition<Entry<K,V>>)(zPos);
					b = (BTPosition<Entry<K,V>>)(xPos);
					T0 = (BTPosition<Entry<K,V>>)left(a);
					T3 = (BTPosition<Entry<K,V>>)right(c);
					T1 = (BTPosition<Entry<K,V>>)left(b);
					T2 = (BTPosition<Entry<K,V>>)right(b);
					c.getParent().setLeft(b);
					c.setParent(b);
					b.setRight(c);
					c.setLeft(T2);
					T2.setParent(c);
					b.setLeft(a);
					a.setParent(b);
					a.setRight(T1);
					T1.setParent(a);
					zPos = c; //Redundante?
				}
			}
			//yPos es HD
			else{
				if(right(yPos) == xPos){
					//rotacion simple derecha
					a = (BTPosition<Entry<K,V>>)(zPos);
					b = (BTPosition<Entry<K,V>>)(yPos);
					c = (BTPosition<Entry<K,V>>)(xPos);
					T0 = (BTPosition<Entry<K,V>>)(left(a));
					T1 = (BTPosition<Entry<K,V>>)(left(b));
					T2 = (BTPosition<Entry<K,V>>)(left(c));
					T3 = (BTPosition<Entry<K,V>>)(right(c));
					a.getParent().setRight(b);
					b.setParent(a.getParent());
					a.setParent(b);
					b.setLeft(a);
					a.setLeft(T1);
					T1.setParent(a);
					zPos = a; //Redundante?
				}
				else{
					//rotacion doble derecha, xPos es HI de yPos
					a = (BTPosition<Entry<K,V>>)(zPos);
					b = (BTPosition<Entry<K,V>>)(xPos);
					c = (BTPosition<Entry<K,V>>)(zPos);
					T0 = (BTPosition<Entry<K,V>>)(left(a));
					T1 = (BTPosition<Entry<K,V>>)(left(b));
					T2 = (BTPosition<Entry<K,V>>)(right(b));
					T3 = (BTPosition<Entry<K,V>>)(right(c));
					a.getParent().setRight(b);
					b.setParent(a.getParent());
					a.setParent(b);
					b.setLeft(a);
					a.setRight(T1);
					T1.setParent(a);
					b.setRight(c);
					c.setParent(b);
					c.setLeft(T2);
					T2.setParent(c);
					zPos = a; //Redundante?
				}
			}
		}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
		catch(ClassCastException e){}
		return zPos; //devuelvo la posición modificada de Z para que "rebalance" recalcule las alturas
	}
}
