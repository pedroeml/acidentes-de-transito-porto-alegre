package pucrs.alpro2.tf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JPanel;
import org.jxmapviewer.viewer.GeoPosition;
import pucrs.alpro2.GerenciadorMapa;
import pucrs.alpro2.GerenciadorMapa.FonteImagens;
import pucrs.alpro2.tf.collection.ListaAcid;
import pucrs.alpro2.tf.collection.MapLogradouro;
import pucrs.alpro2.MyWaypoint;

/**
 * @author Pedro 'PML' Lemos
 * 
 */
public class Main {
	private static MapLogradouro mapLogradouros = new MapLogradouro();
	private static LinkedList<Acidente> listaAllAcidGUI = new LinkedList<>();//Melhor opção para representação no GUI.
	
	public static String boot() throws IOException {
		long antes = System.currentTimeMillis();
		Main.readFile();
		String output = "TEMPO DE LEITURA DO ARQUIVO E GERAÇÃO DAS ESTRUTURAS ENCADEADAS: "+(System.currentTimeMillis() - antes)/1000.0+" s\nTAMANHO DA LISTA DE LOGRADOUROS EM ORDEM ALFABÉTICA: "+mapLogradouros.size();
		
		antes = System.currentTimeMillis();
		
		Main.clone(mapLogradouros);
		
		output += "\nTEMPO DE CÓPIA DE TODOS OS ACIDENTES E GRAVAÇÃO NO LIST DE TODOS OS ACIDENTES: "+(System.currentTimeMillis() - antes)/1000.0+" s\nTAMANHO DO LIST DE TODOS OS ACIDENTES: "+listaAllAcidGUI.size();
		System.out.println(output);
		return output;
	}
	
