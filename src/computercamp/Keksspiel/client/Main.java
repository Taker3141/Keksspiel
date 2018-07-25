package computercamp.Keksspiel.client;

import javax.swing.JFrame;

import computercamp.Keksspiel.server.Player;

public class Main
{
	public static JFrame frame;
	public static DisplayPanel display;
	public static Player[] player = new Player[4];
	
	public static void main(String[] args)
	{		
		frame = new JFrame("Keksspiel");
		frame.setSize(1024, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new GameDisplayPanel();
		frame.add(display);
		frame.setVisible(true);
		
	}
	
	public static void changeDisplay(DisplayPanel newDisplay)
	{
		frame.remove(display);
		frame.removeKeyListener(display);
		display = newDisplay;
		frame.add(display);
		frame.addKeyListener(display);
		frame.repaint();
		frame.setSize(frame.getWidth() + 1, frame.getHeight());
		frame.setSize(frame.getWidth() - 1, frame.getHeight());
	}
	
}
