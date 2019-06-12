package Maps;

public class Prueba {
  public static void main(String[] args){
	  Map<Integer,String> al = new MapeoABB<Integer,String>(new Comparator<Integer>());
	  Map<Integer,String> al2 = new MapeoConHashAbierto<Integer,String>(1023);
	  Map<Integer,String> al3 = new MapeoConHashCerrado<Integer,String>();
	  try{
		  al.put(1, "Reo");
		  al.put(2, "teo");
		  System.out.println(al.size());
		  System.out.println("arbol ABB " + al.get(1));
		  System.out.println("arbol ABB " + al.get(2));
		  al2.put(1,"Pablo");
		  al2.put(2, "Sergio");
		  al2.put(4, "keystone");
		  al2.put(3, "Prueba");
		  al3.put(2, "Prueba");
		  System.out.println("al3" + al3.get(2));
		  al3.put(2, "Hey");
		  al3.put(10, "sen");
		  al3.put(100, "Gen");
		  System.out.println("Entries de al3" + al3.entries());
		  System.out.println(("Hay " + al2.size() + " Entradas"));
		  System.out.println(al2.get(1));
		  System.out.println(al2.get(2));
		  System.out.println(al2.get(3));
		  System.out.println(al2.get(4));
		  System.out.println("elimine la entrada cuyo valor era " + al2.remove(4));
		  System.out.println(al2.remove(5));
		  System.out.println(al2.remove(1));
		  System.out.println(al2.get(4));
	  }
	  catch(InvalidKeyException e){}
  }
}
