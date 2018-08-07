package computercamp.Keksspiel.server;


import java.util.Arrays;

import computercamp.Keksspiel.client.Dick;

public class Player
{
	public Dick dick = new Dick("penis_basic");
	public String name;
	public int money = 0;
	public float cumSize = 1f/20f;
	public final int id;
	public float distanceFromCookie = -1;
	public boolean came = false, cameLast = false;
	public int jerkDuration = 10000;
	public int jerk = 0;
	public Game game;
	public boolean ready = false;
	public Cum cum;
	
	public static int pcounter = 0;
	public static final float[] X_POSITION = {1f/10f, 2f/10f, 6f/10f, 7f/10f};

	public Player(String name, Game g)
	{
		super();
		id = pcounter;
		pcounter++;
		this.name = name;
		this.game = g;
		game.players[id] = this;
	}
	
	public void cum(float cumX, float cumY)
	{
		came = true;
		cum = new Cum(cumX, cumY, cumSize);
		distanceFromCookie = calculateDistance(1f/2f, 4f/5f);
		System.out.println("Distance from Cookie: " + distanceFromCookie);
		game.cumList.add(cum);
		if(distanceFromCookie < 0.10) money += 50;
		else if(distanceFromCookie < 0.15) money += 25;
		else if(distanceFromCookie < 0.20) money += 10;
		cameLast = true;
		for(int i = 0; i < game.players.length; i++) if(i != id && game.players[i] != null) cameLast &= game.players[i].came;
		if(cameLast) 
		{
			int cumCounter = 0;
			for(Player p : game.players) if(p != null && p.distanceFromCookie <= 0.1f)
			{
				p.cum.px += (X_POSITION[id] + 1f/10f) - 0.5f;
				p.cum.py += (1f/3f + 1f/5f) - 0.8f;
				cumCounter += 25;
			}
			money = money >= cumCounter ? money - cumCounter : 0;
		}
		KeksspielServer.checkFinished();
		System.out.println(name + " came!");
	}
	
	private float calculateDistance(float cookieX, float cookieY)
	{
		float[] list = new float[4];
		float dx = (cum.px - cum.w / 2) - cookieX, dy = (cum.py - cum.h / 2) - cookieY;
		list[0] = (float)Math.sqrt(dx * dx + dy * dy);
		dx = (cum.px + cum.w / 2) - cookieX; dy = (cum.py - cum.h / 2) - cookieY;
		list[1] = (float)Math.sqrt(dx * dx + dy * dy);
		dx = (cum.px - cum.w / 2) - cookieX; dy = (cum.py + cum.h / 2) - cookieY;
		list[2] = (float)Math.sqrt(dx * dx + dy * dy);
		dx = (cum.px + cum.w / 2) - cookieX; dy = (cum.py + cum.h / 2) - cookieY;
		list[3] = (float)Math.sqrt(dx * dx + dy * dy);
		Arrays.sort(list);
		return list[3];
	}

	public void reset()
	{
		distanceFromCookie = -1;
		jerk = 0;
		ready = false;
	}
}
