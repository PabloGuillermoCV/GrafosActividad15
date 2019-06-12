package Graphs;

import java.util.Iterator;

public class GrafoConListaDeAdyacencias<V,E> implements Graph<V, E> {
	
	protected PositionList<Vertice<V,E>> nodos;
	protected PositionList<Arco<V,E>> arcos;
	
	//clases anidadas
	
	private class Vertice<V,E> extends MapeoConLista<Object,Object> implements Vertex<V>  {
		private V rotulo;
		private PositionList<Arco<V,E>> adyacentes;
		private Position<Vertice<V,E>> posicionEnNodos;
		public V element() { 
			return rotulo; 
		}
		public Vertice( V rotulo ) {
			this.rotulo = rotulo;
			adyacentes = new ListaDoblementeEnlazada<Arco<V,E>>();
		}
		public void setRotulo(V nuevoRotulo) {
			rotulo = nuevoRotulo; 
		}
		public PositionList<Arco<V,E>> getAdyacentes() {
			return adyacentes; 
		}
		public void setPosicionEnNodos(Position<Vertice<V,E>> p ) {
			posicionEnNodos = p; 
		}
		public Position<Vertice<V,E>> getPosicionEnNodos() { 
			return posicionEnNodos; 
		}
		
	}
	private class Arco<V,E> implements Edge<E> {
		private E rotulo;
		private Vertice<V,E> sucesor, predecesor;
		private Position<Arco<V,E>> posicionEnArcos;
		private Position<Arco<V,E>> posSucesor;
		private Position<Arco<V,E>> posAntecesor;
		private MapeoConLista<Object,Object> m = new MapeoConLista<Object,Object>();
		public Arco( E rotulo,Vertice<V,E> predecesor, Vertice<V,E> sucesor ){ 
			this.rotulo = rotulo;
			this.sucesor = sucesor;
			this.predecesor = predecesor;
		}
		public E element() { 
			return rotulo;
		}
		public Vertice<V,E> getPredecesor() { 
			return predecesor;
		}
		public Vertice<V,E> getSucesor() { 
			return sucesor; 
		}
		public Position<Arco<V,E>> getPosicionEnArcos() { 
			return posicionEnArcos; 
		}
		public Position<Arco<V,E>> getPosSucc(){
			return posSucesor;
		}
		public Position<Arco<V,E>> getPosAnt(){
			return posAntecesor;
		}
		public void setPosicionEnArcos(Position<Arco<V,E>> p) { 
			posicionEnArcos = p;
		}
		public void setPosicionSucesor(Position<Arco<V,E>> PS){
			posSucesor = PS;
		}
		public void setPosicionAntecesor(Position<Arco<V,E>> PA){
			posAntecesor = PA;
		}
		@Override
		public int size() {
			return m.size();
		}
		@Override
		public boolean isEmpty() {
			return m.isEmpty();
		}
		@Override
		public Object get(Object key) throws InvalidKeyException {
			return m.get(key);
		}
		@Override
		public Object put(Object key, Object value) throws InvalidKeyException {
			return m.put(key,value);
		}
		@Override
		public Object remove(Object key) throws InvalidKeyException {
			return m.remove(key);
		}
		@Override
		public Iterable<Object> keys() {
			return m.keys();
		}
		@Override
		public Iterable<Object> values() {
			return m.values();
		}
		@Override
		public Iterable<Entry<Object, Object>> entries() {
			return m.entries();
		}
		
	}
	
	public GrafoConListaDeAdyacencias(){
		nodos = new ListaDoblementeEnlazada<Vertice<V,E>>();
	}
	/**
	 * Devuelve una colecci�n iterable de v�rtices.
	 * @return Una colecci�n iterable de v�rtices.
	 */
	public Iterable<Vertex<V>> vertices(){
		PositionList<Vertex<V>> ret = new ListaDoblementeEnlazada<Vertex<V>>();
		for(Vertex<V> v: nodos){
			ret.addLast(v);
		}
		return ret;
	}
	
