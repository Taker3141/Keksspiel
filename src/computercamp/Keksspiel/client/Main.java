package computercamp.Keksspiel.client;

import javax.swing.JFrame;

public class Main
{
	public static JFrame frame;
	public static DisplayPanel display;
	public static GameDisplayPanel gameDisplay;
	public static ClientPlayer[] player = new ClientPlayer[4];
	
	public static void main(String[] args)
	{		
		frame = new JFrame("Keksspiel");
		frame.setSize(1024, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = gameDisplay = new GameDisplayPanel();
		frame.add(display);
		frame.setVisible(true);
		
		player[0] = new ClientPlayer("", 0, gameDisplay);
		player[1] = new ClientPlayer("", 1, gameDisplay);
		player[2] = new ClientPlayer("", 2, gameDisplay);
		player[3] = new ClientPlayer("", 3, gameDisplay);
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
