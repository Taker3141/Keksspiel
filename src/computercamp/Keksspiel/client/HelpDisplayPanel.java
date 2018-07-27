package computercamp.Keksspiel.client;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class HelpDisplayPanel extends DisplayPanel{
	
	
	public HelpDisplayPanel() 
	{
		KeksspielClient.frame.addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("Help"),  0, 0, size.width, size.height, null);
		g.setFont(new Font("Arial",0,24));
		g.drawString("Press Start to start the game", (int) (1f/3.1f * size.width), (int) (1f/2.8f * size.height));
		g.drawString("Press Multiplayer to play with friends", (int) (1f/3.1f * size.width), (int) (1f/2.4f * size.height));
		g.drawString("Press 'm' to get back to the menu ", (int) (1f/3.1f * size.width), (int) (1f/2.1f * size.height));
		g.drawString("Press 'ESC' to get back to the game from the shop", (int) (1f/3.1f * size.width), (int) (1f/1.88f * size.height));
		g.drawString("Press 'r' to restart the game (your money wont reset)", (int) (1f/3.1f * size.width), (int) (1f/1.7f * size.height));
	}
	
	 
}
