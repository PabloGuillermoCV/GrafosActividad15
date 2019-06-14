package grafoJSON;
import java.util.ArrayList;



public class Pesado {
	
		private Arco arco;
		private int peso;
		
		Pesado(ArrayList<Integer> arcoLista, int peso) {
			// TODO Auto-generated constructor stub
			this.arco = new Arco(arcoLista.get(0), arcoLista.get(1));
			this.peso = peso;
		}

		public Arco getArco(){
			return arco;
		}

		public int getPeso(){
			return peso;
		}

		
	
}
