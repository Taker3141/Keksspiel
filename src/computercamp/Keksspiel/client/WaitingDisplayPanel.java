package computercamp.Keksspiel.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class WaitingDisplayPanel extends DisplayPanel
{
	private boolean pressedEnter = false;
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("background"), 0, 0, size.width, size.height, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 0, 20));
		g.drawString("Auf andere Spieler warten...", 0, 20);
		if(!pressedEnter) g.drawString("Drücke Enter, wenn du bereit bist", 0, 50);
		else 
		{
			g.setColor(Color.GREEN);
			g.drawString("Du bist bereit!", 0, 50);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER) 
		{
			KeksspielClient.networkThread.reportReady();
			pressedEnter = true;
			repaint();
		}
	}
}
