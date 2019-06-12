package Graphs;


public class GrafoDConMatrizDeAdyacencia<V,E> implements GraphD<V, E> {

	private Edge<E>[][] arcos;
	private int cantVertices;
	private PositionList<Vertex<V>> vertices;
	private PositionList<Edge<E>> Edges;
	
	private class Arco<V,E>  extends MapeoConLista<Object,Object> implements Edge<E>{
		private Vertice<V,E> v1;
		private Vertice<V,E> v2;
		private E rot;
		private Position<Edge<E>> posEnArcos;
		public Arco(E r, Vertice<V,E> ini, Vertice<V,E> fin){
			rot = r;
			ini = v1;
			fin = v2;
		}
		public E element(){
			return rot;
		}
		public void setPosEnArcos(Position<Edge<E>> p){
			posEnArcos = p;
		}
		public Vertice<V,E> getIni(){
			return v1;
		}
		public Vertice<V,E> getFin(){
			return v2;
		}
		public Position<Edge<E>> getPosEnArcos(){
			return posEnArcos;
		}
	}
	
	private class Vertice<V,E> extends MapeoConLista<Object,Object> implements Vertex<V>{
		private int indice;
		private Position<Vertex<V>> posEnNodos;
		private V rot;
		public Vertice(V r, int index){
			rot = r;
			indice = index;
		}
		public V element(){
			return rot;
		}
		public int getIndice(){
			return indice;
		}
		public Position<Vertex<V>> getPosEnNodos(){
			return posEnNodos;
		}
		public void setIndice(int I){
			indice = I;
		}
		public void setRotulo(V r){
			rot = r;
		}
		public void setPosEnNodos(Position<Vertex<V>> p){
			posEnNodos = p; 
		}
	}
	
	public GrafoDConMatrizDeAdyacencia(int max){
		arcos = (Arco<V,E>[][]) new Arco[max][max];
		vertices = new ListaDoblementeEnlazada<Vertex<V>>();
		Edges = new ListaDoblementeEnlazada<Edge<E>>();
		cantVertices = 0;
	}
	
