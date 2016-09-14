package pucrs.alpro2.tf.collection;

import java.util.Iterator;

import pucrs.alpro2.tf.Acidente;
import pucrs.alpro2.tf.collection.ListDoubleLinked;

/**
* @author Pedro 'PML' Lemos
*
*/
public class ListaAcid implements ILista<Acidente> {
	private ListDoubleLinked<Acidente> lista;
	
	public ListaAcid() {
		lista = new ListDoubleLinked<>();
	}
	
	@Override
	public void add(Acidente a) {
		if (lista.isEmpty())
			lista.add(a);
		else {
			boolean done = false;
			Iterator<Acidente> it = this.iterator();
			Acidente aux;
			ListDoubleLinked<Acidente>.Node<Acidente> n0, n1;
			n1 = lista.getNode(0); // o primeiro da lista
			n0 = n1.prev; // o header
			while (it.hasNext()) {
				aux = it.next();
				if (aux.getCalendarData().before(a.getCalendarData())) { //Se o elemento na posição atual aconteceu antes do que queremos adicionar então continuamos a iteração.
					n0 = n1;
					n1 = n1.next;
					continue;
				}
				else if (aux.getCalendarData().after(a.getCalendarData())) { //Se o elemento na posição atual aconteceu depois do que queremos adicionar então adicionamos o elemento na posição do atual.
					done = true;
					ListDoubleLinked<Acidente>.Node<Acidente> n = lista.new Node<>(a);
					n0.next = n; // ANTES:
					n.prev = n0; // n0 <--> n1 <--> n2
					n.next = n1; // DEPOIS:
					n1.prev = n; // n0 <--> n <--> n1 <--> n2
					lista.incrementaSize();
					break;
				}
				else { //A lista impede que um elemento já existente seja inserido.
					done = true;
					break;
				}
			}
			if (!done) //Se não houve nenhum elemento repetido, nem há um elemento que aconteceu depois do que queremos adicionar e não houver nenhum próximo
				lista.add(a); //Portanto, o adicionamos.
		}
	}
	
	@Override
	public void addLast(Acidente a) {
		lista.add(a);
	}
	
	@Override
	public Iterator<Acidente> iterator() {
		return lista.iterator();
	}
	
	@Override
	public Acidente get(int i) {
		Iterator<Acidente> it = this.iterator();
		Acidente a = null;
		int count = 0;
		boolean done = false;
		while (it.hasNext() && !done) {
			if (count < i) {
				it.next();
				count++;
			}
			else if (count == i) {
				a = it.next();
				done = true;
			}
		}
		return a;
	}
	
	@Override
	public Acidente getFirst() {
		return lista.get(0);
	}
		
	@Override
	public int size() {
		return lista.size();
	}
	
	@Override
	public void clear() {
		lista.clear();
	}
	
	public String toStringLogradouro() {
		StringBuilder aux = new StringBuilder();
		Iterator<Acidente> it = this.iterator();
		Acidente a = null;
		
		while (it.hasNext()) {
			a = it.next();
			aux.append(a.getLog1());
			aux.append(" ");
			aux.append(a.getLog2());
			aux.append(" ");
			aux.append(a.toString());
			aux.append("\n");
		}
		return aux.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder aux = new StringBuilder();
		Iterator<Acidente> it = this.iterator();
		Acidente a = null;
		
		while (it.hasNext()) {
			a = it.next();
			aux.append(a.toString());
			aux.append("\n");
		}
		return aux.toString();
	}
}
