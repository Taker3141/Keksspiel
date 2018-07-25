package computercamp.Keksspiel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class KeksspielServer
{
	public static void main(String[] args)
	{
		ServerSocket serverSocket = null;
		try
		{
			List<ServerThread> threadList = new ArrayList<ServerThread>();
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
}
