package computercamp.Keksspiel.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ShopDisplayPanel extends DisplayPanel
{	
	public Button buttonmorecum = new Button(1f/18f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonbiggerdick = new Button(6f/18f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonfastercum = new Button(12f/19f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonbbc = new Button(1f/18f, 2f/3f, 1f/ 6f, 1f/ 5f);
	public Button buttonlongschlong = new Button(6f/18f, 2f/3f, 1f/6f, 1f/5f);
	public Button buttontriangle = new Button(12f/19f, 2f/3f, 1f/6f, 1f/5f);
	public Button[] buttons;
	public final ClientPlayer player;
	
	public ShopDisplayPanel(ClientPlayer player) 
	{
		this.player = player;
		addMouseListener(this);	
		KeksspielClient.frame.addKeyListener(this);
		buttons = new Button[] {buttonmorecum, buttonbiggerdick, buttonfastercum, buttonbbc, buttonlongschlong, buttontriangle};
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("Shopupgrades"), 0, 0, size.width, size.height, null);
		for(Button b : buttons) drawButton(g, b);
		drawItem(g, "penis_bbc", 1f/20f, 1f/2f, 1/6f, 1/6f);
		drawItem(g, "penis_longschlong", 7f/20f, 1f/2f, 1/6f, 1/6f);
		drawItem(g, "penis_triangle", 13f/20f, 1f/2f, 1/6f, 1/6f);
		g.setFont(new Font("Arial", 0, 18));
		g.drawString("More Cum: 100",(int) (1f/13f * size.width), (int) (1f/2.2f * size.height));
		g.drawString("Bigger Dick: 250",(int) (1f/2.8f * size.width), (int) (1f/2.2f * size.height));
		g.drawString("Cum faster: 50",(int) (13f/19.7f * size.width), (int) (1f/2.2f * size.height));
		g.drawString("BBC: 20",(int) (1f/13f * size.width), (int) (0.8f/1f * size.height));
		g.drawString("Long Schlong: 20",(int) (1f/2.8f * size.width), (int) (0.8f/1f * size.height));
		g.drawString("Dreieck-Cock: 20", (int) (1f/1.52f * size.width), (int) (0.8f/1f * size.height));
		
		g.setFont(new Font("Arial", 0, 30));
		g.setColor(Color.YELLOW);
		g.drawString("Geld: " + KeksspielClient.gameDisplay.player.money, (int) (0.85f * size.width), (int) (0.2f/1f * size.height));
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(checkbutton(buttonmorecum, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("more_cum");
		if(checkbutton(buttonbiggerdick, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("bigger_dick");
		if(checkbutton(buttonfastercum, e.getX(), e.getY()))  KeksspielClient.networkThread.buyItem("cum_faster"); 
		if(checkbutton(buttonbbc, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("penis_bbc");
		if(checkbutton(buttonlongschlong, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("penis_longschlong");
		if(checkbutton(buttontriangle, e.getX(), e.getY())) KeksspielClient.networkThread.buyItem("penis_triangle");
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) KeksspielClient.changeDisplay(KeksspielClient.gameDisplay);
	}
}
