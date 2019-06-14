package grafoJSON;

public class Arco {
	
		private int nodo1;
		private int nodo2;
		
		public Arco(int i, int j) {
			// TODO Auto-generated constructor stub
			this.nodo1 = i;
			this.nodo2 = j;
		}

		public int getNodoSource(){
			return nodo1;
		}

		public int getNodoTarget(){
			return nodo2;
		}
	
}