	/**
	 * Devuelve una colecci�n iterable de v�rtices.
	 * @return Una colecci�n iterable de v�rtices.
	 */
	public Iterable<Vertex<V>> vertices(){
		PositionList<Vertex<V>> ret = new ListaDoblementeEnlazada<Vertex<V>>();
		for(Vertex<V> v : vertices){
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
		for(Edge<E> e: Edges)
			ret.addLast(e);
		return ret;
	}
	
	/**
	 * Devuelve una colecci�n iterable de arcos incidentes a un v�rtice v.
	 * @param v Un v�rtice.
	 * @return Una colecci�n iterable de arcos incidentes a un v�rtice v.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException{
		PositionList<Edge<E>> ret = new ListaDoblementeEnlazada<Edge<E>>();
		Vertice<V,E> vv = checkVertex(v);
		for(int i = 0; i < cantVertices; i++){
			if(arcos[i][vv.getIndice()] != null)
				ret.addLast(arcos[i][vv.getIndice()]);
		}
		return ret;
	}
	
	/**
	 * Devuelve una colecci�n iterable de arcos adyacentes a un v�rtice v.
	 * @param v Un v�rtice
	 * @return Una colecci�n iterable de arcos adyacentes a un v�rtice v.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException{
		PositionList<Edge<E>> ret = new ListaDoblementeEnlazada<Edge<E>>();
		Vertice<V,E> vv = checkVertex(v);
		for(int i = 0; i < cantVertices; i++){
			if(arcos[vv.getIndice()][i] != null)
				ret.addLast(arcos[vv.getIndice()][i]);
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
		if(ee.getFin() == vv)
			throw new InvalidVertexException("Vertice pedido es el final, NO es Logico");
		else
			return ee.getFin();
	}
	
	/**
	 * Devuelve un Arreglo de 2 elementos con lo v�rtices extremos de un Arco e.
	 * @param  e Un arco
	 * @return Un Arreglo de 2 elementos con los extremos de un Arco e.
	 * @throws InvalidEdgeException si el arco es inv�lido.
	 */
	public Vertex<V> [] endvertices(Edge<E> e) throws InvalidEdgeException{
		Vertex<V>[] ret = (Vertex<V>[]) new Vertice[2];
		Arco<V,E> ee = checkEdge(e);
		ret[0] = ee.getIni();
		ret[1] = ee.getFin();
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
		int iV = vv.getIndice();
		int iW = ww.getIndice();
		return arcos[iV][iW] != null || arcos[iW][iV] != null;
	}
	
	/**
	 * Reemplaza el r�tulo de v por un r�tulo x.
	 * @param v Un v�rtice
	 * @param x R�tulo
	 * @return El r�tulo anterior del v�rtice v al reemplazarlo por un r�tulo x.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public V replace(Vertex<V> v, V x) throws InvalidVertexException{
		Vertice<V,E> vv = checkVertex(v);
		V ret = vv.element();
		vv.setRotulo(x);
		return ret;
	}
	
	/**
	 * Inserta un nuevo v�rtice con r�tulo x.
	 * @param x r�tulo del nuevo v�rtice
	 * @return Un nuevo v�rtice insertado.
	 */
	public Vertex<V> insertVertex(V x){
		if(cantVertices == arcos.length-1)
			agrandarMatriz();
		Vertice<V,E> vv = new Vertice<V,E>(x, cantVertices++);
		vertices.addLast( vv );
		try{
			vv.setPosEnNodos( vertices.last() );
		}
		catch(EmptyListException e){}
		return vv;
	}
	
	/**
	 * Inserta un nuevo arco con r�tulo e, desde un v�rtice v a un v�rtice w.
	 * @param v Un v�rtice
	 * @param w Un v�rtice
	 * @param e r�tulo del nuevo arco.
	 * @return Un nuevo arco insertado desde un v�rtice V a un v�rtice W.
	 * @throws InvalidVertexException si uno de los v�rtices es inv�lido.
	 */
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException{
		Vertice<V,E> vv = checkVertex(v);
		Vertice<V,E> ww = checkVertex(w);
		Arco<V,E> nuevo = new Arco<V,E>(e,vv,ww);
		arcos[vv.getIndice()][ww.getIndice()] = nuevo;
		Edges.addLast(nuevo);
		try{
		nuevo.setPosEnArcos(Edges.last());
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
		Arco<V,E> aux1,aux2;
		int fila = vv.getIndice();
		try{
			//elimino TODOS los arcos que salen de v
			for(int c = 0; c < cantVertices; c++){
				if(arcos[fila][c] != null){
					aux1 = checkEdge(arcos[fila][c]);
					Edges.remove(aux1.getPosEnArcos());
					arcos[fila][c] = null;
				}
				//elimino TODOS los arcos que entran a v
				if(arcos[c][fila] != null){
					aux2 = checkEdge(arcos[c][fila]);
					Edges.remove(aux2.getPosEnArcos());
					arcos[c][fila] = null;
				}
			}
			vertices.remove(vv.getPosEnNodos());
		}
		catch(InvalidEdgeException e){}
		catch(InvalidPositionException e){}
		return ret;
	}
	
	/**
	 * Remueve un arco e y retorna su r�tulo.
	 * @param e Un arco
 	 * @return r�tulo de E.
	 * @throws InvalidEdgeException si el arco es inv�lido.
	 */
	public E removeEdge(Edge<E> e) throws InvalidEdgeException{
		Arco<V,E> ee = checkEdge(e);
		E ret = ee.element();
		Vertice<V,E> v1 = ee.getIni();
		Vertice<V,E> v2 = ee.getFin();
		arcos[v1.getIndice()][v2.getIndice()] = null;
		try{
			Edges.remove(ee.getPosEnArcos());
		}
		catch(InvalidPositionException t){}
		return ret;
	}


	private Vertice<V,E> checkVertex(Vertex<V> v) throws InvalidVertexException{
		if(v == null)
			throw new InvalidVertexException("Vertice vacio");
		else{
			try{
				return (Vertice<V,E>) v;
			}
			catch(ClassCastException e){throw new InvalidVertexException("Vertice NO era un Vertice");}
		}
	}
	private Arco<V,E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		if(e == null)
			throw new InvalidEdgeException("Arco Nulo");
		else{
			try{
				return (Arco<V,E>) e;
			}
			catch(ClassCastException t){throw new InvalidEdgeException("Arco NO era arco");}
		}
	}
	/**
	 * metodo auxiliar que agranda la matriz en caso de que se llene
	 */
	private void agrandarMatriz(){
		Edge<E>[][] nuevo = (Edge<E>[][]) new Arco[arcos.length*2][arcos[0].length*2];
		for(int f = 0; f < arcos.length;f++){
			for(int c = 0; c < arcos[0].length;c++){
				if(arcos[f][c] != null)
					nuevo[f][c] = arcos[f][c];
			}
		}
		arcos = nuevo;
	}
}
