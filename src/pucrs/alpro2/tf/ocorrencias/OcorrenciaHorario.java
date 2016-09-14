package pucrs.alpro2.tf.ocorrencias;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OcorrenciaHorario extends Ocorrencia<Calendar> {
	private SimpleDateFormat sdf;
	
	public OcorrenciaHorario(Calendar c) {
		super(c);
		super.setValue(c);
		this.sdf = new SimpleDateFormat("HH:mm");
	}
	
	public int getHora() {
		return super.getValue().get(Calendar.HOUR_OF_DAY);
	}
	
	@Override
	public String toString() {
		return ""+sdf.format(super.getValue().getTime());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OcorrenciaHorario) {
			OcorrenciaHorario o = (OcorrenciaHorario) obj;
			return this.getHora() == o.getHora();
		}
		return false;
	}
}