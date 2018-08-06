package computercamp.Keksspiel.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class WaitingDisplayPanel extends DisplayPanel
{
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, size.width, size.height);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 0, 20));
		g.drawString("Waiting for other players to join", 0, 20);
		g.drawString("Press Enter when you're ready", 0, 50);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER) KeksspielClient.networkThread.reportReady();
	}
}