	public static void main(String[] args) throws IOException {				
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n\nPressione ENTER para prosseguir.");
        scan.nextLine();
		
        long antes, depois, total;
		Main.boot();
		
		
		System.out.println("\n\nPressione ENTER para prosseguir.");
        scan.nextLine();
		
		boolean exit = false;
        while (exit == false) {//Permite que isso seja executado o quanto o usuário precisar.
        	System.out.println("\f");
            System.out.println("===[ PRINCIPAL ]======");
            System.out.println("[1]  LEITURA DE UM LOGRADOURO ALEATÓRIO PESQUISADO POR POSIÇÃO");
            System.out.println("[2]  VERIFICAR A HORA DO DIA QUANDO OCORREM MAIS ACIDENTES");
            System.out.println("[3]  PESQUISA POR NOME DA RUA/AV E DIA DA SEMANA");
            System.out.println("[4]  PESQUISA DE ACIDENTES EM UM PERÍODO COM RESTRIÇÃO DO TIPO DE ACIDENTE");
            System.out.println("[5]  VERIFICAR A DATA QUE HOUVE MAIS ACIDENTES");
            System.out.println("[6]  VERIFICAR O DIA DA SEMANA QUE HOUVE MAIS ACIDENTES");
            System.out.println("[7]  VERIFICAR SE OCORREM MAIS ACIDENTES DE DIA OU DE NOITE");
            System.out.println("[8]  VERIFICAR SE OCORREM MAIS ACIDENTES EM RELAÇÃO A SITUAÇÕESS CLIMÁTICAS");
            System.out.println("[9]  VISUALIZAR TODOS");
            System.out.println("[10] PESQUISA POR NOME DE RUA/AV");
            System.out.println("[11] PESQUISA POR DATA");
            System.out.println("[0]  Sair");
            switch(scan.nextInt())//Permite que o usuário digite um número inteiro e caia numa das opções citadas acima.
            {
                case 1:
                	scan.nextLine();
                	System.out.println("\n===[2.2] EXEMPLO DE LEITURA DE UM LOGRADOURO ALEATÓRIO PESQUISADO POR POSIÇÃO");
            		System.out.println("DIGITE UM VALOR DE 0 A "+(mapLogradouros.size()-1));
            		int i = scan.nextInt();
                	Logradouro aux = Consultas.get(i, mapLogradouros);
            		System.out.println(aux+"\n");
            		ListaAcid list = aux.getListaAcid();
            		listaAllAcidGUI.clear();
            		Main.clone(list);
            		Main.gui();
            		scan.nextLine();
                	System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 2:
                	scan.nextLine();
                	System.out.println("\n===[3.1] VERIFICAR A HORA DO DIA QUANDO OCORREM MAIS ACIDENTES");
            		System.out.println(Main.opcao2());
            		System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 3:
                	scan.nextLine();
                	System.out.println("\n===[3.2] EXEMPLO DE PESQUISA POR NOME E DIA DA SEMANA");
            		System.out.println("DIGITE UM NOME DE LOGRADOURO (Ex.: AV NILOPOLIS)");
                	String end = scan.nextLine();
                	System.out.println("DIGITE UM DIA DA SEMANA (Ex.: SÁBADO)");
                	String diasem = scan.nextLine();
                	Logradouro aux1 = Consultas.get(end, diasem, mapLogradouros);
            		System.out.println(aux1+"\n");
            		ListaAcid list1 = aux1.getListaAcid();
            		listaAllAcidGUI.clear();
            		Main.clone(list1);
            		Main.gui();
            		
            		System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 4:
                	scan.nextLine();
                	System.out.println("\n===[3.3] EXEMPLO DE PESQUISA DE ACIDENTES EM UM PERÍODO COM RESTRIÇÃO DO TIPO DE ACIDENTE");
            		System.out.println("DIGITE O DIA INICIAL:");
            		int dI = scan.nextInt();
            		System.out.println("DIGITE O MÊS INICIAL:");
            		int mI = scan.nextInt();
            		System.out.println("DIGITE O ANO INICIAL:");
            		int aI = scan.nextInt();
            		System.out.println("DIGITE O DIA FINAL:");
            		int dF = scan.nextInt();
            		System.out.println("DIGITE O MÊS FINAL:");
            		int mF = scan.nextInt();
            		System.out.println("DIGITE O ANO FINAL:");
            		int aF = scan.nextInt();
            		scan.nextLine();
            		System.out.println("DIGITE O TIPO DE ACIDENTE:");
            		String tipoAcid = scan.nextLine();
            		Consultas.periodoTipoAcidentes(dI, mI, aI, dF, mF, aF, tipoAcid, mapLogradouros);
            		listaAllAcidGUI.clear();
            		Main.clone(Consultas.getListaAcidGUI());
            		Main.gui();
            		System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 5:
                	scan.nextLine();
                	System.out.println("\n===[3.4] VERIFICAR A DATA QUE HOUVE MAIS ACIDENTES");
     				System.out.println(Main.opcao5());
     				System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 6:
                	scan.nextLine();
                	System.out.println("\n===[3.5] VERIFICAR O DIA DA SEMANA QUE HOUVE MAIS ACIDENTES");
                	System.out.println(Main.opcao6());
                	System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 7:
                	scan.nextLine();
                	System.out.println("\n===[3.6] VERIFICAR SE OCORREM MAIS ACIDENTES DE DIA OU DE NOITE");
                	antes = System.currentTimeMillis();
                	System.out.println(Main.opcao7());
                	depois = System.currentTimeMillis();
                	total = depois - antes;
                	System.out.println("TEMPO DE PROCESSAMENTO: "+total+" ms");
                	System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 8:
                	scan.nextLine();
                	System.out.println("\n===[3.7] VERIFICAR SE OCORREM MAIS ACIDENTES EM RELAÇÃO A SITUAÇÕES CLIMÁTICAS");
                	antes = System.currentTimeMillis();
                	System.out.println(Main.opcao8());
                	depois = System.currentTimeMillis();
                	total = depois - antes;
                	System.out.println("TEMPO DE PROCESSAMENTO: "+total+" ms");
                	System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 9:
                	Main.clone(mapLogradouros);
                	Main.gui();
            		scan.nextLine();
                	System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 10:
                	scan.nextLine();
                	System.out.println("\n===[4.2] PESQUISA POR NOME DE RUA/AV");
                	System.out.println("DIGITE UM NOME DE LOGRADOURO (Ex.: AV IPIRANGA)");
                	String nome = scan.nextLine();
                	Logradouro l = mapLogradouros.get(nome);
                	ListaAcid list8 = l.getListaAcid();
            		listaAllAcidGUI.clear();
            		Main.clone(list8);
            		Main.gui();
                	System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 11:
                	scan.nextLine();
                	System.out.println("\n===[4.3] PESQUISA POR DATA");
                	System.out.println("DIGITE O DIA:");
            		int d = scan.nextInt();
            		System.out.println("DIGITE O MÊS:");
            		int m = scan.nextInt();
            		System.out.println("DIGITE O ANO:");
            		int a = scan.nextInt();
                	Consultas.get(d, m, a, mapLogradouros);
            		listaAllAcidGUI.clear();
            		Main.clone(Consultas.getListaAcidGUI());
            		Main.gui();
                	System.out.println("\n\nPressione ENTER para voltar.");
                    scan.nextLine();
                    break;
                case 0:
                    exit = true;
                    scan.close();
                    break;
                default:
                    scan.nextLine();
                    System.out.println("Opção inválida!");
                    scan.nextLine();
            }
		}
	}
	