	/**
	 * Devuelve una colecci�n iterable de arcos.
	 * @return Una colecci�n iterable de arcos.
	 */
	public Iterable<Edge<E>> edges(){
		PositionList<Edge<E>> ret = new ListaDoblementeEnlazada<Edge<E>>();
		for(Vertice<V,E> v: nodos){
			Iterator<Arco<V,E>> it = v.getAdyacentes().iterator();
			while(it.hasNext()){
				Arco<V,E> aux = it.next();
				if(!esta(ret,aux))
					ret.addLast(aux);
			}
		}
		return ret;
	}
	
	/**
	 * Devuelve una colecci�n iterable de arcos incidentes a un v�rtice v.
	 * @param v Un v�rtice.
	 * @return Una colecci�n iterable de arcos incidentes a un v�rtice v.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException{
		Vertice<V,E> vv = checkVertex(v);
		PositionList<Edge<E>> ret = new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E> e : vv.getAdyacentes()){
			ret.addLast(e);
		}
		return ret;
	}
	
	
	/**
	 * Devuelve el v�rtice opuesto a un Arco E y un v�rtice V.
	 * @param v Un v�rtice
	 * @param e Un arco
	 * @return El v�rtice opuesto a un Arco E y un v�rtice V.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 * @throws InvalidEdgeException si el arco es inv�lido.
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException{
		Vertice<V,E> vv = checkVertex(v);
		Arco<V,E> ee = checkEdge(e);
		Vertex<V> ret;
		if(ee.getSucesor().equals(vv))
			ret = ee.getPredecesor();
		else
			ret = vv;
		return ret;
	}
	
	/**
	 * Devuelve un Arreglo de 2 elementos con lo v�rtices extremos de un Arco e.
	 * @param  e Un arco
	 * @return Un Arreglo de 2 elementos con los extremos de un Arco e.
	 * @throws InvalidEdgeException si el arco es inv�lido.
	 */
	public Vertex<V> [] endvertices(Edge<E> e) throws InvalidEdgeException{
		Arco<V,E> ee = checkEdge(e);
		Vertex<V>[] ret = new Vertex[2];
		ret[0] = ee.getPredecesor();
		ret[1] = ee.getSucesor();
		return ret;
	}
	
	/**
	 * Devuelve verdadero si el v�rtice w es adyacente al v�rtice v.
	 * @param v Un v�rtice
	 * @param w Un v�rtice
	 * @return Verdadero si el v�rtice w es adyacente al v�rtice v, falso en caso contrario.
	 * @throws InvalidVertexException si uno de los v�rtices es inv�lido.
	 */
	public boolean areAdjacent(Vertex<V> v,Vertex<V> w) throws InvalidVertexException{
		Vertice<V,E> vv = checkVertex(v);
		Vertice<V,E> ww = checkVertex(w);
		boolean is = false;
		Iterator<Arco<V,E>> it = vv.getAdyacentes().iterator();
		while(it.hasNext() && !is){ //chequeo que alguno de los vertices entre los arcos de los adyacentes de v sea w 
			Arco<V,E> aux = it.next();
			if(aux.getSucesor() == ww  || aux.getPredecesor() == ww) //desconozco si v es cola o cabeza del arco
				is = true;
		}
		return is;
	}
	
	/**
	 * Reemplaza el r�tulo de v por un r�tulo x.
	 * @param v Un v�rtice
	 * @param x R�tulo
	 * @return El r�tulo anterior del v�rtice v al reemplazarlo por un r�tulo x.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public V replace(Vertex<V> v, V x) throws InvalidVertexException{
		V ret;
		Vertice<V,E> aux = checkVertex(v);
		ret = aux.element();
		aux.setRotulo(x);
		return ret;
	}
	
	/**
	 * Inserta un nuevo v�rtice con r�tulo x.
	 * @param x r�tulo del nuevo v�rtice
	 * @return Un nuevo v�rtice insertado.
	 */
	public Vertex<V> insertVertex(V x){
		Vertice<V,E> ret = new Vertice<V,E>(x); //�Porqu� no uso Vertex?
		nodos.addLast(ret);
		try{
			ret.setPosicionEnNodos(nodos.last());
		}
		catch(EmptyListException e){}
		return ret;
	}
	
