package pucrs.alpro2.tf.collection;

import java.util.Iterator;

public interface ILista<E> {
	void add(E e);
	void addLast(E e);
	Iterator<E> iterator();
	E get(int i);
	E getFirst();
	int size();
	void clear();
}