	public static JPanel gui() {
		// Criando duas localizações geográficas com coordenadas em POA
		//GeoPosition poa  = new GeoPosition(-30.05, -51.18);
		//GeoPosition poa2 = new GeoPosition(-30.0513, -51.1806);
		LinkedList<GeoPosition> poa = new LinkedList<>();
		Iterator<Acidente> it = listaAllAcidGUI.iterator();
		Acidente a;
		while (it.hasNext()) {
			a = it.next();
			poa.addLast(new GeoPosition(a.getLatitude(),a.getLongitude()));
		}
		
		// Criando o gerenciador do mapa, usando a primeira localização para
		// indicar o seu centro
		GerenciadorMapa manager = new GerenciadorMapa(poa.getFirst(), FonteImagens.VirtualEarth);
		// ou: FonteImagens.OpenStreetMap

		// Adicionando as duas localizações como pontos no mapa (ex: acidentes)
		//List<MyWaypoint> pontos = new ArrayList<>();
		LinkedList<MyWaypoint> pontos = new LinkedList<>();
		Iterator<GeoPosition> itGP = poa.iterator();
		int i = 0;
		GeoPosition gp;
		while (itGP.hasNext()) {
			gp = itGP.next();
			i++;
			pontos.addLast(new MyWaypoint(Color.RED, gp));
		}
		manager.setPontos(pontos);	
		System.out.println("NÚMERO DE PONTOS: "+i);
		
		
		// Exibe o mapa em um JPanel dentro do JFrame
		//JFrame frame = new JFrame("Visualizador de mapa usando JXMapviewer2");
		JPanel painelMapa = new JPanel();
		painelMapa.setLayout(new BorderLayout());		
		painelMapa.add(manager.getMapKit(), BorderLayout.CENTER);
		painelMapa.setBounds(174, 50, 600, 500);
		
		return painelMapa;
	}
	
