package Pilas;

public class PilaArreglo<T> implements Stack<T> {
	protected T[] Pila;
	protected int cant;
	public PilaArreglo(){
		Pila = (T[]) new Object[20];
		cant = 0;
	}
	public void push(T elemento){
		if(cant == Pila.length)
			agrandarPila();
		else{
		Pila[cant] = elemento;
		cant++;
		}
	}
	 public T pop() throws EmptyStackException{
		 if(isEmpty()){
			 throw new EmptyStackException("Pila Vacia");
		 }
		 else{
		 T aux = null;
		 aux = Pila[cant-1];
		 Pila[cant-1] = null;
		 cant--;
		 return aux;
		 }
	 }
	 public int size(){
		 return cant;
	 }
	 public boolean isEmpty(){
		 return cant == 0;
	 }
	 public T top() throws EmptyStackException{
		 if(isEmpty())
			 throw new EmptyStackException("Pila Vacia");
		 else
			 return Pila[cant-1];
	 }
	 private void agrandarPila(){
		T[] PilaN = (T[]) new Object[Pila.length*2];
		 for(int i = 0; i < cant; i++){
			 PilaN[i] = Pila[i];
		 }
		 PilaN = Pila;
	 }
}
