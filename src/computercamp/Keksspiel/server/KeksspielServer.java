package computercamp.Keksspiel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class KeksspielServer
{
	public static Game game = new Game();
	public static List<ServerThread> threadList;
	public static boolean gameStarted = false;
	
	public static void main(String[] args)
	{
		ServerSocket serverSocket = null;
		try
		{
			threadList = new ArrayList<ServerThread>();
			serverSocket = new ServerSocket(10000);
			System.out.println("Keksspiel Server running");
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				ServerThread thread = new ServerThread(clientSocket);
				threadList.add(thread);
				thread.start();
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
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

	public static void checkReady()
	{
		boolean ready = true;
		for(Player p : game.players) if(p != null) ready &= p.ready;
		if(ready) for(ServerThread thread : threadList) thread.sendStart();
		gameStarted = true;
		System.out.println("Started Game!");
	}
}
