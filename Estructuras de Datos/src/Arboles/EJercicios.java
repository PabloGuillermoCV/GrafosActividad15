package Arboles;

public class EJercicios {

	public static<E> PositionList<Position<E>> ejA(Tree<E> A){
		PositionList<Position<E>> ret = new ListaDoblementeEnlazada<Position<E>>();
		try{
			Position<E> p = A.root();
			preEsp(A,ret,p);
		}
		catch(EmptyTreeException e){}
		return ret;
	}
	private  static<E> void preEsp(Tree<E> a, PositionList<Position<E>> t, Position<E> ini){
		try{
			for(Position<E> w : a.children(ini)){
				if(!a.isExternal(w))
					t.addLast(w);
				preEsp(a,t,w);
			}
		}
		catch(InvalidPositionException e){}
	}
	
	public static<E> boolean esAVL(BinaryTree<E> T){
		boolean es = false;
		try{
			if(!T.isEmpty())
				es = aux(T,T.root());
		}
		catch(EmptyTreeException e){}
		return es;
	}
	
	private static<E> boolean aux(BinaryTree<E> T, Position<E> PA){
		boolean is = true;
		try{
			if(T.isExternal(PA))
				is = true;
			else{
				if(T.hasLeft(PA) && T.hasRight(PA)){
					if(Math.abs(height(T,T.left(PA)) - height(T,T.right(PA)) ) >= 1)
							is = false;
					else{
						is = aux(T,T.left(PA)) && aux(T,T.right(PA));
					}
				
				}
				else{
					if(T.hasLeft(PA)){
						if(height(T,T.left(PA)) != 1)
							is = false;
					else
						if(T.isInternal(T.left(PA)))
							is = aux(T, T.left(PA));
				}
				else
					if(height(T,T.right(PA)) != 1)
						is = false;
					else
						if(T.isInternal(T.right(PA)))
							is = aux(T,T.right(PA));
				}
			}
		}
		catch(InvalidPositionException e){}
		catch(BoundaryViolationException e){}
		return is;
	}
	
	private static<E> int height(BinaryTree<E> T,Position<E> PA){
		int H = 1;
		try{
			if(T.isExternal(PA))
				return H;
			else{
				for(Position<E> p: T.children(PA))
					H = Math.max(height(T,p),height(T,p)) + 1;
			}
		}
		catch(InvalidPositionException e){}
		return H;
	}
	
}
