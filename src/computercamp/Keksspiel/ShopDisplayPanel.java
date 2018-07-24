package computercamp.Keksspiel;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ShopDisplayPanel extends JPanel implements MouseListener, KeyListener{
	
	public Button buttonmorecum = new Button(1f/18f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonbiggerdick = new Button(6f/18f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonfastercum = new Button(12f/19f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonbbc = new Button(1f/18f, 2f/3f, 1f/ 6f, 1f/ 5f);
	public Button buttonlongschlong = new Button(6f/18f, 2f/3f, 1f/6f, 1f/5f);
	public Button buttontriangle = new Button(12f/19f, 2f/3f, 1f/6f, 1f/5f);
	
	private Rectangle size;
	
	public ShopDisplayPanel() 
	{
		addMouseListener(this);	
		Main.frame.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		size = g.getClipBounds();
		g.drawImage(Ressource.get("Shopupgrades"), 0, 0, size.width, size.height, null);
		g.drawImage(Ressource.get("Button"),(int)(buttonmorecum.bx * size.width), (int)(buttonmorecum.by * size.height), (int)(buttonmorecum.bl * size.width), (int)(buttonmorecum.bh * size.height), null);
		g.drawImage(Ressource.get("Button"),(int)(buttonbiggerdick.bx * size.width), (int)(buttonbiggerdick.by * size.height), (int)(buttonbiggerdick.bl * size.width), (int)(buttonbiggerdick.bh * size.height), null);
		g.drawImage(Ressource.get("Button"),(int)(buttonfastercum.bx * size.width), (int)(buttonfastercum.by * size.height), (int)(buttonfastercum.bl * size.width), (int)(buttonfastercum.bh * size.height), null);
		g.drawImage(Ressource.get("Button"),(int)(buttonbbc.bx * size.width), (int)(buttonbbc.by * size.height), (int)(buttonbbc.bl * size.width), (int)(buttonbbc.bh * size.height), null);
		g.drawImage(Ressource.get("Button"),(int)(buttonlongschlong.bx * size.width), (int)(buttonlongschlong.by * size.height), (int)(buttonlongschlong.bl * size.width), (int)(buttonlongschlong.bh * size.height), null);
		g.drawImage(Ressource.get("Button"),(int)(buttontriangle.bx * size.width), (int)(buttontriangle.by * size.height), (int)(buttontriangle.bl * size.width), (int)(buttontriangle.bh * size.height), null);
		g.drawImage(Ressource.get("penis_bbc"), (int) (1f/20f * size.width), (int) (0.5f/1f * size.height), (int) (1f/6f * size.width), (int) (1f/6f * size.height), null);
		g.drawImage(Ressource.get("penis_longschlong"), (int) (4f/20f * size.width), (int) (0.5f/1f * size.height), (int) (1f/6f * size.width), (int) (1f/6f * size.height), null);
		g.drawImage(Ressource.get("penis_triangle"), (int) (13f/20f * size.width), (int) (0.5f/1f * size.height), (int) (1f/6f * size.width), (int) (1f/6f * size.height), null);

	
	}
	
	private boolean checkbutton(Button b, int mx, int my)
	{
		return b.bx * size.width < mx && b.by * size.height < my && (b.bx + b.bl) * size.width > mx && (b.by + b.bh) * size.height > my;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(checkbutton(buttonmorecum, e.getX(), e.getY())) 
		{
			System.out.println("Clicked More Cum");
		}
		if(checkbutton(buttonbiggerdick, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Bigge Dick");
		}
		if(checkbutton(buttonfastercum, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Faster Cum");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		{
			System.out.println("Clicked ESCAPE");
			Main.frame.remove(Main.display);
			Main.display = new GameDisplayPanel();
			Main.frame.add(Main.display);
			Main.frame.removeKeyListener(this);
			removeMouseListener(this);
			Main.frame.repaint();
			Main.frame.setSize(Main.frame.getWidth() + 1, Main.frame.getHeight());
			Main.frame.setSize(Main.frame.getWidth() - 1, Main.frame.getHeight());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
