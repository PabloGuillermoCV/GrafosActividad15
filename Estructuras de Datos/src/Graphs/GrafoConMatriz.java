package Graphs;

public class GrafoConMatriz<V,E> implements Graph<V, E> {
	
	//Recordar, la matriz devuelve un arco, los indices estan "Ligados" a los vertices
	protected PositionList<Vertex<V>> vertices;
	protected PositionList<Edge<E>> arcos;
	protected Edge<E> [][] matriz;
	protected int cantidadVertices;
	
	//clases anidadas
	
	private class Vertice<V> extends MapeoConLista<Object,Object> implements Vertex<V> {
		private V rotulo;
		private int indice;
		private Position<Vertex<V>> posicionEnVertices;
		public Vertice( V rotulo, int indice ) { this.rotulo = rotulo; this.indice = indice; }
		public void setPosicionEnVertices( Position<Vertex<V>> p ) {
		posicionEnVertices = p; }
		public void setRotulo(V nuevoRotulo) { rotulo = nuevoRotulo; }
		public int getIndice() { return indice; }
		public void setIndice(int I){indice = I;}
		public Position<Vertex<V>> getPositionEnVertices() { return posicionEnVertices; }
		public V element() { return rotulo; }
	}
	private class Arco<V,E> implements Edge<E> { 
		private E rotulo;
		private MapeoConLista<Object,Object> m = new MapeoConLista<Object,Object>();
		private Position<Edge<E>> posicionEnArcos;
		private Vertice<V> v1, v2;
		public Arco( E rotulo, Vertice<V> v1, Vertice<V> v2 ) {
		this.rotulo = rotulo; this.v1 = v1; this.v2 = v2; }
		public void setPosicionEnArcos( Position<Edge<E>> p ) { posicionEnArcos = p; }
		public E element() { return rotulo; }
		public Position<Edge<E>> getPosicionEnArcos() { return posicionEnArcos; }
		public Vertice<V> getV1() { return v1; }
		public Vertice<V> getV2() { return v2; }
		public void setRotulo(E nuevoRotulo) { rotulo = nuevoRotulo; }
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
		
	public GrafoConMatriz(int max){
		cantidadVertices = 0;
		matriz = (Edge<E>[][]) new Arco[max][max];
		vertices = new ListaDoblementeEnlazada<Vertex<V>>();
		arcos = new ListaDoblementeEnlazada<Edge<E>>();
	}
	
	/**
	 * Devuelve una colecci�n iterable de v�rtices.
	 * @return Una colecci�n iterable de v�rtices.
	 */
	public Iterable<Vertex<V>> vertices(){
		PositionList<Vertex<V>> ret = new ListaDoblementeEnlazada<Vertex<V>>();
		for(Vertex<V> v: vertices){
			ret.addLast(v);
		}
		return ret;
	}
	
	/**
	 * Devuelve una colecci�n iterable de arcos.
	 * @return Una colecci�n iterable de arcos.
	 */
	public Iterable<Edge<E>> edges(){
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
		for( Edge<E> e : arcos )
		lista.addLast(e);
		return lista;
	}
	
	/**
	 * Devuelve una colecci�n iterable de arcos incidentes a un v�rtice v.
	 * @param v Un v�rtice.
	 * @return Una colecci�n iterable de arcos incidentes a un v�rtice v.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException{
		checkVertex(v);
		Vertice<V> aux = (Vertice<V>) v;
		int fila = aux.getIndice();
		PositionList<Edge<E>> ret = new ListaDoblementeEnlazada<Edge<E>>();
		for(int c = 0; c < cantidadVertices; c++){
			if(matriz[fila][c] != null)
				ret.addLast(matriz[fila][c]);
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
		checkVertex(v);
		checkEdge(e);
		Vertice<V> aux1 = (Vertice<V>) v;
		Arco<V,E> aux2 = (Arco<V,E>) e;
		Vertice<V> ret;
		if(aux2.getV1().equals(aux1)) //si de los vertices posibles para el arco, la cola era el vertice pasado por par�metro...
			ret = aux2.getV2(); //el vertice cabeza es el opuesto
		else
			ret = aux2.getV1();
		return ret;
		
	}
	
	/**
	 * Devuelve un Arreglo de 2 elementos con lo v�rtices extremos de un Arco e.
	 * @param  e Un arco
	 * @return Un Arreglo de 2 elementos con los extremos de un Arco e.
	 * @throws InvalidEdgeException si el arco es inv�lido.
	 */
	public Vertex<V> [] endvertices(Edge<E> e) throws InvalidEdgeException{
		checkEdge(e);
		Vertex<V>[] ret = (Vertex<V>[]) new Vertice[2];
		Arco<V,E> aux = (Arco<V,E>) e;
		ret[0] = aux.getV1();
		ret[1] = aux.getV2();
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
		Vertice<V> aux1 = (Vertice<V>) v;
		Vertice<V> aux2 = (Vertice<V>) w;
		return (matriz[aux1.getIndice()][aux2.getIndice()] != null);
	}
	
