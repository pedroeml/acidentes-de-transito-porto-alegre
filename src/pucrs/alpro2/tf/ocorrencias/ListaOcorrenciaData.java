package pucrs.alpro2.tf.ocorrencias;

import java.util.Collections;
import java.util.Iterator;

import pucrs.alpro2.tf.ocorrencias.OcorrenciaData;

public class ListaOcorrenciaData extends ListaOcorrencia<OcorrenciaData> {
	
	public ListaOcorrenciaData() {
		super();
	}
	
	@Override
	public void add(OcorrenciaData o) {
		super.IncrementaCont();
		if(super.getLista().isEmpty())
			super.getLista().add(o);
		else {
			boolean done = false;
			Iterator<OcorrenciaData> it = super.iterator();
			while(it.hasNext()) {
				OcorrenciaData aux = it.next();
				if(o.equals(aux)) { //Se um elemento que está sendo inserido já existe na lista, então incrementados o contador.
					done = true;
					aux.incrementaOcorrencia();
					break;
				}
			}
			if(!done) { //Se não houve nenhum elemento repetido, nem há um elemento que aconteceu depois do que queremos adicionar, não houver nenhum próximo e não estiver pronto.
				super.getLista().add(o);//Portanto, o adicionamos.
				done = true;
			}
		}
	}
	
	@Override
	public void sort() {
		Collections.sort(super.getLista());//Temos que usar o sort por que o atributo 'cont' sempre muda de valor, por isso não há como adicionar na lista em uma posição adequada.
	}		
}
