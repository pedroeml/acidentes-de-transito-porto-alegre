/**
 * 
 */
package pucrs.alpro2.tf.ocorrencias;

/**
 * @author Pedro 'PML' Lemos
 *
 */
public abstract class Ocorrencia<E> implements Comparable<Ocorrencia<E>> {
	private int cont;
	private E value;
	
	public Ocorrencia(E v) {
		cont = 1;
		value = null;
	}
	
	public E getValue() {
		return value;
	}
	
	public void setValue(E v) {
		this.value = v;
	}
	
	public int getNumOcorrencias() {
		return cont;
	}
	
	public void incrementaOcorrencia() {
		cont++;
	}

	@Override
	public int compareTo(Ocorrencia<E> o) {
		if(this.cont > o.getNumOcorrencias())
			return 1;
		else if(this.cont == o.getNumOcorrencias())
			return 0;
		//menor
		return -1;
	}
	
	@Override
	public abstract String toString();
}