package computercamp.Keksspiel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class KeksspielServer
{
	public static List<ServerThread> threadList;
	public static boolean gameStarted = false;
	public static int round = 0;
	public static Player[] players = new Player[4];
	
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
		for(Player p : players) if(p != null) ready &= p.ready;
		if(!ready) return;
		gameStarted = true;
		for(Player p : players) if(p != null) p.ready = false;
		if(round++ >= 10)
		{
			gameStarted = false;
		}
		System.out.println("Starting Round " + round + "!");
	}
	
	public static void checkFinished()
	{
		boolean finished = true;
		for(Player p : players) if(p != null) finished &= p.came;
		if(finished) 
		{
			for(Player p : players) if(p != null) p.reset();
			gameStarted = false;
			System.out.println("Round finished!");
		}
	}

	public static void checkEmpty()
	{
		boolean empty = true;
		for(Player p : players) empty &= p == null;
		if(!empty) return;
		System.out.println("Everyone disconnected, shutting down");
		System.exit(0);
	}
}
