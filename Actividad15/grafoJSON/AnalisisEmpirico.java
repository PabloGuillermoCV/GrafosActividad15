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




	public class AnalisisEmpirico{
		
		public static void main(String[] args) throws IOException {
			Grafo[] grafosComunes = new Grafo[10];
			Grafo[] grafosConexos = new Grafo[10];
			
			try{
				//-----------------------------------------------------GRAFOS COMUNES-------------------------------------------------
				
				//Caso especial, grafo más chico permitido
				Grafo grafo1 = getGrafo(2,1); 
				System.out.println("Grafo con "+ grafo1.getNodosCount() + " nodos y "+ grafo1.getArcosCount() + " arcos construido");
				
				//Grafo pesado pequeño, tipo de peor caso 
				Grafo grafo2 = getGrafo(5,10); 
				System.out.println("Grafo con "+ grafo2.getNodosCount() + " nodos y "+ grafo2.getArcosCount() + " arcos construido");
				
				//Caso especial: A == N-1 
				Grafo grafo3 = getGrafo(50,49); 
				System.out.println("Grafo con "+ grafo3.getNodosCount() + " nodos y "+ grafo3.getArcosCount() + " arcos construido");
				
				//TIPO DE MEJOR CASO: GRAFO RALO
				Grafo grafo4 = getGrafo(71,900); 
				System.out.println("Grafo con "+ grafo4.getNodosCount() + " nodos y "+ grafo4.getArcosCount() + " arcos construido");
				
				//Caso especial: N == A
				Grafo grafo5 = getGrafo(179,179); 
				System.out.println("Grafo con "+ grafo5.getNodosCount() + " nodos y "+ grafo5.getArcosCount() + " arcos construido");
				
				Grafo grafo6 = getGrafo(190,300); 
				System.out.println("Grafo con "+ grafo6.getNodosCount() + " nodos y "+ grafo6.getArcosCount() + " arcos construido");
				
				Grafo grafo7 = getGrafo(200,15000);
				System.out.println("Grafo con "+ grafo7.getNodosCount() + " nodos y "+ grafo7.getArcosCount() + " arcos construido");

				Grafo grafo8 = getGrafo(420,69870); 
				System.out.println("Grafo con "+ grafo8.getNodosCount() + " nodos y "+ grafo8.getArcosCount() + " arcos construido");
				
				Grafo grafo9 = getGrafo(500,40000);
				System.out.println("Grafo con "+ grafo9.getNodosCount() + " nodos y "+ grafo9.getArcosCount() + " arcos construido");
				
				//PEOR CASO: MAXIMO NUMERO DE NODOS Y ARCOS
				Grafo grafo10 = getGrafo(500,124750); 
				System.out.println("Grafo con "+ grafo10.getNodosCount() + " nodos y "+ grafo10.getArcosCount() + " arcos construido");
				
				
				
				//-----------------------------------------------------GRAFOS CONEXOS------------------------------------------------------------
				
				//Caso Especial: grafo más chico permitido
				Grafo grafo1C = getGrafoConexo(2,1); 
				System.out.println("Grafo Conexo con "+ grafo1C.getNodosCount() + " nodos y "+ grafo1C.getArcosCount() + " arcos construido");

				//Caso especial: N == A
				Grafo grafo2C = getGrafoConexo(10,10); 
				System.out.println("Grafo Conexo con "+ grafo2C.getNodosCount() + " nodos y "+ grafo2C.getArcosCount() + " arcos construido");
				
				Grafo grafo3C = getGrafoConexo(20,30); 
				System.out.println("Grafo Conexo con "+ grafo3C.getNodosCount() + " nodos y "+ grafo3C.getArcosCount() + " arcos construido");
				
				//TIPO DE MEJOR CASO: GRAFO RALO
				Grafo grafo4C = getGrafoConexo(80,100); 
				System.out.println("Grafo con "+ grafo4C.getNodosCount() + " nodos y "+ grafo4C.getArcosCount() + " arcos construido");
				
				Grafo grafo5C = getGrafoConexo(124,6999); 
				System.out.println("Grafo Conexo con "+ grafo5C.getNodosCount() + " nodos y "+ grafo5C.getArcosCount() + " arcos construido");
				
				Grafo grafo6C = getGrafoConexo(173,10000); 
				System.out.println("Grafo Conexo con "+ grafo6C.getNodosCount() + " nodos y "+ grafo6C.getArcosCount() + " arcos construido");
				
				//Caso especial: A == N-1
				Grafo grafo7C = getGrafoConexo(211,210); 
				System.out.println("Grafo Conexo con "+ grafo7C.getNodosCount() + " nodos y "+ grafo7C.getArcosCount() + " arcos construido");
				
				
				Grafo grafo8C = getGrafoConexo(300,41258); 
				System.out.println("Grafo Conexo con "+ grafo8C.getNodosCount() + " nodos y "+ grafo8C.getArcosCount() + " arcos construido");
				
				Grafo grafo9C = getGrafoConexo(361,500); 
				System.out.println("Grafo Conexo con "+ grafo9C.getNodosCount() + " nodos y "+ grafo9C.getArcosCount() + " arcos construido");
				
				//PEOR CASO: MAXIMO NUMERO DE NODOS Y ARCOS Y CONEXO
				Grafo grafo10C = getGrafoConexo(500,124750); 
				System.out.println("Grafo Conexo con "+ grafo10C.getNodosCount() + " nodos y "+ grafo10C.getArcosCount() + " arcos construido");
				

				
				grafosComunes[0] = grafo1;
				grafosComunes[1] = grafo2;
				grafosComunes[2] = grafo3;
				grafosComunes[3] = grafo4;
				grafosComunes[4] = grafo5;
				grafosComunes[5] = grafo6;
				grafosComunes[6] = grafo7;
				grafosComunes[7] = grafo8;
				grafosComunes[8] = grafo9;
				grafosComunes[9] = grafo10;
	
				grafosConexos[0] = grafo1C;
				grafosConexos[1] = grafo2C;
				grafosConexos[2] = grafo3C;
				grafosConexos[3] = grafo4C;
				grafosConexos[4] = grafo5C;
				grafosConexos[5] = grafo6C;
				grafosConexos[6] = grafo7C;
				grafosConexos[7] = grafo8C;
				grafosConexos[8] = grafo9C;
				grafosConexos[9] = grafo10C;
				
		
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			//para cada grafo Comun creado, ejecuto el problema 1 según las 2 variantes y tomo los tiempos 
			for(int i = 0; i < grafosComunes.length; i++) {
				double acumulator = 0;
				//creacion de estampillas de tiempo y tomado del tiempo para el problema 1 con BFS
				EDGrafoListaAdyacencias ED = new EDGrafoListaAdyacencias(grafosComunes[i]);
				
				for(int v = 0; v < 100; v++) {
					double timeI = System.nanoTime();
					ConexoBFS p1A = new ConexoBFS(ED);
					p1A.checkConexo();	
					double timeF = System.nanoTime();
					double dif = timeF - timeI;
					acumulator += dif;
				}
				
				acumulator /= 100;
			
				System.out.println("el tiempo para el Problema 1 por BFS para el grafo" + (i+1) + " es: " + (acumulator/1000000) );
				acumulator = 0;
				//creacion de estampillas de tiempo y tomado del tiempo para el problema 1 con Disjoint-Set
				
				for(int v = 0; v < 100; v++) {
					double timeI2 = System.nanoTime();
					ConexoDisjointSet p1A2 = new ConexoDisjointSet(ED);
					p1A2.checkConexo();
					double timeF2 = System.nanoTime();
					double dif2 = timeF2 - timeI2;
					acumulator += dif2;
				}
				
				
				acumulator /= 100;
				
				System.out.println("el tiempo para el Problema 1 por Disjoint-Set para el grafo" + (i+1) + " es: " + (acumulator/1000000) );
			}
			
			//para cada grafo Conexo creado, ejecuto el problema 2 según las 4 variantes y tomo los tiempos
			for(int i = 0; i < grafosConexos.length; i++) {
				double acumulator = 0;
				EDGrafoListaAdyacencias ED = new EDGrafoListaAdyacencias(grafosConexos[i]);
			
				//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Arreglo Ordenado y Disjoint-Set SIN Heuristicas
				
				for(int v = 0; v < 100; v++) {
					KruskalOrdenadoSH p2A2 = new KruskalOrdenadoSH(ED);
					double timeI2 = System.nanoTime();
					p2A2.Kruskal();
					double timeF2 = System.nanoTime();
					double dif2 = timeF2 - timeI2;
					acumulator += dif2;
				}
				
				acumulator /= 100;
				
				System.out.println("el tiempo para el Problema 2 por Lista Ordenada con Disjoint-Set SIN Heuristica para el grafo Conexo grafo" + (i+1) + "C " + " es: " + (acumulator/1000000) );
				
				acumulator = 0;
				//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Arreglo Ordenado y Disjoint-Set CON Heuristicas
				
				for(int v = 0; v < 100; v++) {
					double timeI = System.nanoTime();
					KruskalArcosOrdenados p2A = new KruskalArcosOrdenados(ED);
					p2A.Kruskal();
					double timeF = System.nanoTime();
					double dif = timeF - timeI;
					acumulator += dif;
				}
				
		
				acumulator /= 100;
				System.out.println("el tiempo para el Problema 2 por Lista Ordenada con Disjoint-Set CON Heuristica para el grafo Conexo grafo" + (i+1) + "C " + " es: " + (acumulator/1000000) );
				
				acumulator = 0;
				//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Heap y Disjoint-Set SIN Heuristicas
				
				for(int v = 0; v < 100; v++) {
					double timeI4 = System.nanoTime();
					KruskalHeapSH p2A4 = new KruskalHeapSH(ED);
					p2A4.minimumSpanningTree();
					double timeF4 = System.nanoTime();
					double dif4 = timeF4 - timeI4;
					acumulator += dif4;
				}
				acumulator /= 100;
				System.out.println("el tiempo para el Problema 2 por Heap con Disjoint-Set SIN Heuristica para el grafo Conexo grafo" + (i+1) + "C " + " es: " + (acumulator/1000000) );
			
				acumulator = 0;
				//creacion de estampillas de tiempo y tomado del tiempo para el problema 2 con Heap y Disjoint-Set CON Heuristicas
				
				for(int v = 0; v < 100; v++) {
					double timeI3 = System.nanoTime();
					KruskalHeapCH p2A3 = new KruskalHeapCH(ED);
					p2A3.minimumSpanningTree();
					double timeF3 = System.nanoTime();
					double dif3 = timeF3 - timeI3;
					acumulator += dif3;
				}
				acumulator /= 100;
				
				System.out.println("el tiempo para el Problema 2 por Heap con Disjoint-Set CON Heuristica para el grafo Conexo grafo" + (i+1) + "C " + " es: " + (acumulator/1000000) );
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