	/**
	 * Inserta un nuevo arco con r�tulo e, con v�rtices extremos v y w.
	 * @param v Un v�rtice
	 * @param w Un v�rtice
	 * @param e r�tulo del nuevo arco.
	 * @return Un nuevo arco.
	 * @throws InvalidVertexException si uno de los v�rtices es inv�lido.
	 */
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException{
		Vertice<V,E> vv = checkVertex(v);
		Vertice<V,E> ww = checkVertex(w);
		Arco<V,E> nuevo = new Arco<V,E>(e,vv,ww);
		arcos.addLast(nuevo);
		vv.getAdyacentes().addLast(nuevo);
		ww.getAdyacentes().addLast(nuevo); //Grafo NO Dirigido, debo agregar el arco creado en ambos vertices
		try{
			nuevo.setPosicionEnArcos(arcos.last());
			nuevo.setPosicionAntecesor(vv.getAdyacentes().last());
			nuevo.setPosicionSucesor(ww.getAdyacentes().last());
		}
		catch(EmptyListException t){}
		return nuevo;
	}
	
	/**
	 * Remueve un v�rtice V y retorna su r�tulo.
	 * @param v Un v�rtice
	 * @return r�tulo de V.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public V removeVertex(Vertex<V> v) throws InvalidVertexException{
		Vertice<V,E> vv = checkVertex(v);
		V ret = vv.element();
		try{
			for(Arco<V,E> e : vv.getAdyacentes()){
				if(e.getSucesor() == vv){ //si mi vertice es sucesor (cabeza de del arco)
					Vertice<V,E> v2 = e.getPredecesor();
					v2.getAdyacentes().remove(e.getPosAnt());
					vv.getAdyacentes().remove(e.getPosSucc()); //me da miedo hacer esto, pero sino; T(n,m) = O(n�2+m�2)
				}
				else{ //vv es antecesor (comienzo del arco)
					Vertice<V,E> v2 = e.getSucesor();
					v2.getAdyacentes().remove(e.getPosSucc());
					vv.getAdyacentes().remove(e.getPosAnt());
				}
			}
			nodos.remove(vv.getPosicionEnNodos());
		}
		catch(InvalidPositionException e){}
		return ret;
	}
	
	/**
	 * Remueve un arco e y retorna su rotulo.
	 * @param e Un arco
 	 * @return rotulo de E.
	 * @throws InvalidEdgeException si el arco es invalido.
	 */
	public E removeEdge(Edge<E> e) throws InvalidEdgeException{
		Arco<V,E> ee = checkEdge(e);
		Vertice<V,E> v1 = ee.getPredecesor();
		Vertice<V,E> v2 = ee.getSucesor();
		E ret = ee.element();
		try{
			v1.getAdyacentes().remove(ee.getPosAnt());
			v2.getAdyacentes().remove(ee.getPosSucc()); 
			arcos.remove(ee.getPosicionEnArcos());
		}
		catch(InvalidPositionException t){}
		return ret;
	}
	
