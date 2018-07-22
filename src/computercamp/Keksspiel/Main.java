package computercamp.Keksspiel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main
{
	public static JFrame frame;
	public static JPanel display;
	
	public static void main(String[] args)
	{
		frame = new JFrame("Keksspiel");
		frame.setSize(2048, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new GameDisplayPanel();
		frame.add(display);
		frame.setVisible(true);
	}
}
