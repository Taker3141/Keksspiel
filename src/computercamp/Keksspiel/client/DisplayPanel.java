package computercamp.Keksspiel.client;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DisplayPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
	public Rectangle size;
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		size = g.getClipBounds();
	}
	
	protected boolean checkbutton(Button b, int mx, int my)
	{
		return b.bx * size.width < mx && b.by * size.height < my && (b.bx + b.bl) * size.width > mx && (b.by + b.bh) * size.height > my;
	}
	
	protected void drawButton(Graphics g, Button b)
	{
		g.drawImage(Ressource.get("Button"),(int)(b.bx * size.width), (int)(b.by * size.height), (int)(b.bl * size.width), (int)(b.bh * size.height), null);
	}
	
	protected void drawItem(Graphics g, String imageName, float relX, float relY, float relW, float relH)
	{
		g.drawImage(Ressource.get(imageName), (int)(relX * size.width), (int)(relY * size.height), (int)(relW * size.width), (int)(relH * size.height), null);
	}
	
	@Override public void mouseDragged(MouseEvent arg0) {}
	@Override public void mouseMoved(MouseEvent arg0) {}
	@Override public void mouseClicked(MouseEvent arg0) {}
	@Override public void mouseEntered(MouseEvent arg0) {}
	@Override public void mouseExited(MouseEvent arg0) {}
	@Override public void mousePressed(MouseEvent arg0) {}
	@Override public void mouseReleased(MouseEvent arg0) {}
	@Override public void keyPressed(KeyEvent arg0) {}
	@Override public void keyReleased(KeyEvent arg0) {}
	@Override public void keyTyped(KeyEvent arg0) {}
}