	/**
	 * metodo auxiliar que chequea la validez de un arco pasado por paramatro
	 * @param e Edge a examinar
	 * @return Edge desenamsacarado como Arco
	 * @throws InvalidEdgeException si el Edge es nulo o fallo el Casting
	 */
	private Arco<V,E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		if(e == null)
			throw new InvalidEdgeException("Arco Invalido");
		else{
			try{
				return (Arco<V,E>) e;
			}
			catch(ClassCastException t){throw new InvalidEdgeException("lo pasado por par�metro NO era un Arco");} 
		}
	}
	
	/**
	 * metodo auxiliar que chequea la validez de un Vertex pasado por par�metro
	 * @param v vertex a examinar
	 * @return Vertex desenmascarado como Vertice
	 * @throws InvalidVertexException si el vertex era invalido o fall� el Casting
	 */
	private Vertice<V,E> checkVertex(Vertex<V> v) throws InvalidVertexException{
		if(v == null)
			throw new InvalidVertexException("Vertice nulo");
		else{
			try{
				return (Vertice<V,E>)v;
			}
			catch(ClassCastException e){throw new InvalidVertexException("Vertice Inv�lido");}
		}
	}
	
	/**
	 * metodo auxiliar usado para verificar que NO se repiten arcos en el metodo "Edges"
	 * @param r Lista de arcos a examinar
	 * @param e Arco a buscar
	 * @return verdadero si el arco ya esta en la Lista, falso caso contrario
	 */
	private boolean esta(PositionList<Edge<E>> r,Arco<V,E> e){
		boolean esta = false;
		try{		
			Iterator<Edge<E>> it = r.iterator();
			while(!esta && it.hasNext()){
				Arco<V,E> aux = checkEdge(it.next());
				if(aux.equals(e))
					esta = true;
			}
		}
		catch(InvalidEdgeException g){}
		return esta;	
	}
	
	
	private PositionList<Vertex<V>> DFSShell(){
		PositionList<Vertex<V>> ret = new ListaDoblementeEnlazada<Vertex<V>>();
		Map<Vertex<V>,Boolean>M = new MapeoConLista<Vertex<V>,Boolean>();
		try{
			for(Vertice<V,E> v: nodos){
				M.put(v,false);
			}
			for(Vertice<V,E> V: nodos){
				if(M.get(V) == false)
					DFS(ret,V,M);
			}
		}
		catch(InvalidKeyException e){}
		return ret;
	}
	private void DFS(PositionList<Vertex<V>> l, Vertice<V,E> PA,Map<Vertex<V>,Boolean> mark){
		l.addLast(PA);
		try{
		mark.put(PA,true);
		for(Arco<V,E> w: PA.getAdyacentes()){
			Vertex<V> aux = opposite(PA,w);
			if(mark.get(aux) == false){
				DFS(l,checkVertex(aux),mark);
			}
		}
		}
		catch(InvalidKeyException e){}
		catch(InvalidVertexException e){}
		catch(InvalidEdgeException e){}
		//Procesamiento de v luego del recorrido
	}
	
	private PositionList<Vertex<V>> DBSShell(){
		PositionList<Vertex<V>> ret = new ListaDoblementeEnlazada<Vertex<V>>();
		Map<Vertex<V>,Boolean> marcas = new MapeoConLista<Vertex<V>,Boolean>();
		try{
			for(Vertex<V> v: vertices()){
				marcas.put(v, false);
			}
			for(Vertex<V> V: vertices()){
				if(!marcas.get(V))
					DBS(ret,V,marcas);
			}
			
		}
		catch(InvalidKeyException e){}
		return ret;
	}
	private void DBS(PositionList<Vertex<V>> l, Vertex<V> PA,Map<Vertex<V>,Boolean> mark){
		
		Queue<Vertex<V>> Cola = new ColaConEnlaces<Vertex<V>>();
		try{
			Cola.enqueue(PA);
			while(!Cola.isEmpty()){
			Vertex<V> aProcesar = Cola.dequeue();
			l.addLast(aProcesar);
			mark.put(aProcesar, true);
			for(Edge<E> e: incidentEdges(aProcesar)){
					if(!mark.get(opposite(aProcesar,e)))
							Cola.enqueue(opposite(aProcesar,e));
				}
			}
		}
		catch(FullQueueException e){}
		catch(EmptyQueueException e){}
		catch(InvalidKeyException e){}
		catch(InvalidVertexException e){}
		catch(InvalidEdgeException e){}
	}
}