package computercamp.Keksspiel.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import computercamp.Keksspiel.server.Cum;

public class ClientThread extends Thread
{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private final String name;
	private int failCounter = 0;
	
	public ClientThread(InetAddress serverAddress, String playerName) throws SocketException
	{
		Socket serverSocket = null;
		name = playerName;
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
			while(true)
			{
				while(true)
				{
					out.println("status");
					String inLine = in.readLine();
					if(inLine.startsWith("start") || inLine.startsWith("finished")) 
					{
						try{KeksspielClient.round = Integer.parseInt(inLine.split(" ")[1]);} catch(Exception e) {KeksspielClient.round = 11;}
						if(KeksspielClient.round > 10)
						{
							KeksspielClient.frame.setTitle("Keksspiel: fertig!");
							inLine = in.readLine();
							out.println("cookies");
							inLine = in.readLine();
							KeksspielClient.gameDisplay.results = new int[4];
							for(int i = 0; i < 4; i++) KeksspielClient.gameDisplay.results[i] = Integer.parseInt(inLine.split(" ")[i]);
							KeksspielClient.gameDisplay.repaint();
						}
						break; 
					}
					sleep(50);
				}
				KeksspielClient.changeDisplay(KeksspielClient.gameDisplay);
				while(true)
				{
					syncPlayers();
					KeksspielClient.frame.setTitle("Keksspiel: Runde " + KeksspielClient.round);
					boolean allCame = true;
					for(ClientPlayer p : KeksspielClient.player) if(p != null) allCame &= p.came;
					if(allCame) break;
					sleep(50);
				} 
			}
		}
		catch(Exception e) {}
	}
	
	public synchronized void registerPlayer()
	{
		out.println("add " + name.replace(" ", "_"));
		try
		{
			String inputLine = in.readLine();
			KeksspielClient.playerIndex = Integer.parseInt(inputLine.split(" ")[1]);
			KeksspielClient.gameDisplay.player = KeksspielClient.player[KeksspielClient.playerIndex];
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public synchronized void buyItem(String itemName)
	{
		out.println("shop " + itemName);
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
					ClientPlayer p = KeksspielClient.player[i];

					switch(frag[0])
					{
						case "name": p.name = frag[1]; break;
						case "money": p.money = Integer.parseInt(frag[1]); break;
						case "cum_size": p.cumSize = Float.parseFloat(frag[1]); break;
						case "jerk_duration": p.jerkDuration = Integer.parseInt(frag[1]); break;
						case "jerk": p.jerk = Integer.parseInt(frag[1]); break;
						case "came": p.came = Boolean.parseBoolean(frag[1]); p.cameLast = Boolean.parseBoolean(frag[2]); break;
						case "cum" : 
							int cumId = Integer.parseInt(frag[1]);
							if(!frag[2].equals("null"))
							{
								p.cum[cumId] = new Cum(Float.parseFloat(frag[2]), Float.parseFloat(frag[3]), p.cumSize);
							}
							else p.cum[cumId] = null;
							break;
						case "dick": p.dick = new Dick(Dick.DickType.valueOf(frag[1]), Float.parseFloat(frag[2]), Float.parseFloat(frag[3])); break;
						case "end": end = true; break;
						case "null": KeksspielClient.player[i] = null; end = true; break;
					}
				}
			}
			failCounter = 0;
			KeksspielClient.frame.repaint();
		}
		catch(Exception e)
		{
			System.out.println("Player sync failed");
			if(failCounter++ > 50) 
			{
				System.out.println("Disconnected from Server");
				System.exit(-1);
			}
			e.printStackTrace();
		}
	}
}
