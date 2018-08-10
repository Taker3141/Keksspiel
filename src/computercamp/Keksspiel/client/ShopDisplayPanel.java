package computercamp.Keksspiel.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ShopDisplayPanel extends DisplayPanel
{	
	public Button buttonmorecum = 		new Button(1f/18f, 0.33f, 0.25f, 0.2f);
	public Button buttonbiggerdick = 	new Button(6f/18f, 0.33f, 0.25f, 0.2f);
	public Button buttonfastercum = 	new Button(12f/19f, 0.33f, 0.25f, 0.2f);
	public Button buttonbbc = 			new Button(1f/18f, 0.75f, 0.25f, 0.2f);
	public Button buttonlongschlong = 	new Button(6f/18f, 0.75f, 0.25f, 0.2f);
	public Button buttontriangle = 		new Button(12f/19f, 0.75f, 0.25f, 0.2f);
	public Button[] buttons;
	public Rectangle mouseHover = null;
	public String hoverMessage = "";
	public final ClientPlayer player;
	
	public ShopDisplayPanel(ClientPlayer player) 
	{
		this.player = player;
		addMouseListener(this);
		addMouseMotionListener(this);
		KeksspielClient.frame.addKeyListener(this);
		buttons = new Button[] {buttonmorecum, buttonbiggerdick, buttonfastercum, buttonbbc, buttonlongschlong, buttontriangle};
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("Shopupgrades"), 0, 0, size.width, size.height, null);
		for(Button b : buttons) drawButton(g, b);
		drawItem(g, "penis_bbc", 0.1f, 0.52f, 0.16f, 0.2f);
		drawItem(g, "penis_longschlong", 0.4f, 0.52f, 0.16f, 0.2f);
		drawItem(g, "penis_triangle", 0.7f, 0.52f, 0.16f, 0.2f);
		g.setFont(new Font("Arial", 0, 18));
		g.drawString("Mehr Sperma: 100",(int) (0.1f * size.width), (int)(0.45 * size.height));
		g.drawString("Größerer Schwanz: 50",(int) (0.37f * size.width), (int)(0.45 * size.height));
		g.drawString("Schneller kommen: 50",(int) (0.67f * size.width), (int)(0.45 * size.height));
		g.drawString("BBC: 75",(int) (0.1f * size.width), (int)(0.87f * size.height));
		g.drawString("Long Schlong: 100",(int) (0.37f * size.width), (int)(0.87f * size.height));
		g.drawString("Dreieck-Cock: 200", (int) (0.67f * size.width), (int)(0.87f * size.height));
		
		g.setFont(new Font("Arial", 0, 30));
		g.setColor(Color.YELLOW);
		g.drawString("Geld: " + KeksspielClient.gameDisplay.player.money, (int) (0.85f * size.width), (int) (0.2f/1f * size.height));
		if(mouseHover != null) 
		{
			g.setColor(Color.WHITE);
			g.fillRect(mouseHover.x, mouseHover.y, mouseHover.width, mouseHover.height);
			g.setColor(Color.BLACK);
			g.drawRect(mouseHover.x, mouseHover.y, mouseHover.width, mouseHover.height);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", 0, 18));
			g.drawString(hoverMessage, mouseHover.x + 20, mouseHover.y + 30);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(checkbutton(buttonmorecum, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("more_cum");
		if(checkbutton(buttonbiggerdick, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("bigger_dick");
		if(checkbutton(buttonfastercum, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("cum_faster"); 
		if(checkbutton(buttonbbc, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("penis_bbc");
		if(checkbutton(buttonlongschlong, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("penis_longschlong");
		if(checkbutton(buttontriangle, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("penis_triangle");
		KeksspielClient.networkThread.syncPlayers();
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		try
		{
			int i = -1;
			if(checkbutton(buttonmorecum, e.getX(), e.getY())) i = 0; if(checkbutton(buttonbiggerdick, e.getX(), e.getY())) i = 1; if(checkbutton(buttonfastercum, e.getX(), e.getY())) i = 2; 
			if(checkbutton(buttonbbc, e.getX(), e.getY())) i = 3; if(checkbutton(buttonlongschlong, e.getX(), e.getY())) i = 4; if(checkbutton(buttontriangle, e.getX(), e.getY())) i = 5;
			if(i < 0) {mouseHover = null; hoverMessage = ""; return;}
			int length = 200;
			switch(i)
			{
				case 0: hoverMessage = "Macht es leichter zu treffen"; length = 250; break;
				case 1: hoverMessage = "Penisvergrößerung und etwas mehr Sperma"; length = 400; break;
				case 2: hoverMessage = "Verkürzt die Zeit bis zum abspritzen"; length = 320; break;
				case 3: hoverMessage = "Andere Spieler verlieren beim Essen deines Spermas mehr Geld"; length = 550; break;
				case 4: hoverMessage = "Du bekommst 50% mehr Geld"; length = 280; break;
				case 5: hoverMessage = "Teilt dein Sperma in 3 Teile auf"; length = 290; break;
			}
			if(i >= 0) mouseHover = new Rectangle(e.getX(), e.getY(), length, 40);
		}
		finally {repaint();}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) KeksspielClient.changeDisplay(KeksspielClient.gameDisplay);
	}
}
