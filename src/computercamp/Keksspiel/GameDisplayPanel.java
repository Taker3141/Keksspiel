package computercamp.Keksspiel;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameDisplayPanel extends JPanel
{
	public void paint(Graphics g)
	{
		Rectangle size = g.getClipBounds();
		g.drawImage(Ressource.get("background"), 0, 0, size.width, size.height, null);
	}
}
