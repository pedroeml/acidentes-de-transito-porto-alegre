package pucrs.alpro2.tf.ocorrencias;

import java.util.Collections;
import java.util.Iterator;

import pucrs.alpro2.tf.ocorrencias.OcorrenciaDiaSem;

public class ListaOcorrenciaDiaSem extends ListaOcorrencia<OcorrenciaDiaSem> {
	
	public ListaOcorrenciaDiaSem() {
		super();
	}
	
	@Override
	public void add(OcorrenciaDiaSem o) {
		super.IncrementaCont();
		if(super.getLista().isEmpty())
			super.getLista().add(o);
		else {
			boolean done = false;
			if(!done) {
				Iterator<OcorrenciaDiaSem> it = super.iterator();
				OcorrenciaDiaSem aux;
				while(it.hasNext() && !done) {
					aux = it.next();
					if(o.equals(aux)) {//Se um elemento que está sendo inserido já existe na lista, então incrementados o contador.
							done = true;
							aux.incrementaOcorrencia();
					}
				}
				if(!it.hasNext() && !done) {//Se não houve nenhum elemento repetido, nem há um elemento que aconteceu depois do que queremos adicionar, não houver nenhum próximo e não estiver pronto.
					super.getLista().add(o);//Portanto, o adicionamos.
					done = true;
				}
			}
		}
	}
	
	@Override
	public void sort() {
		Collections.sort(super.getLista());//Temos que usar o sort por que o atributo 'cont' sempre muda de valor, por isso não há como adicionar na lista em uma posição adequada.
	}		
}