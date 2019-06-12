package Graphs;

import java.util.Iterator;

public class GrafoDConListaDeAdyacencias<V,E> implements GraphD<V, E> {

	private PositionList<Vertice<V,E>> nodos;
	private PositionList<Arco<V,E>> arcos;
	
	private class Arco<V,E>  extends MapeoConLista<Object,Object> implements Edge<E>{
		protected E rot;
		protected Vertice<V,E> v1;
		protected Vertice<V,E> v2;
		protected Position<Arco<V,E>> posEnArcos;
		protected Position<Arco<V,E>> posv1;
		protected Position<Arco<V,E>> posv2;
		public Arco(E r, Vertice<V,E> ini, Vertice<V,E> fin){
			rot = r;
			v1 = ini;
			v2 = fin;
		}
		public E element(){
			return rot;
		}
		public void serRotulo(E r){
			rot = r;
		}
		public void setV1(Vertice<V,E> ini){
			v1 = ini;
		}
		public void setV2(Vertice<V,E> fin){
			v2 = fin;
		}
		public void setPosEnArcos(Position<Arco<V,E>> p){
			posEnArcos = p;
		}
		public void setPosv1(Position<Arco<V,E>> p){
			posv1 = p;
		}
		public void setPosv2(Position<Arco<V,E>> p){
			posv2 = p;
		}
		public Vertice<V,E> getIni(){
			return v1;
		}
		public Vertice<V,E> getFin(){
			return v2;
		}
		public Position<Arco<V,E>> getPosEnArcos(){
			return posEnArcos;
		}
		public Position<Arco<V,E>> getPosv1(){
			return posv1;
		}
		public Position<Arco<V,E>> getPosv2(){
			return posv2;
		}
	}
	private class Vertice<V,E> extends MapeoConLista<Object,Object> implements Vertex<V>{
		protected V rot;
		protected PositionList<Arco<V,E>> incident;
		protected PositionList<Arco<V,E>> succesor;
		protected Position<Vertice<V,E>> posEnNodos;
		public Vertice(V r){
			rot = r;
			incident = new ListaDoblementeEnlazada<Arco<V,E>>();
			succesor = new ListaDoblementeEnlazada<Arco<V,E>>();
		}
		public V element(){
			return rot;
		}
		public void setRotulo(V r){
			rot = r;
		}
		public void setPosEnNodos(Position<Vertice<V,E>> p){
			posEnNodos = p;
		}
		public PositionList<Arco<V,E>> getIncident(){
			return incident;
		}
		public PositionList<Arco<V,E>> getSuccesor(){
			return succesor;
		}
		public Position<Vertice<V,E>> getPosEnNodos(){
			return posEnNodos;
		}
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
		PositionList<Edge<E>> ret = new ListaDoblementeEnlazada<Edge<E>> ();
		for(Edge<E> e: arcos){
			ret.addLast(e);
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
		for(Edge<E> e: vv.getIncident()){
			ret.addLast(e);
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
		Vertice<V,E> vv = checkVertex(v);
		PositionList<Edge<E>> ret = new ListaDoblementeEnlazada<Edge<E>>();
		for(Edge<E> e: vv.getSuccesor()){
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
		//asumo que v es inicio del arco e
		Vertice<V,E> vv = checkVertex(v);
		Arco<V,E> ee = checkEdge(e);
		if(ee.getFin() == vv)
			throw new InvalidVertexException("Vertice era fin, NO es L�gico");
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
		Vertex<V>[] ret = (Vertex<V>[]) new Vertex[2];
		Arco<V,E> ee = checkEdge(e);
		if(ee.getIni() == null || ee.getFin() == null){
			throw new InvalidEdgeException("Arco Incompleto???");
		}
		else{
			ret[0] = ee.getIni();
			ret[1] = ee.getFin();
		}
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
		boolean found = false;
		Iterator<Arco<V,E>> it = vv.getSuccesor().iterator();
		//me fijo si aolgun arco saliente de v contiene a w
		while(it.hasNext() && !found){
			Arco<V,E> aux = it.next();
			if(aux.getFin() == ww)
				found = true;
		}
		//me fijo si algun arco saliente de w contiene a v
		it = ww.getSuccesor().iterator();
		while(it.hasNext() && !found){
			Arco<V,E> aux = it.next();
			if(aux.getFin() == vv)
				found = true;
		}
		return found;
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
		Vertice<V,E> ret = new Vertice<V,E> (x);
		nodos.addLast(ret);
		try{
		ret.setPosEnNodos(nodos.last());
		}
		catch(EmptyListException e){}
		return ret;
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
		Arco<V,E> ret = new Arco<V,E>(e,vv,ww);
		arcos.addLast(ret);
		vv.getSuccesor().addLast(ret);
		ww.getIncident().addLast(ret);
		try{
			ret.setPosEnArcos(arcos.last());
			ret.setPosv1(vv.getSuccesor().last());
			ret.setPosv2(ww.getIncident().last());
		}
		catch(EmptyListException t){}
		return ret;
		
	}
	
	/**
	 * Remueve un v�rtice V y retorna su r�tulo.
	 * @param v Un v�rtice
	 * @return r�tulo de V.
	 * @throws InvalidVertexException si el v�rtice es inv�lido.
	 */
	public V removeVertex(Vertex<V> v) throws InvalidVertexException{
		V ret = null;
		Vertice<V,E> vv = checkVertex(v);
		PositionList<Arco<V,E>> removidos = new ListaDoblementeEnlazada<Arco<V,E>>();
		Iterator<Arco<V,E>> it = vv.getIncident().iterator();
		try{
			//borro primero los arcos incidentes a v
			while(it.hasNext()){
				Arco<V,E> aux = it.next();
				removidos.addLast(aux);
				aux.getIni().getSuccesor().remove(aux.getPosv1());
				vv.getIncident().remove(aux.getPosv2());
				arcos.remove(aux.getPosEnArcos());
			}
			it = vv.getSuccesor().iterator();
			//borro los arcos sucesores a v
			while(it.hasNext()){
				Arco<V,E> aux = it.next();
				if(!esta(aux,removidos)){ //evito querer borrar arcos YA borrados
					aux.getFin().getIncident().remove(aux.getPosv2());
					vv.getSuccesor().remove(aux.getPosv1());
					arcos.remove(aux.getPosEnArcos());
				}
			}
			ret = vv.element();
			nodos.remove(vv.getPosEnNodos());
		}
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
		E ret = null;
		Arco<V,E> ee = checkEdge(e);
		try{
			ret = ee.element();
			ee.getIni().getSuccesor().remove(ee.getPosv1());
			ee.getFin().getIncident().remove(ee.getPosv2());
			arcos.remove(ee.getPosEnArcos());
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
	private boolean esta(Arco<V,E> b, PositionList<Arco<V,E>> l){
		boolean found = false;
		Iterator<Arco<V,E>> it = l.iterator();
		while(it.hasNext() && !found){
			if(it.next() == b)
				found = true;
		}
		return found;
	}
}
