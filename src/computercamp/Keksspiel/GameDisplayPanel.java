package computercamp.Keksspiel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameDisplayPanel extends JPanel implements MouseListener, KeyListener
{
	public GameDisplayPanel()
	{
		addMouseListener(this);
		Main.frame.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		Rectangle size = g.getClipBounds();
		g.drawImage(Ressource.get("background"), 0, 0, size.width, size.height, null);
		g.drawImage(Ressource.get("Keks"), size.width / 2, 4 * size.height / 5, size.width / 20, size.height / 10, null);
		g.drawImage(Ressource.get("player0"), size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
		g.drawImage(Ressource.get("player1"), 2 * size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
		g.drawImage(Ressource.get("player2"), 6 * size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
		g.drawImage(Ressource.get("player3"), 7 * size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		System.out.println("Key Pressed:" + e.getKeyChar());
	}
	
	@Override 
	public void mousePressed(MouseEvent e)
	{
		System.out.println("Clicked at " + e.getX() + ", " + e.getY());
	}

	@Override public void keyReleased(KeyEvent arg0) {}
	@Override public void keyTyped(KeyEvent arg0) {}
	@Override public void mouseClicked(MouseEvent arg0) {}
	@Override public void mouseEntered(MouseEvent arg0) {}
	@Override public void mouseExited(MouseEvent arg0) {}
	@Override public void mouseReleased(MouseEvent arg0) {}
}
