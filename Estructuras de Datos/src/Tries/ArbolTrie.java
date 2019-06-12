package Tries;

public class ArbolTrie<E> {

	protected NodoTrie<E> raiz;
	
	public ArbolTrie(){
		raiz = new NodoTrie<E>(null);
	}
	
	public void insert(String clave, E valor){
		insertRec(clave,valor,0,clave.length(),raiz,null);
	}
	
	private void insertRec(String C, E v, int i, int length, NodoTrie<E> PA, NodoTrie<E> PPA){
		if(i < length){
			int indice = (int)C.charAt(i) - (int)'a';
			if(PA.getHijo(indice) == null)
				PA.setHijo(indice,new NodoTrie<E>(PA));
			insertRec(C,v,i+1,length,PA.getHijo(indice),PA);
		}
		else{ //i = n, terminé de insertar todo el string, dejo la clave asociada al ultimo lugar del string (con eso tambien denoto prefijos)
			PA.setImagen(v);
		}
	}
	public E lookUp(String clave){
		return lookUpRec(clave,0,clave.length(),raiz);
	}
	
	private E lookUpRec(String C, int i, int length, NodoTrie<E> PA){
		E ret = null;
		if(i == length){
			ret = PA.getImagen();
		}
		else{
			int indice = (int)C.charAt(i) - (int)'a';
			if(PA.getHijo(indice) == null)
				ret = null; //la clave NO existe
			else{
				lookUpRec(C,i+1,length,PA.getHijo(indice));
			}
		}
		return ret;
	}
	
	public E Remove(String clave) throws ClaveInexistenteException{
		return RemoveRec(clave,0,clave.length(),raiz,0);
	}
	
	private E RemoveRec(String C, int i, int length, NodoTrie<E> PA, int IndexPA) throws ClaveInexistenteException{
		E ret = null;
		if(i == length){
			if(PA.getImagen() == null)
				throw new ClaveInexistenteException("Clave Inexistente");
			else{
				ret = PA.getImagen();
				PA.setImagen(null);
				if(raiz != PA){
					PA.getPadre().setHijo(IndexPA, null);
					PA.setPadre(null);
					return ret;
				}
				
			}
			
		}
		else{
			int indice = (int)C.charAt(i) - (int)'a';
			if(PA.getHijo(indice) == null)
				throw new ClaveInexistenteException("Clave Inexistente");
			else
				return RemoveRec(C,i+1,length,PA.getHijo(indice),indice);
			
		}
		return ret;
	}
}
