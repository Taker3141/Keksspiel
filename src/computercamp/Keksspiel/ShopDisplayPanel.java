package computercamp.Keksspiel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShopDisplayPanel extends DisplayPanel
{	
	public Button buttonmorecum = new Button(1f/18f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonbiggerdick = new Button(6f/18f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonfastercum = new Button(12f/19f, 1f/3f, 1f/6f, 1f/5f);
	public Button buttonbbc = new Button(1f/18f, 2f/3f, 1f/ 6f, 1f/ 5f);
	public Button buttonlongschlong = new Button(6f/18f, 2f/3f, 1f/6f, 1f/5f);
	public Button buttontriangle = new Button(12f/19f, 2f/3f, 1f/6f, 1f/5f);
	public Button[] buttons;
	
	public ShopDisplayPanel() 
	{
		addMouseListener(this);	
		Main.frame.addKeyListener(this);
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
		g.setFont(new Font("Arial",0,26));
		g.drawString("Coins: 50",(int) (13f/19.7f * size.width), (int) (1f/2.2f * size.height));
		g.drawString("Coins: 100",(int) (1f/13f * size.width), (int) (1f/2.2f * size.height));
		
	
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(checkbutton(buttonmorecum, e.getX(), e.getY())) 
		{
			System.out.println("Clicked More Cum");
			
			if(Main.player[0].money >= 100)
			{
				Main.player[0].money = Main.player[0].money - 100;	
				Main.player[0].cum += 0.5;
			}	
	    }
		if(checkbutton(buttonbiggerdick, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Bigger Dick");
			
		}
		if(checkbutton(buttonfastercum, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Faster Cum");
			
			if(checkbutton(buttonfastercum, e.getX(), e.getY()))
			{
				if(Main.player[0].money >= 50 && GameDisplayPanel.JERK_DURATION >= 7000)
				{
					Main.player[0].money = Main.player[0].money - 50;	
					GameDisplayPanel.JERK_DURATION -= 1000;
				}	
			}
		}
		if(checkbutton(buttonbbc, e.getX(), e.getY())) 
		{
			System.out.println("Clicked BBC");
		}
		if(checkbutton(buttonlongschlong, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Longschlong");
		}
		if(checkbutton(buttontriangle, e.getX(), e.getY())) 
		{
			System.out.println("Clicked triangledick");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		{
			System.out.println("Clicked ESCAPE");
			Main.changeDisplay(new GameDisplayPanel());
		}
	}
}
