package grafoJSON;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Ejer1a.ConexoBFS;
import Ejer1a.ConexoDisjointSet;
import grafo.EDGrafoListaAdyacencias;
import kruskalHeap.KruskalHeapCH;
import kruskalHeap.KruskalHeapSH;
import kruskalLista.KruskalArcosOrdenados;
import kruskalLista.KruskalOrdenadoSH;

import java.util.Date;
import java.sql.Timestamp;


	public class AnalisisEmpirico{
		
		public static void main(String[] args) throws IOException {
			
			try{
				Grafo grafo1 = getGrafo(500,40000);
				System.out.println("Grafo con "+ grafo1.getNodosCount() + " nodos y "+ grafo1.getArcosCount() + " arcos construido");
				
				//Grafo pesado pequeño, tipo de peor caso 
				Grafo grafo2 = getGrafo(5,10); 
				System.out.println("Grafo con "+ grafo2.getNodosCount() + " nodos y "+ grafo2.getArcosCount() + " arcos construido");
				
				//PEOR CASO: MAXIMO NUMERO DE NODOS Y ARCOS
				Grafo grafo3 = getGrafo(500,124750); 
				System.out.println("Grafo con "+ grafo3.getNodosCount() + " nodos y "+ grafo3.getArcosCount() + " arcos construido");
				
				Grafo grafo4 = getGrafo(200,15000);
				System.out.println("Grafo con "+ grafo4.getNodosCount() + " nodos y "+ grafo4.getArcosCount() + " arcos construido");
				
				//TIPO DE MEJOR CASO: GRAFO RALO
				Grafo grafo5 = getGrafo(71,900); 
				System.out.println("Grafo con "+ grafo5.getNodosCount() + " nodos y "+ grafo5.getArcosCount() + " arcos construido");
				
				Grafo grafo6 = getGrafo(190,300); 
				System.out.println("Grafo con "+ grafo6.getNodosCount() + " nodos y "+ grafo6.getArcosCount() + " arcos construido");
				
				Grafo grafo7 = getGrafo(420,69870); 
				System.out.println("Grafo con "+ grafo7.getNodosCount() + " nodos y "+ grafo7.getArcosCount() + " arcos construido");
				
				//Caso especial, grafo más chico permitido
				Grafo grafo8 = getGrafo(2,1); 
				System.out.println("Grafo con "+ grafo8.getNodosCount() + " nodos y "+ grafo8.getArcosCount() + " arcos construido");
				
				//Caso especial: N == A
				Grafo grafo9 = getGrafo(179,179); 
				System.out.println("Grafo con "+ grafo9.getNodosCount() + " nodos y "+ grafo9.getArcosCount() + " arcos construido");
				
				//Caso especial: A == N-1 
				Grafo grafo10 = getGrafo(50,49); 
				System.out.println("Grafo con "+ grafo10.getNodosCount() + " nodos y "+ grafo10.getArcosCount() + " arcos construido");
				
				//TIPO DE MEJOR CASO: GRAFO RALO
				Grafo grafo1C = getGrafoConexo(80,100); 
				System.out.println("Grafo con "+ grafo1C.getNodosCount() + " nodos y "+ grafo1C.getArcosCount() + " arcos construido");
				
				//PEOR CASO: MAXIMO NUMERO DE NODOS Y ARCOS Y CONEXO
				Grafo grafo2C = getGrafoConexo(500,124750); 
				System.out.println("Grafo Conexo con "+ grafo2C.getNodosCount() + " nodos y "+ grafo2C.getArcosCount() + " arcos construido");
				
				Grafo grafo3C = getGrafoConexo(20,30); 
				System.out.println("Grafo Conexo con "+ grafo3C.getNodosCount() + " nodos y "+ grafo3C.getArcosCount() + " arcos construido");
				
				Grafo grafo4C = getGrafoConexo(361,500); 
				System.out.println("Grafo Conexo con "+ grafo4C.getNodosCount() + " nodos y "+ grafo4C.getArcosCount() + " arcos construido");
				
				//Caso especial: A == N-1
				Grafo grafo5C = getGrafoConexo(211,210); 
				System.out.println("Grafo Conexo con "+ grafo5C.getNodosCount() + " nodos y "+ grafo5C.getArcosCount() + " arcos construido");
				
				Grafo grafo6C = getGrafoConexo(124,6999); 
				System.out.println("Grafo Conexo con "+ grafo6C.getNodosCount() + " nodos y "+ grafo6C.getArcosCount() + " arcos construido");
				
				//Caso Especial: grafo más chico permitido
				Grafo grafo7C = getGrafoConexo(2,1); 
				System.out.println("Grafo Conexo con "+ grafo7C.getNodosCount() + " nodos y "+ grafo7C.getArcosCount() + " arcos construido");
				
				Grafo grafo8C = getGrafoConexo(173,10000); 
				System.out.println("Grafo Conexo con "+ grafo8C.getNodosCount() + " nodos y "+ grafo8C.getArcosCount() + " arcos construido");
				
				//Caso especial: N == A
				Grafo grafo9C = getGrafoConexo(10,10); 
				System.out.println("Grafo Conexo con "+ grafo9C.getNodosCount() + " nodos y "+ grafo9C.getArcosCount() + " arcos construido");
				
				Grafo grafo10C = getGrafoConexo(300,41258); 
				System.out.println("Grafo Conexo con "+ grafo10C.getNodosCount() + " nodos y "+ grafo10C.getArcosCount() + " arcos construido");
				
				Grafo[] grafosComunes = {grafo1, grafo2, grafo3, grafo4, grafo5, grafo6, grafo7, grafo8, grafo9, grafo10};
				Grafo[] grafosConexos = {grafo1C, grafo2C, grafo3C, grafo4C, grafo5C, grafo6C, grafo7C, grafo8C, grafo9C, grafo10C};
				
				//para cada grafo Comun creado, ejecuto el problema 1 según las 2 variantes y tomo los tiempos 
				for(int i = 0; i < grafosComunes.length; i++) {
					System.out.println("Estoy en el for de grafos comunes");
					//creacion de estampillas de tiempo y tomado del tiempo para el problema 1 con BFS
					EDGrafoListaAdyacencias ED = new EDGrafoListaAdyacencias(grafosComunes[i]);
					Date date = new Date();
					long timeI = date.getTime();
					Timestamp tI = new Timestamp(timeI);
					ConexoBFS p1A = new ConexoBFS(ED);
					p1A.checkConexo();
					long timeF = date.getTime();
					Timestamp tF = new Timestamp(timeF);
					long dif = tF.getTime() - tI.getTime();
					System.out.println("el tiempo para el Problema 1 por BFS para el grafo" + i+1 + "es: " + dif );
					
					//creacion de estampillas de tiempo y tomado del tiempo para el problema 1 con Disjoint-Set
					Date date2 = new Date();
					long timeI2 = date2.getTime();
					Timestamp tI2 = new Timestamp(timeI2);
					ConexoDisjointSet p1A2 = new ConexoDisjointSet(ED);
					p1A2.checkConexo();
					long timeF2 = date.getTime();
					Timestamp tF2 = new Timestamp(timeF2);
					long dif2 = tF2.getTime() - tI2.getTime();
					System.out.println("el tiempo para el Problema 1 por Disjoint-Set para el grafo" + i+1 + "es: " + dif2 );
				}
				
				//para cada grafo Conexo creado, ejecuto el problema 2 según las 4 variantes y tomo los tiempos
				for(int i = 0; i < grafosConexos.length; i++) {
					System.out.println("Estoy en el for de grafos conexos");
					//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Arreglo Ordenado y Disjoint-Set CON Heuristicas
					EDGrafoListaAdyacencias ED = new EDGrafoListaAdyacencias(grafosConexos[i]);
					Date date = new Date();
					long timeI = date.getTime();
					Timestamp tI = new Timestamp(timeI);
					KruskalArcosOrdenados p2A = new KruskalArcosOrdenados(ED);
					p2A.Kruskal();
					long timeF = date.getTime();
					Timestamp tF = new Timestamp(timeF);
					long dif = tF.getTime() - tI.getTime();
					System.out.println("el tiempo para el Problema 2 por Lista Ordenada con Disjoint-Set CON Heuristica para el grafo Conexo grafo" + i+1 + "C " + "es: " + dif );
					
					
					//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Arreglo Ordenado y Disjoint-Set SIN Heuristicas
					Date date2 = new Date();
					long timeI2 = date2.getTime();
					Timestamp tI2 = new Timestamp(timeI2);
					KruskalOrdenadoSH p2A2 = new KruskalOrdenadoSH(ED);
					p2A2.Kruskal();
					long timeF2 = date2.getTime();
					Timestamp tF2 = new Timestamp(timeF2);
					long dif2 = tF2.getTime() - tI2.getTime();
					System.out.println("el tiempo para el Problema 2 por Lista Ordenada con Disjoint-Set SIN Heuristica para el grafo Conexo grafo" + i+1 + "C " + "es: " + dif2 );
					
					
					//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Heap y Disjoint-Set CON Heuristicas
					Date date3 = new Date();
					long timeI3 = date3.getTime();
					Timestamp tI3 = new Timestamp(timeI3);
					KruskalHeapCH p2A3 = new KruskalHeapCH(ED);
					p2A3.minimumSpanningTree();
					long timeF3 = date3.getTime();
					Timestamp tF3 = new Timestamp(timeF3);
					long dif3 = tF3.getTime() - tI3.getTime();
					System.out.println("el tiempo para el Problema 2 por Heap con Disjoint-Set CON Heuristica para el grafo Conexo grafo" + i+1 + "C " + "es: " + dif3 );
					
					
					
					//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Heap y Disjoint-Set SIN Heuristicas
					Date date4 = new Date();
					long timeI4 = date4.getTime();
					Timestamp tI4 = new Timestamp(timeI4);
					KruskalHeapSH p2A4 = new KruskalHeapSH(ED);
					p2A4.minimumSpanningTree();
					long timeF4 = date4.getTime();
					Timestamp tF4 = new Timestamp(timeF4);
					long dif4 = tF4.getTime() - tI4.getTime();
					System.out.println("el tiempo para el Problema 2 por Heap con Disjoint-Set SIN Heuristica para el grafo Conexo grafo" + i+1 + "C " + "es: " + dif4 );
				}
		
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			/*
			 * Generar varios grafos de diferente configuracion y buscar 
			 * arbol de cubrimiento minimal para cada uno. 
			 * 
			 * Medir el rendimiento usando timestamps. (es una clase en java.util.Date)
			 * 
			 */
			
			
		}

		private static Grafo getGrafo(int nodos, int arcos) throws Exception {
			// TODO Auto-generated method stub
			String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos;
			Process process = Runtime.getRuntime().exec(consulta);
			InputStream inputSt = process.getInputStream();
			@SuppressWarnings("resource")
			Scanner s = new Scanner(inputSt).useDelimiter("\\A");
			String jsonString = s.hasNext() ? s.next() : "";
			System.out.println("Tengo el grafo en formato JSON. Lo convierto...");
			Gson gson = new GsonBuilder().create();
			try{
				Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
				return new Grafo(gr);
			} catch (Exception e) {
				throw new Exception(jsonString);
			}
		}
		
		private static Grafo getGrafoConexo(int nodos, int arcos) throws Exception {
			// TODO Auto-generated method stub
			String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos+"&conexo=1";
			Process process = Runtime.getRuntime().exec(consulta);
			InputStream inputSt = process.getInputStream();
			@SuppressWarnings("resource")
			Scanner s = new Scanner(inputSt).useDelimiter("\\A");
			String jsonString = s.hasNext() ? s.next() : "";
			System.out.println("Tengo el grafo en formato JSON. Lo convierto...");
			Gson gson = new GsonBuilder().create();
			try{
				Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
				return new Grafo(gr);
			} catch (Exception e) {
				throw new Exception(jsonString);
			}
		}
	}