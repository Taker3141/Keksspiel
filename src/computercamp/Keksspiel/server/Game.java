package computercamp.Keksspiel.server;

import java.util.ArrayList;
import java.util.List;

import computercamp.Keksspiel.client.Cum;

public class Game
{
	public Player[] players;
	public List<Cum> cumList = new ArrayList<Cum>();
	
	public Game(Player[] players)
	{
		this.players = players;
	}
}
