package Maps;
import java.util.Iterator;
public interface PositionList<E> extends Iterable<E> {
	//metodos compartidos de Estructuras 
	public int size();
	 public boolean isEmpty();
	 //metodos de lectura,iteracion
	 public Position<E> first() throws EmptyListException;
	 public Position<E> last() throws EmptyListException;
	 public Position<E> prev( Position<E> p )
	throws InvalidPositionException, BoundaryViolationException;
	 public Position<E> next( Position<E> p )
	throws InvalidPositionException, BoundaryViolationException;
	 //Metodos de modificacion
	 public void addFirst(E e);
	 public void addLast(E e);
	 public void addAfter( Position<E> p, E e ) throws InvalidPositionException;
	 public void addBefore( Position<E> p, E e )
	throws InvalidPositionException;
	 public E remove( Position<E> p ) throws InvalidPositionException;
	 public E set( Position<E> p, E e ) throws InvalidPositionException;
	 //Metodo Iterator
	 public Iterator<E> iterator();
	 public PositionList<Position<E>> positions();
}
