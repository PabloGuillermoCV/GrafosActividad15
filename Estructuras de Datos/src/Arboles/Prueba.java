package Arboles;

public class Prueba {

	public static void main(String[] args) {
		BinaryTree<String> p = new ArbolBinario<String>();
		try{
		p.createRoot("Hola");
		p.addLeft(p.root(), "Soy");
		p.addRight(p.root(), "Pablo");
		p.addRight(p.left(p.root()), "Como");
		System.out.println(toStringArb(p,p.root()));
		}
		catch(EmptyTreeException e){}
		catch(InvalidPositionException e){}
		catch(InvalidOperationException e){}
		catch(BoundaryViolationException e){}

	}
	
	private static String toStringArb(BinaryTree<String> A, Position<String> PA){
		String ret = "";
	try{
		if(A.isExternal(PA))
			ret += PA.element() + ".";
		else{
			ret += " " + PA.element();
			if(A.hasLeft(PA))
				ret+= " " + toStringArb(A,A.left(PA));
			if(A.hasRight(PA))
				ret += " " + toStringArb(A,A.right(PA));
		}	
	}
	catch(InvalidPositionException e){}
	catch(BoundaryViolationException e){}
	return ret;
	}

}
