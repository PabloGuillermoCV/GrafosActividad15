package Colas;

public class ColaArrCirc<T> implements Queue<T> {
 protected T[] elem;
 protected int F;
 protected int R;
 private static final int Max = 20;
 public ColaArrCirc(){
	 elem = (T[]) new Object[Max];
	 F = 0;
	 R = 0;
 }
 public void enqueue(T e) throws FullQueueException{
	if(size() == Max-1)
		throw new FullQueueException("Cola llena");
	else{
		elem[R] = e;
		R = (R+1) % Max;
	}
 }
 public T dequeue() throws EmptyQueueException{
	 T aux = null;
	 if(size() == 0){
		 throw new EmptyQueueException("Cola Vacia");
	 }
	 else{
	 aux = elem[F];
	 elem[F] = null;
	 F = (F + 1) % Max;
	 }
	 return aux;
 }
 public int size(){
	 return (Max - F + R) % Max;
 }
 public T front() throws EmptyQueueException{
	 if(size() == 0)
		 throw new EmptyQueueException("Cool Vacia");
	 else
		 return elem[F];
 }
 public boolean isEmpty(){
	 return F == R;
 }
}
