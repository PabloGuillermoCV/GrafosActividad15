package Maps;
import java.lang.*;
import java.util.*;
public class ElementIterator<E> implements Iterator<E> {
 protected PositionList<E> list;
 protected Position<E> cursor;
 //constructor
 public ElementIterator(PositionList<E> l){
	 list = l;
	 try{
	 if(list.isEmpty())
		 cursor = null;
	 else
		 cursor = list.first();
	 }
	 catch (EmptyListException e){ System.out.println("se intentó iterar una lista vacia"); }
 }
 //metodos de Iterator
 public boolean hasNext(){
	 return cursor != null;
 }
 public E next(){
	 E toRet = cursor.element();
	 try{
	 cursor = (cursor == list.last()) ? null : list.next(cursor);
	 }
	 catch(EmptyListException e){ System.out.println("Se intentó iterar una lista vacai"); }
	 catch(BoundaryViolationException e){ System.out.println("Se intetnó entrar a una posicion fuera de la lista"); }
	 catch(InvalidPositionException e){System.out.println("Se iontentó entrar con una posición inválida");}
	 return toRet;
 }
 //remove...
}
