package pucrs.alpro2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.WaypointRenderer;

/**
 * Classe para gerenciar um mapa
 * @author Marcelo Cohen
 */
public class GerenciadorMapa {
	final JXMapKit jXMapKit;
	private WaypointPainter<MyWaypoint> pontosPainter;
	private GeoPosition centro;
	
	public enum FonteImagens { OpenStreetMap, VirtualEarth };
	
	/*
	 * Cria um gerenciador de mapas, a partir de uma posição e uma fonte de imagens
	 * 
	 * @param centro centro do mapa
	 * @param fonte fonte das imagens (FonteImagens.OpenStreetMap ou FonteImagens.VirtualEarth)
	 */
	public GerenciadorMapa(GeoPosition centro, FonteImagens fonte) {
		jXMapKit = new JXMapKit();
		TileFactoryInfo info = null;
		if(fonte == FonteImagens.OpenStreetMap)
			info = new OSMTileFactoryInfo();
		else
			info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		jXMapKit.setTileFactory(tileFactory);
		
		// Ajustando a opacidade do mapa (50%)
		jXMapKit.getMainMap().setAlpha(0.5f);

		// Ajustando o nível de zoom do mapa
		jXMapKit.setZoom(4);
		// Informando o centro do mapa
		jXMapKit.setAddressLocation(centro);
		// Indicando que não desejamos ver um marcador nessa posição
		jXMapKit.setAddressLocationShown(false);
		
		// Criando um objeto para "pintar" os pontos
		pontosPainter = new WaypointPainter<MyWaypoint>();
		
		// Criando um objeto para desenhar os pontos
		pontosPainter.setRenderer(new WaypointRenderer<MyWaypoint>() {
			
			@Override
			public void paintWaypoint(Graphics2D g, JXMapViewer viewer, MyWaypoint wp) {

				// Desenha cada waypoint como um pequeno círculo
				Point2D point = viewer.getTileFactory().geoToPixel(wp.getPosition(), viewer.getZoom());
				int x = (int)point.getX();
				int y = (int)point.getY();
				g = (Graphics2D) g.create();
				g.setColor(wp.getColor());
				g.fill(new Ellipse2D.Float(x-3,y-3,6,6));
			}
		});
		
		jXMapKit.getMainMap().setOverlayPainter(pontosPainter);
	}
	
	/*
	 * Informa os pontos a serem desenhados (precisa ser chamado a cada vez que mudar)
	 * @param lista lista de pontos (objetos MyWaypoint)
	 */
	public void setPontos(List<MyWaypoint> lista) {
		// Criando um conjunto de pontos
		Set<MyWaypoint> pontos = new HashSet<MyWaypoint>(lista);
		// Informando o conjunto ao painter
		pontosPainter.setWaypoints(pontos);
	}
	
	/*
	 * Retorna a referência ao objeto JXMapKit, para ajuste de parâmetros (se for o caso)
	 * @returns referência para objeto JXMapKit em uso
	 */
	public JXMapKit getMapKit() {
		return jXMapKit;
	}

}
