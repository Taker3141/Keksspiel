package computercamp.Keksspiel.client;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import computercamp.Keksspiel.server.Player;

@SuppressWarnings("serial")
public class GameDisplayPanel extends DisplayPanel
{
	private int lastmousex, lastmousey;
	private int speed;
	private int barlength;
	private boolean came = false;
	private List<Cum> cumList = new ArrayList<Cum>();
	private Button shopbutton = new Button(15f/17f, 1f/9f, 1f/9f, 1f/9f);
	
	public GameDisplayPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		Main.frame.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("background"), 0, 0, size.width, size.height, null);
		g.drawImage(Ressource.get("Keks"), size.width / 2, 4 * size.height / 5, size.width / 20, size.height / 10, null);
		for(int i = 0; i < Main.player.length; i++)
		{
			Player p = Main.player[i];
			g.drawImage(p.getTexture(), (int)(p.px * size.width), (int)(p.py * size.height), (int)(p.pw * size.width), (int)(p.ph * size.height), null);
			g.drawImage(p.dick.getTexture(), (int) ((p.px + p.pw/2) * size.width), (int) ((p.py + p.ph/2) * size.height), (int) (p.dick.dw * size.width * (i < 2 ? 1 : -1)),(int) (p.dick.dh * size.height), null);
			
		}
		for(Cum c : cumList)
		{
			g.drawImage(c.getTexture(), (int)(c.px * size.width - (c.w * Main.player[0].cumSize / 2)), (int)(c.py * size.height - (c.h * Main.player[0].cumSize / 2)), (int)(c.w * size.width * Main.player[0].cumSize), (int)(c.h * size.height * Main.player[0].cumSize), null);			
		}
		if(!came) 
		{
			g.drawImage(Ressource.get("jerk_bar_balls"), 10, 10, 60, 60, null);
			g.drawImage(Ressource.get("jerk_bar_cock"), 70, 10, (int) (barlength * (1 / Main.player[0].jerkDuration) * size.width) - 120, 60, null);
			g.drawImage(Ressource.get("jerk_bar_tip"), (int) (barlength * (1 / Main.player[0].jerkDuration) * size.width) - 60, 10, 60, 60, null);
		}
		
		if(came) g.drawImage(Ressource.get("Shopbutton"),(int)(shopbutton.bx * size.width), (int)(shopbutton.by * size.height), (int)(shopbutton.bl * size.width), (int)(shopbutton.bh * size.height), null);
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
			Main.changeDisplay(new GameDisplayPanel());
		}
	}
	
	@Override 
	public void mousePressed(MouseEvent e)
	{
		if(checkbutton(shopbutton, e.getX(), e.getY()) && came) 
		{
			System.out.println("Clicked Shop");
			Main.changeDisplay(new ShopDisplayPanel());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		speed = (int)Math.sqrt(((lastmousex - e.getX()) * (lastmousex - e.getX())) + (lastmousey - e.getY()) * (lastmousey - e.getY()));
		lastmousex = e.getX();
		lastmousey = e.getY();
		barlength += speed;
		if(barlength > Main.player[0].jerkDuration && !came) 
		{
			Main.player[0].cum(((float)e.getX() / ((float)size.width)), ((float)e.getY() / ((float)size.height)));
		}
		repaint();
	}
}

