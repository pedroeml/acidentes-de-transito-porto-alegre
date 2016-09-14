
package pucrs.alpro2;

import java.awt.Color;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * Um waypoint que tem uma cor
 * @author Martin Steiger, Marcelo Cohen
 */
public class MyWaypoint extends DefaultWaypoint
{
	private final Color color;

	/**
	 * @param color a cor
	 * @param coord a localização
	 */
	public MyWaypoint(Color color, GeoPosition coord)
	{
		super(coord);
		this.color = color;
	}

	/**
	 * @returns a cor do waypoint
	 */
	public Color getColor()
	{
		return color;
	}
}
