/**
 * 
 */
package pucrs.alpro2.tf;

import java.text.SimpleDateFormat; 
import java.util.Calendar;

/**
 * @author Pedro 'PML' Lemos
 *
 */
public class Acidente {
	private Calendar data_hora;
	private String log1, log2, Local, Tipo_Acid, Dia_Sem, Tempo, Noite_Dia, Fonte;
	private int predial1, feridos, mortes, morte_post, fatais, auto, taxi, lotacao, onibus_urb, onibus_int, caminhao, moto, carroca, bicicleta, outro, dia, mes, ano;
	private float latitude, longitude;
	private SimpleDateFormat sdf;
	
	public enum TipoAcid { ABALROAMENTO, ATROPELAMENTO, CAPOTAGEM, CHOQUE, COLISÃO, EVENTUAL, INCÊNDIO, NÃO_CADASTRADO, QUEDA, TOMBAMENTO };
	
	public Acidente(String log1, String log2, int predial1, String local, String Tipo_Acid, String data_hora, int feridos, 
			int mortes, int morte_post, int fatais, int auto, int taxi, int lotacao, int onibus_urb, int onibus_int, int caminhao,
			int moto, int carroca, int bicicleta, int outro, String Tempo, String Noite_Dia, String Fonte, int dia, int mes, int ano, 
			String latitude, String longitude) {
		this.log1 = log1;
		this.log2 = log2;
		this.Local = local;
		this.Tipo_Acid = Tipo_Acid;
		this.data_hora = Calendar.getInstance();
		setData_hora(data_hora, ano, mes, dia);
		this.setDia_Sem(this.data_hora);
		this.sdf = new SimpleDateFormat("dd/MM/yyyy"+" "+"HH:mm");
		this.predial1 = predial1;
		this.feridos = feridos;
		this.mortes = mortes;
		this.morte_post = morte_post;
		this.fatais = fatais;
		this.auto = auto;
		this.taxi = taxi;
		this.lotacao = lotacao;
		this.onibus_urb = onibus_urb;
		this.onibus_int = onibus_int;
		this.caminhao = caminhao;
		this.moto = moto;
		this.carroca = carroca;
		this.bicicleta = bicicleta;
		this.outro = outro;
		this.Tempo = Tempo;
		this.Noite_Dia = Noite_Dia;
		this.Fonte = Fonte;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public String getLog1() {
		return log1;
	}

	public String getLog2() {
		return log2;
	}

	public String getData_hora() {
		return sdf.format(this.data_hora.getTime())+"";
	}
	
	public String getData() {
		return this.getDia_Sem()+", "+this.getData_hora();
	}
	
	public Calendar getCalendarData() {
		return data_hora;
	}
	
	public int getHora() {
		return data_hora.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinuto() {
		return data_hora.get(Calendar.MINUTE);
	}
	
	public void setData_hora(String data_hora, int ano, int mes, int dia) {
		int hora, minuto;
		
		if (data_hora.length() != 14 || (""+data_hora.charAt(9)).equals(" ") || (""+data_hora.charAt(10)).equals(" ") || (""+data_hora.charAt(12)).equals(" ") || (""+data_hora.charAt(13)).equals(" ")) {//Se o String seguir estas condições necessárias para realizarmos a operação desse método...
			hora = 0;//Definimos a hora e o minuto em 0.
			minuto = 0;
		}
		else {
			hora = Integer.parseInt((data_hora.charAt(9)+""+data_hora.charAt(10)));
			minuto = Integer.parseInt((data_hora.charAt(12)+""+data_hora.charAt(13)));
		}
			
		this.data_hora.set(ano, mes-1, dia, hora, minuto, 0);//Na classe calendar o meses vão de 0 até 11, ou seja, de Janeiro até Dezembro
	}

	public String getLocal() {
		return Local;
	}

	public String getTipo_Acid() {
		return Tipo_Acid;
	}

	public String getDia_Sem() {
		return Dia_Sem;
	}
	
	public void setDia_Sem(Calendar c) {
		int aux = c.get(Calendar.DAY_OF_WEEK);
		switch(aux) {
			case 1:
				this.Dia_Sem = "DOMINGO";
				break;
			case 2:
				this.Dia_Sem = "SEGUNDA-FEIRA";
				break;
		    case 3:
		    	this.Dia_Sem = "TERÇA-FEIRA";
		    	break;
		    case 4:
		    	this.Dia_Sem = "QUARTA-FEIRA";
		    	break;
		    case 5:
		    	this.Dia_Sem = "QUINTA-FEIRA";
		    	break;
		    case 6:
		    	this.Dia_Sem = "SEXTA-FEIRA";
		    	break;
		    default:
		    	this.Dia_Sem = "SÁBADO";
		    	break;
		}
	}

	public String getTempo() {
		return Tempo;
	}

	public String getNoite_Dia() {
		return Noite_Dia;
	}

	public String getFonte() {
		return Fonte;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getPredial1() {
		return predial1;
	}

	public int getFeridos() {
		return feridos;
	}

	public int getMortes() {
		return mortes;
	}

	public int getMorte_post() {
		return morte_post;
	}

	public int getFatais() {
		return fatais;
	}

	public int getAuto() {
		return auto;
	}

	public int getTaxi() {
		return taxi;
	}

	public int getLotacao() {
		return lotacao;
	}

	public int getOnibus_urb() {
		return onibus_urb;
	}

	public int getOnibus_int() {
		return onibus_int;
	}

	public int getCaminhao() {
		return caminhao;
	}

	public int getMoto() {
		return moto;
	}

	public int getCarroca() {
		return carroca;
	}

	public int getBicicleta() {
		return bicicleta;
	}

	public int getOutro() {
		return outro;
	}
	
	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		latitude = latitude.replace(',','.');//trocamos a vírgula por ponto, para poder utilizar o tipo de dado 'float'.
		this.latitude = Float.parseFloat(latitude);//então convertemos de String para 'float'.
	}
	
	public float getLongitude() {
		return longitude;
	};
	
	public void setLongitude(String longitude)
	{
		longitude = longitude.replace(',','.');//trocamos a vírgula por ponto, para poder utilizar o tipo de dado 'float'.
		this.longitude = Float.parseFloat(longitude);//então convertemos de String para 'float'.
	}
	
	@Override
	public String toString() {
		return this.getData();
	}
}
