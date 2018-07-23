package computercamp.Keksspiel;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameDisplayPanel extends JPanel implements MouseListener, KeyListener, MouseMotionListener
{
	private int lastmousex, lastmousey;
	private int speed;
	private int barlength;
	
	public GameDisplayPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		Main.frame.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		Rectangle size = g.getClipBounds();
		g.drawImage(Ressource.get("background"), 0, 0, size.width, size.height, null);
		//g.drawImage(Ressource.get("Figur1"), 500, 10 , 800,800, null);
		//g.drawImage(Ressource.get("penis_basic"), 880,450, 100,100,null);
		g.drawImage(Ressource.get("Keks"), size.width / 2, 4 * size.height / 5, size.width / 20, size.height / 10, null);
		g.drawImage(Ressource.get("player0"), size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
		g.drawImage(Ressource.get("player1"), 2 * size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
		g.drawImage(Ressource.get("player2"), 6 * size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
		g.drawImage(Ressource.get("player3"), 7 * size.width / 10, size.height / 3, size.width / 4, size.height / 2, null);
		g.fillRect(10, 10, (int) (barlength * 0.01), 30);
	
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		speed = (int)Math.sqrt(((lastmousex - e.getX()) * (lastmousex - e.getX())) + (lastmousey - e.getY()) * (lastmousey - e.getY()));
		System.out.println(speed);
		lastmousex = e.getX();
		lastmousey = e.getY();
		
		barlength += speed;
		repaint();
		
	}
}