	/**
	 * Reemplaza el r�tulo de v por un r�tulo x.
	 * @param v Un v�rtice
	 * @param x R�tulo
	 * @return El r�tulo anterior del v�rtice v al reemplazarlo por un r�tulo x.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public V replace(Vertex<V> v, V x) throws InvalidVertexException{
		checkVertex(v);
		Vertice<V> aux = (Vertice<V>) v;
		V ret = aux.element();
		aux.setRotulo(x);
		return ret;
	}
	
	/**
	 * Inserta un nuevo v�rtice con r�tulo x.
	 * @param x r�tulo del nuevo v�rtice
	 * @return Un nuevo v�rtice insertado.
	 */
	public Vertex<V> insertVertex(V x){
		if(cantidadVertices == matriz.length-1)
				agrandarMatriz();
		Vertice<V> vv = new Vertice<V>(x, cantidadVertices++);
		vertices.addLast( vv );
		try{
		vv.setPosicionEnVertices( vertices.last() );
		}
		catch(EmptyListException e){}
		return vv;
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
		checkVertex(v);
		checkVertex(w);
		Vertice<V> aux1 = (Vertice<V>)v;
		Vertice<V> aux2 = (Vertice<V>)w;
		Arco<V,E> ret = new Arco<V,E>(e,aux1,aux2);
		matriz[aux1.getIndice()][aux2.getIndice()] = matriz[aux2.getIndice()][aux1.getIndice()] = ret; //grafo NO dirigido, la matriz DEBE ser Sim�trica
		arcos.addLast(ret);
		try {
			ret.setPosicionEnArcos(arcos.last());
		} 
		catch (EmptyListException e1) {e1.printStackTrace();}
		return ret;
	}
	
	/**
	 * Remueve un v�rtice V y retorna su r�tulo.
	 * @param v Un v�rtice
	 * @return r�tulo de V.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public V removeVertex(Vertex<V> v) throws InvalidVertexException{
		checkVertex(v);
		Vertice<V> aux = (Vertice<V>)v;
		V ret = aux.element();
		PositionList<Edge<E>> elim = new ListaDoblementeEnlazada<Edge<E>>();
		int fila = aux.getIndice();
		for(int C = 0; C < cantidadVertices; C++){ //busco si existen arcos incidentes para v y los guardo, en caso de que hayan
			if(matriz[fila][C] != null){
				elim.addLast(matriz[fila][C]);
				matriz[fila][C] = null; //elimino el arco de la matriz, pero NO de la Lista de Arcos 
			}
		}
		if(!elim.isEmpty()){ //si la lista NO esta vacia, siginfica que existen arcos incidentes a V, estos DEBEN ser eliminados ANTES de elmimnar a v
			try{
				for(Edge<E> e: elim){
					Arco<V,E> arc = (Arco<V,E>)e;
					arcos.remove(arc.getPosicionEnArcos());
				}
			}
			catch(InvalidPositionException e){}
		}
		else{
			try{
				reacomodarMatriz(fila);
				vertices.remove(aux.getPositionEnVertices());
				cantidadVertices--;
			}
			catch(InvalidPositionException e){}
		}
		return ret;
		
	}
	
	/**
	 * Remueve un arco e y retorna su r�tulo.
	 * @param e Un arco
 	 * @return r�tulo de E.
	 * @throws InvalidEdgeException si el arco es inv�lido.
	 */
	public E removeEdge(Edge<E> e) throws InvalidEdgeException{
		checkEdge(e);
		Arco<V,E> aux = (Arco<V,E>)e;
		E ret = aux.element();
		//obtengo los vertices que hacen uso del arco que voy a eliminar
		Vertice<V> v1 = aux.getV1();
		Vertice<V> v2 = aux.getV2();
		matriz[v1.getIndice()][v2.getIndice()] = matriz[v2.getIndice()][v1.getIndice()] = null; //elimino la conexi�n entre los vertices que usaban al arco de la matriz
		try{
			arcos.remove(aux.getPosicionEnArcos());
		}
		catch(InvalidPositionException f){}
		return ret;
	}
	/**
	 * metodo auxiliar que chequea que un vertice no sea invalido
	 * @param V vertice a examinar
	 * @throws InvalidVertexException si el vertice era nulo
	 */
	private void checkVertex(Vertex<V> V) throws InvalidVertexException{
		if(V == null)
			throw new InvalidVertexException("Vertice Vacio");
	}
	/**
	 * metodo auxiliar que chequea si un arco es invalido
	 * @param E arco a examinar
	 * @throws InvalidEdgeException si el arco era nulo
	 */
	private void checkEdge(Edge<E> E) throws InvalidEdgeException{
		if(E == null)
			throw new InvalidEdgeException("Arco Vacio");
	}
	/**
	 * metodo auxiliar que agranda la matriz en caso de que se llene
	 */
	private void agrandarMatriz(){
		Edge<E>[][] nuevo = (Edge<E>[][]) new Arco[matriz.length*2][matriz[0].length*2];
		for(int f = 0; f < matriz.length;f++){
			for(int c = 0; c < matriz[0].length;c++){
				if(matriz[f][c] != null)
					nuevo[f][c] = matriz[f][c];
			}
		}
		matriz = nuevo;
	}
	/**
	 * metodo auxiliar que "achica" la matriz en 1 una vez eliminado un vertice
	 * @param f fila que debe de estar vacia
	 */
	private void reacomodarMatriz(int f){
		Edge<E>[][] nuevo = (Edge<E>[][]) new Arco[matriz.length-2][matriz[0].length-2];
		for(int fila = 0; fila < f; fila++){
			for(int C = 0; C < matriz[0].length; C++){
				nuevo[fila][C] = matriz[fila][C]; //copio los elementos antes de la fila borrada como si nada
			}
		}
		for(int F = f; F <= matriz.length - 1;F++){
			for(int c = 0; c < matriz[0].length;c++){
				nuevo[F][c] = matriz[F + 1][c]; //copio los elementos de la amrtiz, pero moviendolos una fila hacia abajo
				Arco<V,E> aux = (Arco<V,E>) matriz[F+1][c];
				aux.getV1().setIndice(F-1);
				
			}
		}
		matriz = nuevo;
	}
	
}
