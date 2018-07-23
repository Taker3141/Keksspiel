package computercamp.Keksspiel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main
{
	public static JFrame frame;
	public static JPanel display;
	public static Player[] player = new Player[4];
	
	public static void main(String[] args)
	{
		player[0] = new Player("", 1f/10f , 1f/3f, 1f/4f, 1f/2f);
		player[1] = new Player("", 2f/10f , 1f/3f, 1f/4f, 1f/2f);
		player[2] = new Player("", 6f/10f , 1f/3f, 1f/4f, 1f/2f);
		player[3] = new Player("", 7f/10f , 1f/3f, 1f/4f, 1f/2f);
		
		frame = new JFrame("Keksspiel");
		frame.setSize(1024, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new GameDisplayPanel();
		frame.add(display);
		frame.setVisible(true);
		
	}
	
}
