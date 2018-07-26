package computercamp.Keksspiel.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread
{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	public final int threadNumber;
	public static int threadCounter = 0;
	public Player player;
	
	public ServerThread(Socket clientSocket)
	{
		threadNumber = threadCounter++;
		try
		{
			socket = clientSocket;
			System.out.println("Thread" + threadNumber + ": New connection on port " + socket.getPort() + " to " + clientSocket.getInetAddress());
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		try
		{
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				String[] frag = inputLine.split(" ");
				switch(arg(frag, 0))
				{
					case "add": 
						if(player != null) out.println("duplicate");
						else if(Player.pcounter < 4) 
						{
							player = new Player(arg(frag, 1), KeksspielServer.game);
							out.println("ok");
							println("Added player " + player.id + ": " + arg(frag, 1));
						}
						else out.println("full");
						break;
					case "ready": 
						if(player != null) 
						{
							player.ready = true;
							out.println("ok");
							println(player.name + " is ready");
							KeksspielServer.checkReady();
						}
						break;
					case "player":
						int id;
						try {id = Integer.parseInt(arg(frag, 1));} catch(Exception e) {id = -1;}
						if(id < 0 || id >= 4) {out.println("null"); break;}
						Player p = KeksspielServer.game.players[id];
						if(p == null) {out.println("null"); break;}
						out.println("id " + p.id);
						out.println("name " + p.name);
						out.println("money " + p.money);
						out.println("cum_size " + p.cumSize);
						out.println("jerk " + p.jerkDuration);
						out.println("came " + p.came);
						out.println("dick " + p.dick.name);
						break;
				}
			}
			println("Connection closed");
			socket.close();
			in.close();
			out.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String arg(String[] fragments, int index)
	{
		if(index < fragments.length) return fragments[index];
		return "";
	}
	
	private void println(String message)
	{
		System.out.println("Thread" + threadNumber + ": " + message);
	}

	public void sendStart()
	{
		out.println("start");
	}
}
