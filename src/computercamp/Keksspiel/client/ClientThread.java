package computercamp.Keksspiel.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread
{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private boolean finished = false;
	
	public ClientThread(InetAddress serverAddress) throws SocketException
	{
		Socket serverSocket = null;
		try
		{
			serverSocket = new Socket(serverAddress, 10000);
			System.out.println("Keksspiel Client running");

			socket = serverSocket;
			System.out.println("Connected to server");
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch(SocketException e1)
		{
			throw e1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		registerPlayer();
		try
		{
			while(!in.readLine().equals("start"));
			KeksspielClient.changeDisplay(KeksspielClient.gameDisplay);
			while(!finished)
			{
				syncPlayers();
				sleep(500);
			}
		}
		catch(Exception e) {}
	}
	
	public synchronized void registerPlayer()
	{
		out.println("add Marcell_D'avis");
		try
		{
			String inputLine = in.readLine();
			KeksspielClient.gameDisplay.player = KeksspielClient.player[Integer.parseInt(inputLine.split(" ")[1])];
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public synchronized void reportJerk(int speed, float mouseX, float mouseY)
	{
		out.println("jerk " + speed + " " + mouseX + " " + mouseY);
	}
	
	public synchronized void reportReady()
	{
		out.println("ready");
	}
	
	public synchronized void syncPlayers()
	{
		try 
		{
			String inputLine;
			for(int i = 0; i < 4; i++)
			{
				out.println("player " + i);
				boolean end = false;
				while(!end)
				{
					inputLine = in.readLine();
					String[] frag = inputLine.split(" ");
					switch(frag[0])
					{
						case "name": KeksspielClient.player[i].name = frag[1]; break;
						case "money": KeksspielClient.player[i].money = Integer.parseInt(frag[1]); break;
						case "cum_size": KeksspielClient.player[i].cumSize = Float.parseFloat(frag[1]); break;
						case "jerk_duration": KeksspielClient.player[i].jerkDuration = Integer.parseInt(frag[1]); break;
						case "jerk": KeksspielClient.player[i].jerk = Integer.parseInt(frag[1]); break;
						case "came": KeksspielClient.player[i].came = Boolean.parseBoolean(frag[1]); break;
						case "cum" : 
							KeksspielClient.player[i].cumX = Float.parseFloat(frag[1]);
							KeksspielClient.player[i].cumY = Float.parseFloat(frag[2]);
							break;
						case "dick": KeksspielClient.player[i].dick = new Dick(frag[1]); break;
						case "end": end = true; break;
						case "null": KeksspielClient.player[i] = null; end = true; break;
					}
				}
			}
			KeksspielClient.frame.repaint();
		}
		catch(Exception e)
		{
			System.out.println("Player sync failed");
			e.printStackTrace();
		}
	}
}
