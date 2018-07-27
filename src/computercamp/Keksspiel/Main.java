package computercamp.Keksspiel;

import javax.swing.JFrame;

public class Main
{
	public static JFrame frame;
	public static DisplayPanel display;
	public static Player[] player = new Player[4];
	public static MenuDisplayPanel menu;
	
	public static void main(String[] args)
	{
		player[0] = new Player("", 0,1f/10f , 1f/3f, 1f/4f, 1f/2f);
		player[1] = new Player("",0, 2f/10f , 1f/3f, 1f/4f, 1f/2f);
		player[2] = new Player("",0, 6f/10f , 1f/3f, 1f/4f, 1f/2f);
		player[3] = new Player("",0, 7f/10f , 1f/3f, 1f/4f, 1f/2f);
		
		frame = new JFrame("Keksspiel");
		frame.setSize(1024, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new MenuDisplayPanel();
		frame.add(display);
		frame.setVisible(true);
		menu = new MenuDisplayPanel();
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
