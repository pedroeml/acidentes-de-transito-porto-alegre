package pucrs.alpro2.tf.collection;

import java.util.Iterator;

public interface ListTAD<E> extends Iterable<E> {
	void add(E e); // insere e no final
	void add(int index, E element); // insere e em determinada posição (index)
	E get(int index); // obtém elemento na posição index
	int indexOf(E e); // retorna a posição onde e está
	void set(int index, E element); // altera o elemento na posição index
	boolean remove(E e); // remove o elemento e, se existir
	E remove(int index); // remove o elemento da posição index
	boolean isEmpty(); // retorna true se o array está vazio
	int size(); // retorna o total de elementos armazenados
	boolean contains(E e); // retorna true se e existe no array
	void clear(); // remove todos os elementos
	Iterator<E> iterator(); // retorna um iterador
}
