package computercamp.Keksspiel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
			while (true)
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
			try
			{
				if(serverSocket != null) serverSocket.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private static class ServerThread extends Thread
	{
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		private final int threadNumber;
		public static int threadCounter = 0;
		
		public ServerThread(Socket clientSocket)
		{
			threadNumber = threadCounter++;
			try
			{
				socket = clientSocket;
				System.out.println("Thread" + threadNumber + ": New connection on port " + socket.getPort() + " to " + clientSocket.getInetAddress());
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out.println("Willkommen beim Keksspiel!");
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
					System.out.println("Thread" + threadNumber + ": " + inputLine);
				}
				socket.close();
				in.close();
				out.close();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
