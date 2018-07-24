package computercamp.Keksspiel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameDisplayPanel extends JPanel implements MouseListener, KeyListener, MouseMotionListener
{
	private int lastmousex, lastmousey;
	private int speed;
	private int barlength;
	private boolean came = false;
	private List<Cum> cumList = new ArrayList<Cum>();
	private Rectangle size;
	private static final float JERK_DURATION = 10000;
	private Button shopbutton = new Button(15f/17f, 1f/9f, 1f/9f, 1f/9f);
	
	public GameDisplayPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		Main.frame.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		size = g.getClipBounds();
		g.drawImage(Ressource.get("background"), 0, 0, size.width, size.height, null);
		g.drawImage(Ressource.get("Keks"), size.width / 2, 4 * size.height / 5, size.width / 20, size.height / 10, null);
		int pw = size.width / 4, ph = size.height / 2;
		for(int i = 0; i < Main.player.length; i++)
		{
			Player p = Main.player[i];
			g.drawImage(p.getTexture(), (int)(p.px * size.width), (int)(p.py * size.height), (int)(p.pw * size.width), (int)(p.ph * size.height), null);
			g.drawImage(p.dick.getTexture(), (int) ((p.px + p.pw/2) * size.width), (int) ((p.py + p.ph/2) * size.height), (int) (p.dick.dw * size.width * (i < 2 ? 1 : -1)),(int) (p.dick.dh * size.height), null);
		}
		for(Cum c : cumList)
		{
			g.drawImage(c.getTexture(), (int)(c.px * size.width), (int)(c.py * size.height), (int)(c.w * size.width), (int)(c.h * size.height), null);			
		}
		if(!came) g.fillRect(10, 10, (int) (barlength * (1 / JERK_DURATION) * size.width), 30);
		
		g.drawImage(Ressource.get("Shopbutton"),(int)(shopbutton.bx * size.width), (int)(shopbutton.by * size.height), (int)(shopbutton.bl * size.width), (int)(shopbutton.bh * size.height), null);
		g.setFont(new Font("Arial",0,14));
		
		for (int i = 0; i < Main.player.length; i++) {
			g.drawString("Player" + (i +1) + " : " + Main.player[i].money , (int) (1f/50f * size.width), (int) (0.2f/2f * size.height * i + 80));
		}
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		
		if(e.getKeyCode() == KeyEvent.VK_R)
		{
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
	
	private boolean checkbutton(Button b, int mx, int my)
	{
		return b.bx * size.width < mx && b.by * size.height < my && (b.bx + b.bl) * size.width > mx && (b.by + b.bh) * size.height > my;
	}
	
	@Override 
	public void mousePressed(MouseEvent e)
	{
		if(checkbutton(shopbutton, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Shop");
			Main.frame.remove(Main.display);
			Main.frame.add(new ShopDisplayPanel());
			Main.frame.repaint();
			Main.frame.setSize(Main.frame.getWidth() + 1, Main.frame.getHeight());
			Main.frame.setSize(Main.frame.getWidth() - 1, Main.frame.getHeight());
		}
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
		lastmousex = e.getX();
		lastmousey = e.getY();
		barlength += speed;
		if(barlength > JERK_DURATION && !came) 
		{
			came = true;
			Cum cum = new Cum(((float)e.getX()) / (float)size.width, ((float)e.getY()) / (float)size.height);
			float dx = cum.px - 1f/2f, dy = cum.py - 4f/5f;
			Main.player[0].distanceFromCookie = (float)Math.sqrt(dx * dx + dy * dy);
			System.out.println("Distance from Cookie: " + Main.player[0].distanceFromCookie);
			cumList.add(cum);
			
		}
		repaint();
		
	}
}

