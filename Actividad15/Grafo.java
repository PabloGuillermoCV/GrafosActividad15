import java.util.ArrayList;

/* 
	que un grafo tenga N-1 arcos no implica que sea conexo
	con disjoint set se chequea usando Findset, recorriendo la lista de arcos, SIN recorrer el grafo
	con BFS se corrobora si se genera una foresta con una raiz 
		(se podria coratar antes al encontrar un No conexo)
	Punto 2:
		Lista Ordenada
			-DS con Heurísticas
			-DS sin Heurísticas
		Heap
			-DS con heurísticas
			-DS sin Heurísticas
	
	la Estructura Grafo la tenemos que armar nosotros, el grafo dado acá no es eficiente
	el modelo es una construcción matematica
	n es una lista de nodos
	a es una lista de arcos, donde cada arco tiene un inicio, un fin y un peso
	podemos dejar este modelo y traducir lo que da el modelo a nuestra implementación más eficiente!!!

	el problema del análisis del tiempo empiezan una vez creada la estructura

	primero llamamos al grafo
	ti = creamos un timestamp inicial
	llamamos a un metodo
	tf = verificamos el timestamp final
	el timestamp que va en la tabla es tf - ti

	G <- grafo(n,a)
	ti <- timestamp
	kruskalHCH
	tf <- timestamp
	kru

*/

public class Grafo {
	private int[] nodos;
	private ArrayList<Pesado> arcos;

	private class Pesado {
		private Arco arco;
		private int peso;
		
		private Pesado(ArrayList<Integer> arcoLista, int peso) {
			// TODO Auto-generated constructor stub
			this.arco = new Arco(arcoLista.get(0), arcoLista.get(1));
			this.peso = peso;
		}

		private class Arco {
			private int nodo1;
			private int nodo2;
			
			public Arco(int i, int j) {
				// TODO Auto-generated constructor stub
				this.nodo1 = i;
				this.nodo2 = j;
			}
		}
	}
	
	public int getNodosCount(){
		return this.nodos.length;
	}
	
	public int getArcosCount(){
		return this.arcos.size();
	}
	

	@SuppressWarnings("rawtypes")
	public Grafo(GrafoObj grafoJson){
		this.nodos = grafoJson.nodos;
		this.arcos = new ArrayList<Pesado>();
		
		Object[][] arcosJson = grafoJson.arcos;
		
		for (int i = 0; i<arcosJson.length; i++){
			
			ArrayList<Integer> arcoLista = new ArrayList<>(); 
			arcoLista.add(((Double) ((ArrayList) arcosJson[i][0]).get(0)).intValue());
			arcoLista.add(((Double) ((ArrayList) arcosJson[i][0]).get(1)).intValue());
			Pesado pesado = new Pesado(arcoLista, ((Double) arcosJson[i][1]).intValue());
			this.arcos.add(pesado); 
		}
	}
	
	public static class GrafoObj {
		int[] nodos;
		Object[][] arcos;
	}

}