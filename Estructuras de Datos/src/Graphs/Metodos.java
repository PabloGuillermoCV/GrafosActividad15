package Graphs;

import java.util.Iterator;

import ColasConPrioridad.Heap;
import ColasConPrioridad.PriorityQueue;

public class Metodos<V,E> {
	
	Object Estado = new Object();
	Object Visitado = new Object();
	Object NoVisitado = new Object();

	public static<V,E> boolean hayCamino(Graph<V,E> g, Vertex<V> or,Vertex<V> des, Map<Vertex<V>,Boolean> vis){
		boolean hay = false;
		try{
			Vertex<V> op;
			vis.put(or, true);
			if(or == des)
				hay = true;
			else{
				Iterator<Edge<E>> itArc = g.incidentEdges(or).iterator();
				while(itArc.hasNext() && !hay){
					op = g.opposite(or, itArc.next());
					if(!vis.get(op))
						hay = hayCamino(g,op,des,vis);
					
				}
				
			}
		}
		catch(InvalidKeyException e){}
		catch(InvalidVertexException e){}
		catch(InvalidEdgeException e){}
		return hay;
	}
	public static<V,E> boolean hayCiclo(Graph<V,E> g, Vertex<V> v){
		boolean hay = false;
		Vertex<V> op;
		Map<Vertex<V>,Boolean> vis = new MapeoConLista<Vertex<V>,Boolean>();
		try{
			for(Vertex<V> b: g.vertices())
				vis.put(b, false);
			Iterator<Edge<E>> itArc = g.incidentEdges(v).iterator();
			while(itArc.hasNext() && !hay){
				op = g.opposite(v, itArc.next());
				hay = hayCamino(g,op,v,vis);
			}
		}
		catch(InvalidKeyException e){}
		catch(InvalidEdgeException e){}
		catch(InvalidVertexException e){}
		return hay;
	}
	
	public static<V,Float> PositionList<Vertex<V>> hallarCaminoCostoMinimo(Graph<V,Float> G, Vertex<V> Origen, Vertex<V> Destino){
		Solucion Cmin = new Solucion(0);
		Solucion Cact = new Solucion(0);
		Map<Vertex<V>,Boolean> marks = new MapeoConLista<Vertex<V>, Boolean>();
		try{
			for(Vertex<V> v: G.vertices()){
				marks.put(v, false);
			}
		}
		catch(InvalidKeyException e){}
		PositionList<Vertex<V>> caminoAct = new ListaDoblementeEnlazada<Vertex<V>>();
		PositionList<Vertex<V>> caminoMin = new ListaDoblementeEnlazada<Vertex<V>>();
		aux(G,Origen,Destino,caminoAct,Cact,caminoMin,Cmin,marks);
		return caminoMin;
	}
	
	private static<V,Float> void aux(Graph<V, Float> g, Vertex<V> PA, Vertex<V> destino,PositionList<Vertex<V>> caminoAct, Solucion CostA, PositionList<Vertex<V>> caminoMin, Solucion CostM, Map<Vertex<V>,Boolean> m){
		try{
			m.put(PA, true);
			caminoAct.addLast(PA);
			if(PA == destino){ //llegué a donde queria llegar
				if(CostA.menor(CostM)){ //me fijo si es necesario actualizar el costo minimo
					CostM.setCosto(CostA.getCosto());
					for(Vertex<V> v: caminoAct){
						caminoMin.addLast(v); //en caso de haber hallado un nuevo minimo, paso mi camino actual encontrado a mi camino minimo hallado, clone en profundidad?
					}
				}
				else{
					for(Edge<Float> p: g.incidentEdges(PA)){
						Vertex<V> op = g.opposite(PA, p);
						if(!m.get(op)){ //el vertice opuesto a PA tomando el arco p como referencia NO esta visitado
							CostA.setCosto(CostA.getCosto() + (float)p.element());
							aux(g,op,destino,caminoAct,CostA,caminoMin,CostM,m);
						}
					}
				}
			}
			
			caminoAct.remove(caminoAct.last()); //backtracking
			m.put(PA, false);
			
		}
		catch(InvalidKeyException t){}
		catch(InvalidVertexException t){}
		catch(InvalidEdgeException t){}
		catch(InvalidPositionException t){}
		catch(EmptyListException t){}
  }
	
