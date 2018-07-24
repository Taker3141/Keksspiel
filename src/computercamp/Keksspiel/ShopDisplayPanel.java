package computercamp.Keksspiel;

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
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(checkbutton(buttonmorecum, e.getX(), e.getY())) 
		{
			System.out.println("Clicked More Cum");
		}
		if(checkbutton(buttonbiggerdick, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Bigge Dick");
		}
		if(checkbutton(buttonfastercum, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Faster Cum");
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
