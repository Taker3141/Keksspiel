package computercamp.Keksspiel.client;

import javax.swing.JFrame;

public class KeksspielClient
{
	public static JFrame frame;
	public static DisplayPanel display;
	public static MenuDisplayPanel menu;
	public static GameDisplayPanel gameDisplay;
	public static ClientPlayer[] player = new ClientPlayer[4];
	public static int playerIndex;
	public static ClientThread networkThread;
	
	public static void main(String[] args)
	{
		frame = new JFrame("Keksspiel");
		frame.setSize(1024, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu = new MenuDisplayPanel();
		display = menu;
		frame.add(display);
		frame.setVisible(true);
		
		for(int i = 0; i < 4; i++) player[i] = new ClientPlayer(i, gameDisplay);
	}
	
	public static void connetToServer()
	{
		
	}
	
	public static void changeDisplay(DisplayPanel newDisplay)
	{
		frame.remove(display);
		frame.removeKeyListener(display);
		display = newDisplay;
		frame.add(display);
		frame.addKeyListener(display);
		frame.requestFocus();
		frame.repaint();
		frame.setSize(frame.getWidth() + 1, frame.getHeight());
		frame.setSize(frame.getWidth() - 1, frame.getHeight());
	}
}
