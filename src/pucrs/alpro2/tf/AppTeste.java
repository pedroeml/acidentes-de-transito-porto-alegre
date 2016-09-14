/**
* Esta classe foi criada apenas para fins de testes de métodos.
*/
package pucrs.alpro2.tf;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
* @author Pedro 'PML' Lemos
*
*/
public class AppTeste {

	public static void main(String[] args) {
//		Logradouro lo = new Logradouro("AV PLINIO BRASIL MILANO");
//		System.out.println(lo.getNome()+"\n"+lo.getTipo_End());
//		System.out.println(lo);
		
		Calendar c = Calendar.getInstance();
		c.set(2014, 10, 22, 0, 45);
		System.out.println(c.getTime());
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		//System.out.println(c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println(c.get(Calendar.HOUR_OF_DAY));//Formato de horário militar.
		//System.out.println(c.get(Calendar.HOUR));//Formato de horário usando AM/PM.
		System.out.println(c.get(Calendar.MINUTE));
		//System.out.println(c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"+" "+"HH:mm");
		
		
		
//		String Dia_Sem;
//		int Dia_SemInt = c.get(Calendar.DAY_OF_WEEK);
//		switch(Dia_SemInt) {
//			case 1:
//				Dia_Sem = "DOMINGO";
//				break;
//			case 2:
//				Dia_Sem = "SEGUNDA-FEIRA";
//				break;
//		    case 3:
//		    	Dia_Sem = "TERÇA-FEIRA";
//		    	break;
//		    case 4:
//		    	Dia_Sem = "QUARTA-FEIRA";
//		    	break;
//		    case 5:
//		    	Dia_Sem = "QUINTA-FEIRA";
//		    	break;
//		    case 6:
//		    	Dia_Sem = "SEXTA-FEIRA";
//		    	break;
//		    default:
//		    	Dia_Sem = "SÁBADO";
//		    	break;
//		}
//		System.out.println(sdf.format(c.getTime())+" "+Dia_Sem);
//		
//		String aux = "0,2333";
//		System.out.println(aux);
//		aux = aux.replace(",",".");
//		System.out.println(aux);
		
		
//		boolean aux = false;
//		if(TipoAcid.ABALROAMENTO.toString().compareToIgnoreCase("abalroamento") == 0)
//			aux = true;
//			System.out.println(aux);
		
//		ListaLog lista = new ListaLog();
//		lista.add(new LogradouroEx("AV CRISTOVAO II"));
//		System.out.println(lista);
//		lista.add(new LogradouroEx("AV CRISTOVAO COLOMBO"));
//		System.out.println(lista);
//		lista.add(new LogradouroEx("AV ERICO VERISSIMO"));
//		System.out.println(lista);
//		lista.add(new LogradouroEx("R DOS ANDRADAS"));
//		System.out.println(lista);
//		LogradouroEx log = new LogradouroEx("");
//		lista.add(log);//A lista não permite que Logradouros com o Nome ou Tipo_End vazios
//		System.out.println(lista);
//		System.out.println(lista.get(lista.size()-1));//imprimindo último elemento
		
//		String data_hora = "20090326 21:00";
//		String Haux = data_hora.charAt(9)+""+data_hora.charAt(10);
//		int hora = Integer.parseInt(Haux);
//		System.out.println(hora);
	}

}
