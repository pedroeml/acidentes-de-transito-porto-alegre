package pucrs.alpro2.tf;

import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import pucrs.alpro2.tf.collection.MapLogradouro;
import pucrs.alpro2.tf.ocorrencias.ListaOcorrencia;
import pucrs.alpro2.tf.ocorrencias.ListaOcorrenciaData;
import pucrs.alpro2.tf.ocorrencias.ListaOcorrenciaDiaSem;
import pucrs.alpro2.tf.ocorrencias.ListaOcorrenciaHorario;
import pucrs.alpro2.tf.ocorrencias.OcorrenciaData;
import pucrs.alpro2.tf.ocorrencias.OcorrenciaDiaSem;
import pucrs.alpro2.tf.ocorrencias.OcorrenciaHorario;

public class Consultas {
	private static LinkedList<Acidente> listaAcidGUI;

	public static LinkedList<Acidente> getListaAcidGUI() {
		return listaAcidGUI;
	}

	public static String diaNoiteMaisAcidentes(MapLogradouro mapLog) {
		int contDia = 0, contNoite = 0, maior, menor;
		String turnoMaior, turnoMenor;
		Iterator<Logradouro> itLog = mapLog.iterator();
		Iterator<Acidente> itAcid;
		Logradouro log;
		Acidente a;

		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getNoite_Dia().equalsIgnoreCase("dia"))
					contDia++;
				else
					contNoite++;
			}
		}

		if (contDia > contNoite) {
			turnoMaior = "DIA";
			turnoMenor = "NOITE";
			maior = contDia;
			menor = contNoite;
		} else {
			turnoMaior = "NOITE";
			turnoMenor = "DIA";
			maior = contNoite;
			menor = contDia;
		}

		itLog = mapLog.iterator();
		listaAcidGUI = new LinkedList<>();

		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getNoite_Dia().equalsIgnoreCase(turnoMaior))
					listaAcidGUI.addLast(a);
			}
		}

		return "TURNO COM MAIOR OCORRÊNCIA DE ACIDENTES: " + turnoMaior + ", Nº DE OCORRÊNCIAS: " + maior + "\n"
				+ "TURNO COM MENOR OCORRÊNCIA DE ACIDENTES: " + turnoMenor + ", Nº DE OCORRÊNCIAS: " + menor + "\n"
				+ "TOTAL DE ACIDENTES LIDOS: " + (maior + menor);
	}

	public static Logradouro get(String nome, String dia_sem, MapLogradouro mapLog) {
		if (nome == null || nome.isEmpty())
			throw new IllegalArgumentException("Nome do logradouro é vazio ou null");
		Logradouro log = new Logradouro(nome, nome.indexOf(" "));
		Iterator<Acidente> it = mapLog.get(nome).getListaAcid().iterator();
		Acidente a;
		while (it.hasNext()) {
			a = it.next();
			if (dia_sem.equalsIgnoreCase(a.getDia_Sem()))
				log.getListaAcid().addLast(a);
		}
		return log;
	}

	public static Logradouro get(int i, MapLogradouro mapLog) {
		Iterator<Logradouro> it = mapLog.iterator();
		int count = 0;
		while (it.hasNext()) {
			if (count < i) {
				it.next();
				count++;
				continue;
			} else if (count == i) {
				return it.next();
			}
		}
		return null;
	}
	
	public static void get(int dia, int mes, int ano, MapLogradouro mapLog) {
		int mesDecrementado = mes - 1;
		listaAcidGUI = new LinkedList<>();
		Iterator<Logradouro> itLog = mapLog.iterator();
		Iterator<Acidente> itAcid;
		Logradouro log;
		Acidente a;
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getCalendarData().get(Calendar.DAY_OF_MONTH) == dia
						&& a.getCalendarData().get(Calendar.MONTH) == mesDecrementado
						&& a.getCalendarData().get(Calendar.YEAR) == ano)
					listaAcidGUI.addLast(a);
			}
		}
	}

	public static String horarioMaisAcidentes(MapLogradouro mapLog) {
		ListaOcorrencia<OcorrenciaHorario> horario = new ListaOcorrenciaHorario();
		Iterator<Logradouro> itLog = mapLog.iterator();
		Iterator<Acidente> itAcid;
		Logradouro log;
		Acidente a;
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				horario.add(new OcorrenciaHorario(a.getCalendarData()));
			}
		}
		horario.sort();

		int hora = horario.getLast().getHora();
		listaAcidGUI = new LinkedList<>();
		itLog = mapLog.iterator();
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getHora() == hora)
					listaAcidGUI.addLast(a);
			}
		}

		return "HORÁRIO COM MAIOR OCORRÊNCIA DE ACIDENTES: " + horario.getLast() + ", Nº DE OCORRÊNCIAS: "
				+ horario.getLast().getNumOcorrencias() + "\n" + "HORÁRIO COM 2ª MAIOR OCORRÊNCIA DE ACIDENTES: "
				+ horario.get(horario.size() - 2) + ", Nº DE OCORRÊNCIAS: "
				+ horario.get(horario.size() - 2).getNumOcorrencias() + "\n"
				+ "HORÁRIO COM MENOR OCORRÊNCIA DE ACIDENTES: " + horario.getFirst() + ", Nº DE OCORRÊNCIAS: "
				+ horario.getFirst().getNumOcorrencias() + "\n" + "HORÁRIO COM 2ª MENOR OCORRÊNCIA DE ACIDENTES: "
				+ horario.get(1) + ", Nº DE OCORRÊNCIAS: " + horario.get(1).getNumOcorrencias() + "\n"
				+ "TOTAL DE ACIDENTES LIDOS: " + horario.totalLidos();
	}

	public static String dataMaisAcidentes(MapLogradouro mapLog) {
		ListaOcorrencia<OcorrenciaData> data = new ListaOcorrenciaData();
		Iterator<Logradouro> itLog = mapLog.iterator();
		Iterator<Acidente> itAcid;
		Logradouro log;
		Acidente a;

		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				data.add(new OcorrenciaData(a.getCalendarData()));
			}
		}

		data.sort();
		OcorrenciaData ultimo = data.getLast();
		listaAcidGUI = new LinkedList<>();
		itLog = mapLog.iterator();

		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getDia() == ultimo.getDia() && a.getMes() == ultimo.getMes() && a.getAno() == ultimo.getAno())
					listaAcidGUI.addLast(a);
			}
		}

		return "DATA COM MAIOR OCORRÊNCIA DE ACIDENTES: " + data.getLast() + ", Nº DE OCORRÊNCIAS: "
				+ data.getLast().getNumOcorrencias() + "\n" + "DATA COM 2ª MAIOR OCORRÊNCIA DE ACIDENTES: "
				+ data.get(data.size() - 2) + ", Nº DE OCORRÊNCIAS: " + data.get(data.size() - 2).getNumOcorrencias()
				+ "\n" + "DATA COM MENOR OCORRÊNCIA DE ACIDENTES: " + data.getFirst() + ", Nº DE OCORRÊNCIAS: "
				+ data.getFirst().getNumOcorrencias() + "\n" + "DATA COM 2ª MENOR OCORRÊNCIA DE ACIDENTES: "
				+ data.get(1) + ", Nº DE OCORRÊNCIAS: " + data.get(1).getNumOcorrencias() + "\n"
				+ "TOTAL DE ACIDENTES LIDOS: " + data.totalLidos();
	}

	public static String diaSemMaisAcidentes(MapLogradouro mapLog) {
		ListaOcorrencia<OcorrenciaDiaSem> diasem = new ListaOcorrenciaDiaSem();
		Iterator<Logradouro> itLog = mapLog.iterator();
		Iterator<Acidente> itAcid;
		Logradouro log;
		Acidente a;
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				diasem.add(new OcorrenciaDiaSem(a.getCalendarData()));
			}
		}
		diasem.sort();

		String dia_sem = diasem.getLast().toString();
		listaAcidGUI = new LinkedList<>();
		itLog = mapLog.iterator();
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getDia_Sem().equalsIgnoreCase(dia_sem))
					listaAcidGUI.addLast(a);
			}
		}
		return "DIA DA SEMANA COM MAIOR OCORRÊNCIA DE ACIDENTES: " + diasem.getLast() + ", Nº DE OCORRÊNCIAS: "
				+ diasem.getLast().getNumOcorrencias() + "\n" + "DIA DA SEMANA COM 2ª MAIOR OCORRÊNCIA DE ACIDENTES: "
				+ diasem.get(diasem.size() - 2) + ", Nº DE OCORRÊNCIAS: "
				+ diasem.get(diasem.size() - 2).getNumOcorrencias() + "\n"
				+ "DIA DA SEMANA COM MENOR OCORRÊNCIA DE ACIDENTES: " + diasem.getFirst() + ", Nº DE OCORRÊNCIAS: "
				+ diasem.getFirst().getNumOcorrencias() + "\n" + "DIA DA SEMANA COM 2ª MENOR OCORRÊNCIA DE ACIDENTES: "
				+ diasem.get(1) + ", Nº DE OCORRÊNCIAS: " + diasem.get(1).getNumOcorrencias() + "\n"
				+ "TOTAL DE ACIDENTES LIDOS: " + diasem.totalLidos();
	}

	public static String climaMaisAcidentes(MapLogradouro mapLog) {
		LinkedList<Clima> list = new LinkedList<>();
		Iterator<Clima> itClima;
		Iterator<Logradouro> itLog = mapLog.iterator();
		Iterator<Acidente> itAcid;
		Clima c;
		Logradouro log;
		Acidente a;
		boolean done = false, first = true;
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				done = false;
				if (first) {
					first = !first;
					list.addLast(new Clima(a.getTempo()));
				} else {
					itClima = list.iterator();
					while (itClima.hasNext()) {
						c = itClima.next();
						if (a.getTempo().equalsIgnoreCase(c.getClima()) && !done) {
							c.incrementaCont();
							done = !done;
						}
					}
					if (!done) {
						list.addLast(new Clima(a.getTempo()));
						done = !done;
					}
				}
			}
		}

		Collections.sort(list);

		int totalClimas = 0;
		itClima = list.iterator();
		while (itClima.hasNext()) {
			c = itClima.next();
			totalClimas += c.getCont();
		}

		Clima maiorOcorrencia = list.getLast();
		String tempo = maiorOcorrencia.getClima();
		listaAcidGUI = new LinkedList<>();
		itLog = mapLog.iterator();
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getTempo().equalsIgnoreCase(tempo))
					listaAcidGUI.addLast(a);
			}
		}

		return String.format(
				"CLIMA COM MAIOR OCORRÊNCIA DE ACIDENTES: %10s, Nº DE OCORRÊNCIAS: %6d\nCLIMA COM 2ª MAIOR OCORRÊNCIA DE ACIDENTES: %10s, Nº DE OCORRÊNCIAS: %6d\nCLIMA COM MENOR OCORRÊNCIA DE ACIDENTES: %10s, Nº DE OCORRÊNCIAS: %6d\nCLIMA COM 2ª MENOR OCORRÊNCIA DE ACIDENTES: %10s, Nº DE OCORRÊNCIAS: %6d\nTOTAL DE CLIMAS LIDOS: %7d",
				tempo, maiorOcorrencia.getCont(), list.get(list.size() - 2), list.get(list.size() - 2).getCont(),
				list.getFirst(), list.getFirst().getCont(), list.get(1), list.get(1).getCont(), totalClimas);
	}

	public static void periodoTipoAcidentes(int Idia, int Imes, int Iano, int Fdia, int Fmes, int Fano, String tipoAcid, MapLogradouro mapLog) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(Iano, Imes - 1, Idia, 0, 0);
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(Fano, Fmes - 1, Fdia, 23, 59);
		listaAcidGUI = new LinkedList<>();

		Iterator<Logradouro> itLog = mapLog.iterator();
		Iterator<Acidente> itAcid;
		Logradouro log;
		Acidente a;
		while (itLog.hasNext()) {
			log = itLog.next();
			itAcid = log.getListaAcid().iterator();
			while (itAcid.hasNext()) {
				a = itAcid.next();
				if (a.getCalendarData().after(dataInicial) && a.getCalendarData().before(dataFinal) && a.getTipo_Acid().equalsIgnoreCase(tipoAcid))
					listaAcidGUI.addLast(a);
			}
		}
	}
}
