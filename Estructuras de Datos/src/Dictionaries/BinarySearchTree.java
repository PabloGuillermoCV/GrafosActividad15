package Dictionaries;

import Arboles.InvalidPositionException;

public class BinarySearchTree<K,V> extends Dictionaries.ArbolBinario<Entry<K,V>> {

	protected Comparator<K> C;
	protected int size;
	protected Position<Entry<K,V>> actionPos;
	
	protected static class BSTEntry<K,V> implements Entry<K,V>{
		protected K key;
		protected V value;
		protected Position<Entry<K,V>> pos;
		BSTEntry(){}
		BSTEntry(K k, V v, Position<Entry<K,V>> p){
			key = k;
			value = v;
			pos = p;
		}
		public K getKey(){return key;}
		public V getValue(){return value;}
		public void setValue(V val){value = val;}
		public void setKey(K k){key = k;}
		public Position<Entry<K,V>>position(){return pos;}
	}
	
	protected K key(Position<Entry<K,V>> pos){
		return pos.element().getKey();
	}
	protected V value(Position<Entry<K,V>> pos){
		return pos.element().getValue();
	}
	protected Entry<K,V> entry(Position<Entry<K,V>>pos){
		return pos.element();
	}
	protected void replaceEntry(Position<Entry<K, V>> pos, Entry<K,V> ent){
		((BSTEntry<K,V>)ent).pos = pos;
		Entry<K,V> e = pos.element();
		e.setKey(ent.getKey());
		e.setValue(ent.getValue());
	}
	
	protected Entry<K,V> insertAtExternal(Position<Entry<K,V>> parent, Entry<K,V> e){
			
		try {
			addLeft(parent,new Entrada<K,V>(null,null));
			addRight(parent,new Entrada<K,V>(null,null));
			replace(parent,e);
			size++;
			return e;
		} catch (InvalidOperationException | Dictionaries.InvalidPositionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
	
	public BinarySearchTree(Comparator<K> c){
		C = c;
		try {
			createRoot(null);
		}
		catch(InvalidOperationException e){}
		
	}
	
	protected Position<Entry<K,V>> treeSearch(K key, Position<Entry<K, V>> position){
		try {
			if(isExternal(position)) return position;
			else{
				K curKey = key(position);
				int comp = C.compare(key, curKey);
				if(comp < 0)
					return treeSearch(key,left(position));
				else if(comp > 0)
					return treeSearch(key,right(position));
			}
		}
		catch(BoundaryViolationException e){} 
		catch (Dictionaries.InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return position;
	}
	
	
}
