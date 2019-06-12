package Dictionaries;

import java.util.Iterator;

public class Diccionario23<K,V> implements Dictionary<K, V> {

	private Nodo23<K,V> raiz;
	private Comparator<K> comp;
	private int size;
	
	public Diccionario23(Comparator<K> c){
		raiz = new Nodo23<K,V>(null);
		comp = c;
		size = 0;
	}
	
	/**
	 * Consulta el número de entradas del diccionario.
	 * @return Número de entradas del diccionario.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Consulta si el diccionario está vacío.
	 * @return Verdadero si el diccionario está vacío, falso en caso contrario.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Entry<K,V> find(K key) throws InvalidKeyException{
		checkKey(key);
		Entry<K,V> ret = buscar(key,raiz);
		return ret;
	}
	
	/**
	 * Retorna una colección iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colección iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		checkKey(key);
		Dictionary<K,V> D = buscarDiccionario(key,raiz);
		if(D == null)
			return null;
		else
			return D.findAll(key);
	}
	
	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		checkKey(key);
		Entry<K,V> ret = new Entrada<K,V>(key,value);
		Insertar(key,value,raiz);
		return ret;
	}
	
	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la clave es inválida.
	 */
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		
	}
	
	/**
	 * Retorna una colección iterable con todas las entradas en el diccionario.
	 * @return Colección iterable de todas las entradas.
	 */
	public Iterable<Entry<K,V>> entries(){
		
	}
	
	private void Insertar(K key, V value, Nodo23<K,V>PA){
		if(PA.getEntradas().size() == 0){ //llegué a una hoja, debo actualizar el Padre con la entrada nueva y chequear si ocurre un Overflow
			Nodo23<K,V> Padre = PA.getPadre();
			if(Padre.getEntradas().size() == 1){ //el nodo a actualizar contiene una entrada, debo crear el dummy "HM" y ya esta
					Padre.addEntry(key, value);
					Padre.getAll()[1] = new Nodo23<K,V>(Padre);
			}
			else{ //hay más de 1 entrada en el nodo (hay 2), VA ocurrir un OverFlow
				Padre.addEntry(key,value);
				Padre.getAll()[3] = new Nodo23<K,V>(Padre);
				RemendarOverFlow(Padre); //Ocurre un Overflow ya que el nodo contiene 3 Entradas y 4 Hijos
			}
		}
	}
	
	private void RemendarOverFlow(Nodo23<K,V> PdP){
		int i = 0;
		Iterator<Entry<K,V>> it = PdP.getEntradas().entries().iterator();
		Entry<K,V>[]  e = (Entry<K,V>[]) new Object[3];
		while(it.hasNext()){
			e[i] = it.next();
			i++;
		}
		if(PdP == raiz){ //el overflow ocurre en la raiz del arbol, debo hacer que el arbol crezca 1 en altura, tambien es el caso base de la recursión
			Nodo23<K,V> r = new Nodo23<K,V>(null);
			raiz = r;
			raiz.addEntry(e[1].getKey(), e[1].getValue());
			PdP.getHijo(1).setFather(null);
			PdP.getAll()[1] = null;
			PdP.getHijo(3).setFather(null);
			PdP.getAll()[3] = null;
			PdP.setFather(raiz);
			raiz.getAll()[2] = PdP;
			try{
				PdP.getEntradas().remove(e[0]);
				PdP.getEntradas().remove(e[1]);
			}
			catch(InvalidEntryException t){}
			Nodo23<K,V> HI = new Nodo23<K,V>(raiz);
			HI.addEntry(e[0].getKey(), e[0].getValue());
			
		}
		else{ //caso general de la recursion, hay que remendar y verificar si ocurre un overflow en el Padre del nodo arreglado
			Nodo23<K,V> Padre = PdP.getPadre();
			Padre.addEntry(e[1].getKey(), e[1].getValue());
			Nodo23<K,V> HaAgregar = new Nodo23<K,V>(Padre);
			try{
				PdP.getEntradas().remove(e[0]);
				PdP.getEntradas().remove(e[1]);
				PdP.getHijo(1).setFather(null);
				PdP.getHijo(3).setFather(null);
				PdP.getAll()[1] = PdP.getAll()[3] = null;
			}
			catch(InvalidEntryException t){}
			Nodo23<K,V>[] h = Padre.getAll();
			if(h[0] == PdP){
				if(h[1] != null){
					Padre.getAll()[3] = h[2];
					Padre.getAll()[2] = h[1];
					HaAgregar.addEntry(e[2].getKey(), e[2].getValue());
					Padre.getAll()[1] = HaAgregar;
					try{
						PdP.getEntradas().remove(e[1]);
						PdP.getEntradas().remove(e[2]);
					}
					catch(InvalidEntryException t){}
					remendarOverflow34(PdP);
				}
				else{
					HaAgregar.addEntry(e[2].getKey(), e[2].getValue());
					Padre.getAll()[1] = HaAgregar;
					if(Padre.getEntradas().size() == 3)
						RemendarOverFlow(Padre);
				}
			}
			else{
				if(h[1] == PdP){
					Padre.getAll()[3] = Padre.getAll()[2];
					HaAgregar.addEntry(e[2].getKey(), e[2].getValue());
					HaAgregar.setFather(Padre);
					Padre.getAll()[2] = HaAgregar;
					try{
						PdP.getEntradas().remove(e[1]);
						PdP.getEntradas().remove(e[2]);
					}
					catch(InvalidEntryException t){}
					remendarOverflow34(Padre);
				}
				else{ //PdP es HD
					if(h[1] != null){
						
					}
					else{
						HaAgregar.addEntry(e[0].getKey(), e[0].getValue());
						HaAgregar.setFather(Padre);
						Padre.getAll()[1] = HaAgregar;
						try{
							PdP.getEntradas().remove(e[0]);
							PdP.getEntradas().remove(e[1]);
						}
						catch(InvalidEntryException t){}
						if(Padre.getEntradas().size() == 3)
							RemendarOverFlow(Padre);
					}
				}
			}
			
		}
		
	}
	
	private void remendarOverflow34(Nodo23<K,V> PdP){
		
	}
	
	private Dictionary<K,V> buscarDiccionario(K key, Nodo23<K,V> PA){
		Dictionary<K,V> ret = null;
		if(PA.getEntradas().size() == 0)
			ret = null;
		else{
			try{
				if(PA.getEntradas().find(key) != null){
					ret = PA.getEntradas();
				}
				else{ //me dio nulo, NO esta en este nodo
					int H = determinarCamino(key,PA);
					ret = buscarDiccionario(key,PA.getHijo(H));
				}
			}
			catch(InvalidKeyException e){}
		}
		return ret;
	}
	
	private Entry<K,V> buscar(K key, Nodo23<K,V> PA){
		Entry<K,V> ret = null;
		if(PA.getEntradas().size() == 0) //llegue a una hoja, no tengo rotulos
			ret = null;
		else{
			try{
				Entry<K,V> E = PA.getEntradas().find(key);
				if( E != null)
					ret = E;
				else{
					int H = determinarCamino(key,PA);
					ret = buscar(key,PA.getHijo(H));
				}
			}
			catch(InvalidKeyException e){}
		}
		return ret;
	}
	
	private int determinarCamino(K key, Nodo23<K,V> PA){
		int i = 0;
		Iterator<Entry<K,V>> it = PA.getEntradas().entries().iterator();
		Entry<K,V>[]  e = (Entry<K,V>[]) new Object[2];
		while(it.hasNext()){
			e[i] = it.next();
			i++;
		}
		i = 0;
		int c = comp.compare(key,e[0].getKey());
			if(c < 0){ //k < k1, debo ir por el HI
				i = 0;
			}
			else{
				if(PA.getEntradas().size() == 2){ //si el nodo es completo
					c = comp.compare(key, e[1].getKey());
					if(comp.compare(key, e[0].getKey()) > 0 && c < 0)
						i = 1; //k1 < k < k2, debo ir por HM
						else
							if(comp.compare(key, e[0].getKey()) > 0 && c > 0)
									i = 2; //k > k2, debo ir por el HD
					}
				else{
					if(c > 0) //si mi nodo NO es completo y k > k1
						i = 2; //voy por el HD existente
				}
			}
			return i;
	}
	
	
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException ("LLave Invalida");
	}
	
}
