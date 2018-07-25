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
				println(inputLine);
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
	
	private void println(String message)
	{
		System.out.println("Thread" + threadNumber + ": " + message);
	}
}
