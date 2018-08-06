package computercamp.Keksspiel.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import computercamp.Keksspiel.client.Dick;

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
			println("New connection on port " + socket.getPort() + " to " + clientSocket.getInetAddress());
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
					case "add": add(arg(frag, 1)); break;
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
						player(id);
						break;
					case "jerk": jerk(Integer.parseInt(arg(frag, 1)), Float.parseFloat(arg(frag, 2)), Float.parseFloat(arg(frag, 3))); break;
					case "shop": shop(arg(frag, 1)); break;
					default: 
						out.println("unknown " + inputLine);
						System.out.println("Unknown command: " + inputLine);
						break;
				}
			}
			println("Connection closed");
			KeksspielServer.threadList.remove(this);
			socket.close();
			in.close();
			out.close();
		} 
		catch (Exception e)
		{
			println("Oh no, this thread crashed :(");
			e.printStackTrace();
		}
	}
	
	private void shop(String item)
	{
		switch(item)
		{
			case "penis_bbc": if(player.money >= 20) 
				{
					player.money -= 20;
					player.dick = new Dick("penis_bbc");
					out.println("ok");
				}
				else out.println("no");
			break;
			case "penis_longschlong": if(player.money >= 20) 
				{
					player.money -= 20;
					player.dick = new Dick("penis_longschlong");
					out.println("ok");
				}
				else out.println("no");
			break;
			case "penis_triangle": if(player.money >= 20) 
				{
					player.money -= 20;
					player.dick = new Dick("penis_triangle");
					out.println("ok");
				}
				else out.println("no");
			break;
			case "more_cum": if(player.money >= 100)
				{
					player.money -= 100;
					player.cumSize += 0.3;
					out.println("ok");
				}
				else out.println("no");
			break;
			case "bigger_dick": if(player.money >= 250 && player.jerkDuration >= 7000) 
				{
					player.money -= 250;	
					player.jerkDuration -= 800;
					player.cumSize += 0.3;
					out.println("ok");
				}
				else out.println("no");
			break;
			case "cum_faster": if(player.money >= 50 && player.jerkDuration >= 7000)
				{
					player.money -= 50;	
					player.jerkDuration -= 1000;
					out.println("ok");
				}
				else out.println("no");
			break;
			default: 
				System.out.println("Unknown item: " + item);
				out.println("unknown " + item);
			break;
		}
	}
	
	private void player(int id)
	{
		if(id < 0 || id >= 4) {out.println("null"); return;}
		Player p = KeksspielServer.game.players[id];
		out.println("id " + id);
		if(p == null) {out.println("null"); return;}
		out.println("name " + p.name);
		out.println("money " + p.money);
		out.println("cum_size " + p.cumSize);
		out.println("jerk_duration " + p.jerkDuration);
		out.println("jerk " + p.jerk);
		out.println("came " + p.came);
		if(p.cum != null) out.println("cum " + p.cum.px + " " + p.cum.py);
		out.println("dick " + p.dick.name);
		out.println("end");
	}
	
	private void add(String name)
	{
		name = name.replace("_", " ");
		if(player != null) out.println("duplicate");
		if(KeksspielServer.gameStarted) out.println("no");
		else if(Player.pcounter < 4) 
		{
			player = new Player(name, KeksspielServer.game);
			out.println("ok " + player.id);
			println("Added player " + player.id + ": " + name);
		}
		else out.println("full");
	}
	
	private void jerk(int v, float mx, float my)
	{
		if(player.came) return;
		try
		{
			player.jerk += v;
			out.println("ok");
			if(player.jerk > player.jerkDuration) player.cum(mx, my);
		} 
		catch(Exception e) {v = (int)(mx = my = -1);}
	}
	
	private String arg(String[] fragments, int index)
	{
		if(index < fragments.length) return fragments[index];
		return "";
	}
	
	private void println(String message)
	{
		System.out.println("Thread " + threadNumber + ": " + message);
	}

	public void sendStart()
	{
		out.println("start");
	}
}