	public int[][] Floyd(Graph<V,Float> G){
		int cant = cant_Vertices1(G);
		int a = 1;
		Vertex<V>[] vertices = (Vertex<V>[]) new Object[cant+1];
		for(Vertex<V> v: G.vertices() ){
			vertices[a] = v;
			a++;
		}
		a = 0;
		float[][] A = new float[cant+1][cant+1];
		int[][] Path = new int[cant+1][cant+1];
		for(int i = 1; i <= cant+1; i++ ){
			for(int j = 1; j <= cant+1; j++){
				try {
					if(G.areAdjacent(vertices[i], vertices[j])){
						A[i][j] = costo(G,vertices[i],vertices[j]);
					}
					else
						A[i][j] = Float.MAX_VALUE;
				} catch (InvalidVertexException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Path[i][j] = 0; //camino directo por defecto
			}
		}
		for(int i = 1; i <= cant+1; i++){
			A[i][i] = 0; //de un vertice a si mismo el camino es directo y no hay peso relacionado
		}
		
		for(int k = 1; k <= cant+1; k++){
			for(int i = 1; i <= cant+1; i++){
				for(int j = 1; j <= cant+1; j++ ){
					if(A[i][k] + A[k][j] < A[i][j]){
						A[i][j] = A[i][k] + A[k][j];
						Path[i][j] = k;
					}
				}
			}
		}
		return Path;
	}
	
	public void recuperarFloyd(int[][] p,Queue<Integer> q, int i, int j ){
		int K = p[i][j];
		if(K != 0){
			recuperarFloyd(p,q,i,K);
			try{
				q.enqueue(K);
			}
			catch(FullQueueException e){}
			recuperarFloyd(p,q,K,j);
		}
	}
	
	
	private float costo(Graph<V,Float> G, Vertex<V> v1, Vertex<V> v2){
		float ret = -1;
		Vertex<V>[] v;
		Iterator<Edge<Float>> it = G.edges().iterator();
		while(it.hasNext() && ret == -1){
			Edge<Float> e = it.next();
			try{
				 v = G.endvertices(e);
			}
			catch(InvalidEdgeException t){return 0;}
			if(v[0] == v1 && v[1] == v2){
				ret = e.element();
			}
		}
		return ret;
	}
	
	private int cant_Vertices1(Graph<V,Float> G){
		int ret = 0;
		for(Vertex<V> v: G.vertices()){
			ret++;
		}
		return ret;
	}
	
	public boolean esConexo(Graph<V,E> G){
		boolean is = false;
		int c = cant_Vertices(G);
		Vertex<V> or = null;
		for(Vertex<V> v: G.vertices()){
			or = v;
			break;
		}
		int a = recorrerConexo(G,or,is,c,0);
		return is;
		
	}
	
	public boolean VarioConexo(Graph<V,E> g){
		int cantA = cantCompConexas(g);
		for(Vertex<V> v: g.vertices()){
			try{
				g.removeVertex(v);
			}
			catch(InvalidVertexException e){}
			break;
		}
		int cantD = cantCompConexas(g);
		if(cantA != cantD)
			return true;
		else
			return false;
	}
	
	public int cantCompConexas(Graph<V,E> g){
		int cant = 0;
		Map<Vertex<V>,Integer> comp = new MapeoConLista<Vertex<V>,Integer>();
		for(Vertex<V> v: g.vertices()){
			try{
				comp.put(v, 0); //marcado inicial de la componente conexa
				v.put(Estado, NoVisitado);
			}
			catch(InvalidKeyException e){}
		}
		for(Vertex<V> v: g.vertices()){
			try{
				if(comp.get(v) == 0){ //hay un vertice que NO fue detectado al haber recorrido el grafo
					cant++; //aumento la cantidad de componentes conexas existentes
					recorrerYContar(g,v,comp,cant); //exploro y marco toda la region conexa encontrada
				}
			}
			catch(InvalidKeyException e){}
		}
		return cant;
	}
	private void recorrerYContar(Graph<V,E> g, Vertex<V> PA, Map<Vertex<V>,Integer> m, int comp){
		try{
			PA.put(Estado, Visitado);
			m.put(PA, comp); //marco que el vertice pertenece a la componente Conexa N° comp
			for(Edge<E> e: g.incidentEdges(PA)){
				Vertex<V> op = g.opposite(PA, e);
				if(op.get(Estado) == NoVisitado)
					recorrerYContar(g,op,m,comp);
			}
			
		}
		catch(InvalidKeyException t){}
		catch(InvalidVertexException t){}
		catch(InvalidEdgeException t){}
		
	}
	
	private int recorrerConexo(Graph<V,E> g, Vertex<V> PA, boolean es, int cantV,int CantA){
		try{
			PA.put(Estado, Visitado);
			CantA++;
			for(Edge<E> e: g.incidentEdges(PA)){
				Vertex<V> op = g.opposite(PA, e);
				if(op.get(Estado) == NoVisitado)
					CantA = recorrerConexo(g,op,es,cantV,CantA);
			}
			if(CantA == cantV)
				es = true;
		}
		catch(InvalidKeyException t){}
		catch(InvalidVertexException t){}
		catch(InvalidEdgeException t){}
		return CantA;
	}
	private int cant_Vertices(Graph<V, E> g) {
		int ret = 0;
		for(Vertex<V> v: g.vertices()){
			try{
				v.put(Estado, NoVisitado);
			}
			catch(InvalidKeyException e){}
			ret++;
		}
		return ret;
	}
	
}
