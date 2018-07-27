package computercamp.Keksspiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MenuDisplayPanel extends DisplayPanel{
	
	public JTextField ip;
	
	public MenuDisplayPanel() 
	{
		addMouseListener(this);
		this.setLayout(null);
		Main.frame.addKeyListener(this);
		
		ip = new JTextField();
		ip.setSize(300,32);
		ip.setLocation(340, 250);
		ip.setVisible(false);
		
		add(ip);
	}
	
	public Button start = new Button(1f/19.8f, 1f/1.4f, 1f/6f, 1f/5f);
	public Button multiplayer = new Button(1f/2.5f, 1f/1.4f, 1f/6f, 1f/5f);
	public Button hilfe = new Button(1f/1.38f, 1f/1.4f, 2f/6f, 2f/5f);
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("Menü"),  0, 0, size.width, size.height, null);
		if(ip.isVisible())
		{
			g.setFont(new Font("Arial",0,18));
			g.drawString("Bitte gib die IP-Adresse ein: ", 340, 240);
		}
	}
	
    @Override
	public void keyPressed(KeyEvent e)
	{
    	System.out.println("key pressed");
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			Main.changeDisplay(Main.menu);
		}		
    	
	}

	public void mouseClicked(MouseEvent e) 
	{
		if(checkbutton(start, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Start");
		    Main.changeDisplay(new GameDisplayPanel());
	    }
		if(checkbutton(multiplayer, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Multiplayer");
			ip.setVisible(true);
			repaint();
		}
		if(checkbutton(hilfe, e.getX(), e.getY())) 
		{
			System.out.println("Clicked Help");
			Main.changeDisplay(new HelpDisplayPanel());
		}
	}
}
