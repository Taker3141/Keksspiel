package computercamp.Keksspiel.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;

public class KeksspielClient
{
	private static boolean finished = false;
	public static JFrame frame;
	public static DisplayPanel display;
	public static GameDisplayPanel gameDisplay;
	public static ClientPlayer[] player = new ClientPlayer[4];
	public static ClientThread networkThread;
	
	public static void main(String[] args)
	{
		frame = new JFrame("Keksspiel");
		frame.setSize(1024, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = gameDisplay = new GameDisplayPanel();
		frame.add(display);
		frame.setVisible(true);
		
		for(int i = 0; i < 4; i++) player[i] = new ClientPlayer(i, gameDisplay);
		
		Socket serverSocket = null;
		try
		{
			serverSocket = new Socket(InetAddress.getByAddress(new byte[]{127, 0, 0, 1}), 10000);
			System.out.println("Keksspiel Client running");
			networkThread = new ClientThread(serverSocket);
			networkThread.start();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			while(!finished) try {Thread.sleep(1000);} catch(Exception e) {}
			if(serverSocket != null) try
			{
				serverSocket.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
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
