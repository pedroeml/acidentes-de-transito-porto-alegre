package pucrs.alpro2.tf.collection;

import java.util.Iterator;

public class IteratorRemoveTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListDoubleLinked<Integer> l = new ListDoubleLinked<>();
		
		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		printList(l);
		
		Iterator<Integer> it = l.iterator();
		for (int e = it.next(); it.hasNext(); e = it.next()) {
			if (e == 30)
				it.remove();
		}
		
		printList(l);
	}
	
	
	private static void printList(ListTAD<Integer> l) {
		
		System.out.print("[");
		for (Iterator<Integer> it = l.iterator(); it.hasNext(); ){
			int e = it.next();
			System.out.printf((it.hasNext() ? "%d, " : "%d"), e);
			
		}
		System.out.print("]");
	}
}
