/**
 * 
 */
package pucrs.alpro2.tf.ocorrencias;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author Pedro 'PML' Lemos
 *
 */
public abstract class ListaOcorrencia<E> {
	private Vector<E> lista;
	private int cont;
	
	public ListaOcorrencia() {
		cont = 0;
		lista = new Vector<>();
	}
	
	public E get(int i) {
		return lista.get(i);
	}
	
	public E getFirst() {
		return lista.firstElement();
	}
	
	public E getLast() {
		return lista.lastElement();
	}
	
	public abstract void add(E e);
	
	public void IncrementaCont() {
		cont++;
	}
	
	public List<E> getLista() {
		return lista;
	}
	
	public Iterator<E> iterator() {
		return lista.iterator();
	}
		
	public int size() {
		return lista.size();
	}
	
	public abstract void sort();
	
	public int totalLidos() {
		return this.cont;
	}
}