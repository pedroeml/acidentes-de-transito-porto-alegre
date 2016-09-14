package pucrs.alpro2.tf.ocorrencias;

import java.util.Calendar;

public class OcorrenciaDiaSem extends Ocorrencia<Calendar> {
	private int dia_sem;
	
	public OcorrenciaDiaSem(Calendar c) {
		super(c);
		super.setValue(c);
		this.dia_sem = c.get(Calendar.DAY_OF_WEEK);
	}
	
	public int getDia_sem() {
		return this.dia_sem;
	}
	
	@Override
	public String toString() {
		switch(this.dia_sem) {
			case 1:
				return "DOMINGO";
			case 2:
				return "SEGUNDA-FEIRA";
			case 3:
		    	return "TERÇA-FEIRA";
		    case 4:
		    	return "QUARTA-FEIRA";
		    case 5:
		    	return "QUINTA-FEIRA";
		    case 6:
		    	return "SEXTA-FEIRA";
		    default:
		    	return "SÁBADO";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OcorrenciaDiaSem) {
			OcorrenciaDiaSem o = (OcorrenciaDiaSem) obj;
			return this.dia_sem == o.getDia_sem();
		}
		return false;
	}
}
