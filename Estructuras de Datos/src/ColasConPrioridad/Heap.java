package ColasConPrioridad;


public class Heap<K,V> implements PriorityQueue<K, V> {
	protected int size;
	protected Entrada<K,V>[] ArbolHeap;
	protected Comparator<K> comp;
	
	/* RECORDAR: sea i la posición actual en la Heap...
	 * Padre(i) = i/2
	 * Hijo_izquierdo(i) = 2i
	 * Hijo_derecho(i) = 2i+1
	 */
	
	public Heap (int maxElems, Comparator<K> comp){
		size = 0;
		ArbolHeap = (Entrada<K,V>[]) new Entrada[maxElems]; //Porqué no puedo usar Entry?
		this.comp = comp;
	}
	/**
	 * Consulta la cantidad de elementos de la cola.
	 * @return Cantidad de elementos de la cola.
	 */	
	public int size(){
		return size;
	}

	/**
	 * Consulta si la cola está vacía.
	 * @return Verdadero si la cola está vacía, falso en caso contrario.
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * Devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola está vacía.
	 */
	public Entry<K,V> min()throws EmptyPriorityQueueException{
		if(ArbolHeap[1] == null){
			throw new EmptyPriorityQueueException("Cola vacia");
		}
		else
			return ArbolHeap[1];
	}

	/**
	 * Inserta un par clave-valor y devuelve la entrada creada.
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * @return Entrada creada.
	 * @throws InvalidKeyException si la clave es inválida.
	 */
	public Entry<K,V> insert(K key,V value)throws InvalidKeyException{
		checkKey(key);
		Entrada<K,V> ret =  new Entrada<K,V>(key,value);
		ArbolHeap[++size] = ret;
		int i = size;
		boolean seguir = true;
		while (i > 1 && seguir){
			Entry<K,V> EA = ArbolHeap[i];
			Entry<K,V> padre = ArbolHeap[i/2];
			if(comp.compare(EA.getKey(), padre.getKey()) < 0){ //el hijo es más chico que el Padre, esto es una MinHeap, asi que NO Puede pasar
				Entrada<K,V> aux = ArbolHeap[i]; //guardo el hijo
				ArbolHeap[i] = ArbolHeap[i/2]; //mando al padre donde estaba el hijo
				ArbolHeap[i/2] = aux; //mando el que inserté para arriba
				i /= 2; //reduzco el contador, ya que ya hice el movimiento, me muevo hacia la raíz del arbol
			}
			else{ //sino, el arbol ya está ordenado, no es necesario seguir
				seguir = false;
			}
		}
		return ret;	
	}

	/**
	 * Remueve y devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola está vacía.
	 */
	public Entry<K,V> removeMin()throws EmptyPriorityQueueException{
		if(size == 0)
			throw new EmptyPriorityQueueException("Cola Vacia");
		Entry<K,V> ret = min();
		if(size == 1){
			ArbolHeap[1] = null;
			size = 0;
		}
		else{
			ArbolHeap[1] = ArbolHeap[size];
			ArbolHeap[size] = null;
			size--;
			int i = 1;
			boolean seguir = true;
			while(seguir){
				//calculo los hijos de la posición en la que estoy
				int HI = 2*i;
				int HD = (2*i)+1;
				boolean EHI = HI <= size(); //determino si realmente existen los hijos de la posicion
				boolean EHD = HD <= size();
				if(!EHI) //si no hay Hijo Izquierdo, llegué a una hoja y ya moví todo de forma ordenada, no hace falta seguir
					seguir = false;
				else{
					int m; // Minimo de los hijos de i.
					if( EHD ) { //si existe Hijo Derecho, es necesario determinar el minimo de los dos
						// Calculo cual es el menor de los hijos.
						if( comp.compare( ArbolHeap[HI].getKey(), ArbolHeap[HD].getKey()) < 0 ) m = HI;
					 		else m = HD; 
					} 
					else m = HI; //sino, el minimo es el Hijo Izquierdo
					if(comp.compare(ArbolHeap[i].getKey(), ArbolHeap[m].getKey()) > 0){ //si el mínimo de los hijos es más grande que el padre
						Entrada<K,V> aux = ArbolHeap[i]; //Guardo el elemento que estoy moviendo
						ArbolHeap[i] = ArbolHeap[m]; //intercambio de lugar el minimo de los hijos y el elemento que estoy moviendo
						ArbolHeap[m] = aux;
						i = m; //sigo con "m" siendo la nueva posición
					}
					else
						seguir = false; //el minimo de los hijos es más chico, ya ordené la Heap
				}
			}
		}
		return ret;
	}
	/**
	 * metodo auxiliar para clonar Heaps
	 * @return un clon de la Heap
	 */
	public Heap<K,V> clone(){
		Heap<K,V> aux = new Heap<K,V>(size,comp);
		try{
			for(int i = 1; i < size; i++){
				aux.insert(ArbolHeap[i].getKey(), ArbolHeap[i].getValue());
			}
		}
		catch(InvalidKeyException e){}
		return aux;
	}
	
	public void replaceKey(Entry<K,V> e, K key){
		for(Entry<K,V> r: ArbolHeap){
			if(r == e){
				r.setKey(key);
				break;
			}
		}
	}
	
	
	private void checkKey(K key)throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Llave vacia");
	}
	
}
