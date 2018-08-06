package computercamp.Keksspiel.client;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.*;

@SuppressWarnings("serial")
public class MenuDisplayPanel extends DisplayPanel
{
	public JTextField ip, name;
	public Button help = new Button(1f/1.38f, 1f/1.4f, 2f/6f, 2f/5f);
	
	public MenuDisplayPanel() 
	{
		addMouseListener(this);
		this.setLayout(null);
		KeksspielClient.frame.addKeyListener(this);
		
		ip = new JTextField();
		ip.setSize(300, 32);
		ip.setLocation(340, 250);
		ip.addActionListener((ActionEvent e) ->
				{
					try
					{
						String[] numbers = ip.getText().split("\\.");
						if(numbers.length != 4)
						{
							ip.setText("Ungültiges Format");
							return;
						}
						byte[] bytes = new byte[4];
						for(int i = 0; i < 4; i++) bytes[i] = (byte)Integer.parseInt(numbers[i]);
						KeksspielClient.networkThread = new ClientThread(InetAddress.getByAddress(bytes), name.getText());
						KeksspielClient.networkThread.start();
						KeksspielClient.changeDisplay(new WaitingDisplayPanel());
					}
					catch(SocketException ex1)
					{
						ip.setText("Kein Server unter dieser Addresse gefunden!");
						ex1.printStackTrace();
					}
					catch(Exception ex) 
					{
						ip.setText("Ungültiges Format");
						ex.printStackTrace();
					}
				});
		
		add(ip);
		
		name = new JTextField();
		name.setSize(300, 32);
		name.setLocation(340, 350);
		add(name);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Ressource.get("Menu"),  0, 0, size.width, size.height, null); 
		g.setFont(new Font("Arial",0,18));
		g.drawString("Server-IP-Adresse: ", 340, 240);
		g.drawString("Name: ", 340, 340);
	}

	public void mouseClicked(MouseEvent e) 
	{
		if(checkbutton(help, e.getX(), e.getY())) KeksspielClient.changeDisplay(new HelpDisplayPanel());
	}
}