	/**
	 * @param args the program args (ignored) 
	 * @throws IOException 
	 */
	public static void readFile() throws IOException {
		/**	================================================================================
		 *		 LEITURA DO ARQUIVO
		 *			- Leitura de dados
		 *			- Estruturação dos dados em listas encadeadas
		 *	================================================================================
		 */
		// Exemplo: como ler dados de um arquivo ZIP
		try (ZipFile file = new ZipFile("acidentes.zip")) {
			
			// entries vai conter todos os arquivos do zip (no caso, só tem um)
			Enumeration<? extends ZipEntry> entries = file.entries();
			// entry nos dá acesso a cada arquivo (como só tem um, basta uma chamada)
			ZipEntry entry = entries.nextElement();
			
			// getName() nos dá acesso ao nome real do arquivo dentro do zip
			System.out.println(entry.getName());
			
			// getInputStream(...) devolve uma ref. para um InputStream, que pode
			// então ser lido como se fosse um arquivo normal
			
			try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(entry)))) {
				// Aqui o BufferedReader já tem acesso aos dados...
				// Exemplo: contando as linhas do arquivo
				String line;
				br.readLine();
				while ((line = br.readLine()) != null) {
					
					String sc[] = line.split(";"); // separador: ;
			        String log1 = sc[0].trim(), log2 = sc[1].trim();
			        
					if (!log1.isEmpty()) {
						int log1FirstSpaceIndex = log1.indexOf(" ");
						
						if (log1FirstSpaceIndex != -1) {			
							Acidente a = new Acidente(log1, log2, Integer.parseInt(sc[2]), sc[3], sc[4], sc[5], Integer.parseInt(sc[7]), Integer.parseInt(sc[8]), Integer.parseInt(sc[9]), Integer.parseInt(sc[10]), 
									Integer.parseInt(sc[11]), Integer.parseInt(sc[12]), Integer.parseInt(sc[13]), Integer.parseInt(sc[14]), Integer.parseInt(sc[15]), Integer.parseInt(sc[16]), Integer.parseInt(sc[17]),
									Integer.parseInt(sc[18]), Integer.parseInt(sc[19]), Integer.parseInt(sc[20]), sc[21], sc[22], sc[23], Integer.parseInt(sc[24]), Integer.parseInt(sc[25]), Integer.parseInt(sc[26]),
									sc[27], sc[28]);
			
							mapLogradouros.add(log1, a, log1FirstSpaceIndex);//Adicionamos na coleção o primeiro logradouro.
							
							if (sc[3].equalsIgnoreCase("Cruzamento") && !log2.isEmpty()) { //Se o local do acidente foi um cruzamento, então será necessário adicionar a referência do acidente também para outro logradouro.
								int log2FirstSpaceIndex = log2.indexOf(" ");
								
								if (log2FirstSpaceIndex != -1)
									mapLogradouros.add(log2, a, log2FirstSpaceIndex);
							}
						}
					}
				}	
			}
		}
	}
	
	public static void update_ListaAllAcidGUI(LinkedList<Acidente> list) {
		listaAllAcidGUI.clear();
		listaAllAcidGUI = list;
	}
	
	public static void update_ListaAllAcidGUI(Iterator<Acidente> it) {
		Acidente auxA;
		while (it.hasNext()) {
			auxA = it.next();
			listaAllAcidGUI.addLast(auxA);
		}
	}
	
	public static void clone(ListaAcid list) {
		Iterator<Acidente> it = list.iterator();
		Main.update_ListaAllAcidGUI(it);
	}
	
	public static void clone(LinkedList<Acidente> list) {
		Main.update_ListaAllAcidGUI(list);
	}
	
	public static void clone(MapLogradouro list) {
		listaAllAcidGUI.clear();
		Iterator<Logradouro> itL = list.iterator();
		Logradouro auxLog;
		while (itL.hasNext()) {
			auxLog = itL.next();
			Main.clone(auxLog.getListaAcid());
		}
	}
	
	public static String opcao2() {
		long antes = System.currentTimeMillis();
		String output = Consultas.horarioMaisAcidentes(mapLogradouros);
		Main.update_ListaAllAcidGUI(Consultas.getListaAcidGUI());
		return output+"\t\tTempo de processamento: "+(System.currentTimeMillis() - antes)/1000.0+" s";
	}
	
	public static String opcao5() {
		long antes = System.currentTimeMillis();
		String output = Consultas.dataMaisAcidentes(mapLogradouros);
		Main.update_ListaAllAcidGUI(Consultas.getListaAcidGUI());
		return output+"\t\tTempo de processamento: "+(System.currentTimeMillis() - antes)/1000.0+" s";
	}
	
	public static String opcao6() {
		long antes = System.currentTimeMillis();
		String output = Consultas.diaSemMaisAcidentes(mapLogradouros);
		Main.update_ListaAllAcidGUI(Consultas.getListaAcidGUI());
		return output+"\t\tTempo de processamento: "+(System.currentTimeMillis() - antes)/1000.0+" s";
	}
	
	public static String opcao7() {
		long antes = System.currentTimeMillis();
		String output = Consultas.diaNoiteMaisAcidentes(mapLogradouros);
		Main.update_ListaAllAcidGUI(Consultas.getListaAcidGUI());
		return output+"\t\tTempo de processamento: "+(System.currentTimeMillis() - antes)/1000.0+" s";
	}
	
	public static String opcao8() {
		long antes = System.currentTimeMillis();
		String output = Consultas.climaMaisAcidentes(mapLogradouros);
		Main.update_ListaAllAcidGUI(Consultas.getListaAcidGUI());
		return output+"\t\tTempo de processamento: "+(System.currentTimeMillis() - antes)/1000.0+" s";
	}
}
