package Dictionaries;

public class Prueba {

	public static void main(String[] args) {
		Dictionary<Integer,String> p = new DiccionarioABB<Integer,String>(new Comparator<Integer>());
		try{
		p.insert(5, "Leo");
		p.insert(4, "Lone");
		p.insert(10, "mayor");
		p.insert(11, "creo");
		p.insert(3, "guard");
		System.out.println(p.size());
		System.out.println(p.toString());
		System.out.println(p.find(11));
		System.out.println(p.findAll(11));
		}
		catch(InvalidKeyException e){}
		
	}

}
