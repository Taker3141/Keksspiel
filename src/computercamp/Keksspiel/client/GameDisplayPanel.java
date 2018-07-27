package computercamp.Keksspiel.client;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import computercamp.Keksspiel.server.Cum;

@SuppressWarnings("serial")
public class GameDisplayPanel extends DisplayPanel
{
	private int lastmousex, lastmousey;
	private int speed;
	private Button shopbutton = new Button(15f/17f, 1f/9f, 1f/9f, 1f/9f);
	public ClientPlayer player = new ClientPlayer(0, KeksspielClient.gameDisplay);
	
	public GameDisplayPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		KeksspielClient.frame.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("background"), 0, 0, size.width, size.height, null);
		g.drawImage(Ressource.get("Keks"), size.width / 2, 4 * size.height / 5, size.width / 20, size.height / 10, null);
		for(int i = 0; i < KeksspielClient.player.length; i++)
		{
			if(KeksspielClient.player[i] == null) break;
			ClientPlayer p = KeksspielClient.player[i];
			g.drawImage(p.getTexture(), (int)(p.px * size.width), (int)(p.py * size.height), (int)(p.pw * size.width), (int)(p.ph * size.height), null);
			g.drawImage(p.dick.getTexture(), (int) ((p.px + p.pw/2) * size.width), (int) ((p.py + p.ph/2) * size.height), (int) (p.dick.dw * size.width * (i < 2 ? 1 : -1)),(int) (p.dick.dh * size.height), null);
			g.drawImage(Ressource.get("cum"), (int)(p.cumX * size.width - (size.width * p.cumSize / 2)), (int)(p.cumY * size.height - (size.height * player.cumSize)), (int)(size.width * player.cumSize), (int)(size.height * player.cumSize * 2), null);			
		}
		if(!player.came) 
		{
			g.drawImage(Ressource.get("jerk_bar_balls"), 10, 10, 60, 60, null);
			g.drawImage(Ressource.get("jerk_bar_cock"), 70, 10, (int) (player.jerk * (1 / (float)player.jerkDuration) * size.width) - 120, 60, null);
			g.drawImage(Ressource.get("jerk_bar_tip"), (int) (player.jerk * (1 / (float)player.jerkDuration) * size.width) - 60, 10, 60, 60, null);
		}
		
		if(player.came) g.drawImage(Ressource.get("Shopbutton"),(int)(shopbutton.bx * size.width), (int)(shopbutton.by * size.height), (int)(shopbutton.bl * size.width), (int)(shopbutton.bh * size.height), null);
		g.setFont(new Font("Arial",0,14));
		
		

		for (int i = 0; i < KeksspielClient.player.length; i++) if(KeksspielClient.player[i] != null)
		{
			g.drawString(KeksspielClient.player[i].name + " : " + KeksspielClient.player[i].money , (int) (1f/50f * size.width), (int) (0.2f/2f * size.height * i + 80));
		}
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_R)
		{
			KeksspielClient.changeDisplay(new GameDisplayPanel());
		}
	}
	
	@Override 
	public void mousePressed(MouseEvent e)
	{
		if(checkbutton(shopbutton, e.getX(), e.getY()) && player.came) 
		{
			System.out.println("Clicked Shop");
			KeksspielClient.changeDisplay(new ShopDisplayPanel(player));
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(player.came) return;
		speed = (int)Math.sqrt(((lastmousex - e.getX()) * (lastmousex - e.getX())) + (lastmousey - e.getY()) * (lastmousey - e.getY()));
		lastmousex = e.getX();
		lastmousey = e.getY();
		player.jerk += speed;
		repaint();
		KeksspielClient.networkThread.reportJerk(speed, ((float)lastmousex) / size.width, ((float)lastmousey) / size.height);
	}
}

