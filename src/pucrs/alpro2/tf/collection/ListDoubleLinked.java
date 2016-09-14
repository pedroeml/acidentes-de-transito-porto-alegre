package pucrs.alpro2.tf.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListDoubleLinked<E> implements ListTAD<E> {
	private Node<E> header;
	private Node<E> trailer;
	private int count;
	
	protected class Node<T> {
		protected T element;
		protected Node<T> next;
		protected Node<T> prev;

		protected Node(T t) {
			element = t;
			prev = next = null;
		}	
	}
	
	public ListDoubleLinked() {
		clear();
	}
	
	@Override
	public void add(E elem) {
		Node<E> n = new Node<E>(elem);
		Node<E> last = trailer.prev;
		n.prev = last;
		n.next = trailer;
		last.next = n;
		trailer.prev = n;
		count++;
	}

	// Procura e retorna um nodo pela posição
	protected Node<E> getNode(int index) {
		if (index < 0 || index >= count)
			throw new IndexOutOfBoundsException();
		Node<E> atual = header.next;
		for (int pos = 0; pos < index; pos++)
			atual = atual.next;
		return atual;
	}

	// Remove o nodo passado como parâmetro
	private void removeNode(Node<E> atual) {
		Node<E> ant = atual.prev;
		Node<E> prox = atual.next;
		ant.next = prox;
		prox.prev = ant;
		count--;
	}

	// Encontra o nodo que contém o elemento informado (null se não achar)
	private Node<E> findNode(E elem) {
		Node<E> atual = header.next;
		while (atual != trailer) {
			if (elem.equals(atual.element))
				return atual;
			atual = atual.next;
		}
		return null;
	}

	@Override
	public void add(int index, E element) {
		Node<E> atual = getNode(index);
		Node<E> novo = new Node<E>(element);

		Node<E> ant = atual.prev;
		ant.next = novo;
		novo.prev = ant;
		novo.next = atual;
		atual.prev = novo;
		count++;
	}

	@Override
	public E get(int index) {
		Node<E> atual = getNode(index);
		return atual.element;
	}

	@Override
	public int indexOf(E e) {
		Node<E> atual = header.next;
		int pos = 0;
		while (atual != trailer) {
			if (e.equals(atual.element))
				return pos;
			atual = atual.next;
			pos++;
		}
		return -1;
	}

	@Override
	public void set(int index, E element) {
		Node<E> atual = getNode(index);
		atual.element = element;
	}

	@Override
	public boolean remove(E e) {
		Node<E> atual = findNode(e);
		if (atual != null) {
			removeNode(atual);
			return true;
		}
		return false;
	}

	@Override
	public E remove(int index) {
		Node<E> atual = getNode(index);
		removeNode(atual);
		return atual.element;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public int size() {
		return count;
	}
	
	public void incrementaSize() {
		this.count++;
	}

	@Override
	public boolean contains(E e) {
		return findNode(e) != null;
	}

	@Override
	public void clear() {
		header = new Node<E>(null);
		trailer = new Node<E>(null);
		header.next = trailer;
		trailer.prev = header;
		count = 0;
	}

	@Override
	public String toString() {
		StringBuilder aux = new StringBuilder();
		Node<E> atual = header.next;
		while (atual != trailer) {
			aux.append(atual.element);
			aux.append(" ");
			atual = atual.next;
		}
		return aux.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		// Veja que não podemos usar E porque este método NÃO é genérico
		// Portanto, a única forma é usar ?, que significa: ignore o tipo
		if (obj instanceof ListDoubleLinked<?> == false)
			return false;
		ListDoubleLinked<?> outra = (ListDoubleLinked<?>) obj;
		if (count != outra.size())
			return false;
		Node<E> atual1 = header.next;
		Node<E> atual2 = (Node<E>) outra.header.next;
		while (atual1 != trailer) {
			if (!atual1.element.equals(atual2.element))
				return false;
			atual1 = atual1.next;
			atual2 = atual2.next;
		}
		return true;		
	}
	
	public Iterator<E> iteradorReverso() {
		return new Iterator<E>() {

			private Node<E> atual = trailer.prev;
			
			@Override
			public boolean hasNext() {
				return atual != header;
			}

			@Override
			public E next() {
				if (atual == header)
					throw new NoSuchElementException();
				E item = atual.element;
				atual = atual.prev;
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private Node<E> atual = header.next;
			
			@Override
			public boolean hasNext() {
				return atual != trailer;
			}

			@Override
			public E next() {
				if (atual == trailer)
					throw new NoSuchElementException();
				E item = atual.element;
				atual = atual.next;
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	public ListDoubleLinked<E> clone() {
		ListDoubleLinked<E> lista = new ListDoubleLinked<>();
		Iterator<E> it = this.iterator();
		while (it.hasNext())
			lista.add(it.next());
		return lista;
	}
}
