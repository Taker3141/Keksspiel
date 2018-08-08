package computercamp.Keksspiel.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

import computercamp.Keksspiel.server.Cum;

@SuppressWarnings("serial")
public class GameDisplayPanel extends DisplayPanel
{
	private int lastmousex, lastmousey;
	private int speed;
	private Button shopbutton = new Button(15f/17f, 1f/9f, 1f/9f, 1f/9f);
	private Button buttonReady = new Button(15f/17f, 4f/9f, 1f/9f, 1f/9f);
	public ClientPlayer player = KeksspielClient.player[KeksspielClient.playerIndex];
	public int[] results = null;
	
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
		ClientPlayer last = null;
		for(int i = 0; i < KeksspielClient.player.length; i++)
		{
			ClientPlayer p = KeksspielClient.player[i];
			if(p != null && p.cameLast) last = p;
		}
		for(int i = 0; i < KeksspielClient.player.length; i++)
		{
			if(KeksspielClient.player[i] == null) break;
			ClientPlayer p = KeksspielClient.player[i];
			g.drawImage(p.getTexture(), (int)(p.px * size.width), (int)(p.py * size.height), (int)(p.pw * size.width), (int)(p.ph * size.height), null);
			g.drawImage(p.dick.getTexture(), (int) ((p.px + p.pw/2) * size.width), (int) ((p.py + p.ph/2) * size.height), (int) (p.dick.dw * size.width * (i < 2 ? 1 : -1)),(int) (p.dick.dh * size.height), null);	
		}
		if(last == null) g.drawImage(Ressource.get("Keks"), size.width / 2, 4 * size.height / 5, size.width / 20, size.height / 10, null);
		else g.drawImage(Ressource.get("Keks"), (int)((last.px + 1f/10f) * size.width), (int)((last.py + 1f/5f) * size.height), size.width / 20, size.height / 10, null);
		if(!player.came) 
		{
			g.drawImage(Ressource.get("jerk_bar_balls"), 10, 10, 60, 60, null);
			g.drawImage(Ressource.get("jerk_bar_cock"), 70, 10, (int) (player.jerk * (1 / (float)player.jerkDuration) * size.width) - 120, 60, null);
			g.drawImage(Ressource.get("jerk_bar_tip"), (int) (player.jerk * (1 / (float)player.jerkDuration) * size.width) - 60, 10, 60, 60, null);
		}
		
		if(player.came) 
		{
			g.drawImage(Ressource.get("button_shop"),(int)(shopbutton.bx * size.width), (int)(shopbutton.by * size.height), (int)(shopbutton.bl * size.width), (int)(shopbutton.bh * size.height), null);
			g.drawImage(Ressource.get("button_ready"),(int)(buttonReady.bx * size.width), (int)(buttonReady.by * size.height), (int)(buttonReady.bl * size.width), (int)(buttonReady.bh * size.height), null);
		}
		g.setFont(new Font("Arial", 0, 20));
		
		for(int i = 0; i < KeksspielClient.player.length; i++) if(KeksspielClient.player[i] != null)
		{
			ClientPlayer p = KeksspielClient.player[i];
			g.drawString(p.name + " : " + p.money + "$" + (p.came ? ", ist " + (p.cameLast ? "als letzter" : "") + " gekommen" : ""), (int) (1f/50f * size.width), (int) (0.2f/2f * size.height * i + 100));
			for(int j = 0; j < p.cum.length; j++) if(p.cum[j] != null)
			{
				Cum c = p.cum[j];
				g.drawImage(Ressource.get("cum"), (int)(c.px * size.width - (size.width * p.cumSize / 2)), (int)(c.py * size.height - (size.height * p.cumSize)), (int)(size.width * p.cumSize), (int)(size.height * p.cumSize * 2), null);
			}
		}
		
		if(results != null)
		{
			g.setColor(Color.WHITE);
			g.fillRect(size.width / 4, size.height /4, size.width / 2, size.height / 2);
			g.setColor(Color.BLACK);
			g.drawRect(size.width / 4, size.height /4, size.width / 2, size.height / 2);
			g.setColor(Color.GRAY);
			for(int i = 0; i < results.length; i++)
			{
				String message;
				int number = results[i];
				if(number < 0 || KeksspielClient.player[i] == null) continue;
				if(number == 0) message = "musste nie den Keks essen ";
				else message = "musste " + number + " mal den Keks essen ";
				boolean lost = true, won = true;
				for(int j = 0; j < 4; j++)
				{
					if(results[j] < 0) continue;
					lost &= number >= results[j];
					won &= number <= results[j];
				}
				if(lost && won) {g.setColor(Color.YELLOW); message += ": Unentschieden";}
				else
				{
					if(lost) {g.setColor(Color.RED); message += "und hat verloren";}
					if(won) {g.setColor(Color.GREEN); message += "und hat gewonnen";}
				}
				message += ".";
				g.drawString(KeksspielClient.player[i].name + " " + message, size.width / 4 + 50, size.height / 4 + 50 + i * 50);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}
	
	@Override 
	public void mousePressed(MouseEvent e)
	{
		if(checkbutton(shopbutton, e.getX(), e.getY()) && player.came) KeksspielClient.changeDisplay(new ShopDisplayPanel(player));
		if(checkbutton(buttonReady, e.getX(), e.getY()) && player.came) 
		{
			KeksspielClient.networkThread.reportReady();
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

