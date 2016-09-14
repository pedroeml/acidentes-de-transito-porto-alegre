package pucrs.alpro2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.GeoPosition;

import pucrs.alpro2.GerenciadorMapa.FonteImagens;

/**
 * Uma aplicação de exemplo que usa JXMapKit e JXMapViewer2
 * 
 * @author Martin Steiger, Marcelo Cohen
 */
public class ExemploMapa {
	/**
	 * @param args the program args (ignored) 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {		
		
		// Criando duas localizações geográficas com coordenadas em POA
		GeoPosition poa  = new GeoPosition(-30.05, -51.18);
		GeoPosition poa2 = new GeoPosition(-30.0513, -51.1806);
		
		// Criando o gerenciador do mapa, usando a primeira localização para
		// indicar o seu centro
		GerenciadorMapa manager = new GerenciadorMapa(poa, FonteImagens.VirtualEarth);
		// ou: FonteImagens.OpenStreetMap

		// Adicionando as duas localizações como pontos no mapa (ex: acidentes)
		List<MyWaypoint> pontos = new ArrayList<>();
		pontos.add(new MyWaypoint(Color.RED, poa));
		pontos.add(new MyWaypoint(Color.BLACK, poa2));
		manager.setPontos(pontos);	

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
			int total = 0;
			try(BufferedReader br = new BufferedReader(new InputStreamReader(
					file.getInputStream(entry)))) {
				// Aqui o BufferedReader já tem acesso aos dados...
				// Exemplo: contando as linhas do arquivo
				String line;				
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					total++;
				}				
			}	
			System.out.println("total de linhas: "+total);
		}
		
		// Exibe o mapa em um JPanel dentro do JFrame
		JFrame frame = new JFrame("Visualizador de mapa usando JXMapviewer2");
		JPanel painelMapa = new JPanel();
		painelMapa.setLayout(new BorderLayout());		
		painelMapa.add(manager.getMapKit(), BorderLayout.CENTER);
		
		frame.getContentPane().add(painelMapa);	
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
