package pucrs.alpro2.tf.ocorrencias;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OcorrenciaData extends Ocorrencia<Calendar> {
	private SimpleDateFormat sdf;
	
	public OcorrenciaData(Calendar c) {
		super(c);
		super.setValue(Calendar.getInstance());
		super.getValue().set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0);
		this.sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public int getDia() {
		return super.getValue().get(Calendar.DAY_OF_MONTH);
	}
	
	public int getMes() {
		return super.getValue().get(Calendar.MONTH);
	}
	
	public int getAno() {
		return super.getValue().get(Calendar.YEAR);
	}
	
	@Override
	public String toString() {
		return ""+sdf.format(super.getValue().getTime());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OcorrenciaData) {
			OcorrenciaData o = (OcorrenciaData) obj;
			return this.getDia() == o.getDia() && this.getMes() == o.getMes() && this.getAno() == o.getAno();
		}
		return false;
	}
}
