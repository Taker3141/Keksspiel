package computercamp.Keksspiel.server;

import java.util.*;

import computercamp.Keksspiel.client.Dick;

public class Player
{
	public Dick dick = new Dick(Dick.DickType.BASIC);
	public String name;
	public int money = 0;
	public float cumSize = 1f/20f;
	public final int id;
	public float distanceFromCookie = -1;
	public boolean came = false, cameLast = false;
	public int jerkDuration = 50000;
	public int jerk = 0;
	public boolean ready = false;
	public Cum[] cum = new Cum[3];
	public int cumCounter = 0;
	
	public static int pcounter = 0;
	public static final float[] X_POSITION = {1f/10f, 2f/10f, 6f/10f, 7f/10f};

	public Player(String name)
	{
		id = pcounter;
		pcounter++;
		this.name = name;
		KeksspielServer.players[id] = this;
	}
	
	public void cum(float cumX, float cumY)
	{
		came = true;
		if(dick.type == Dick.DickType.TRIANGLE)
		{
			Random r = new Random();
			for(int i = 0; i < 3; i++) cum[i] = new Cum(cumX + r.nextFloat() * 0.1f - 0.05f, cumY + r.nextFloat() * 0.1f - 0.05f, cumSize);
		}
		else cum[0] = new Cum(cumX, cumY, cumSize);
		{
			distanceFromCookie = calculateDistance(1f/2f + 1f/40f, 4f/5f + 1f/20f);
			System.out.println("Distance from Cookie: " + distanceFromCookie);
			float moneyMultiplier = dick.type == Dick.DickType.LONGSCHLONG ? 1.5f : 1;
			if (distanceFromCookie < 0.06) money += 50 * moneyMultiplier;
			else if (distanceFromCookie < 0.10) money += 25 * moneyMultiplier;
			else if (distanceFromCookie < 0.20) money += 10 * moneyMultiplier;
		}
		cameLast = true;
		Player[] players = KeksspielServer.players;
		for(int i = 0; i < players.length; i++) if(i != id && players[i] != null) cameLast &= players[i].came;
		if(cameLast) 
		{
			int moneyCounter = 0;
			for(Player p : players) if(p != null && p.distanceFromCookie <= 0.06f) for(int i = 0; i < cum.length; i++) if(cum[i] != null)
			{
				p.cum[i].px += (X_POSITION[id] + 1f/10f) - 0.5f;
				p.cum[i].py += (1f/3f + 1f/5f) - 0.8f;
				moneyCounter += 25;
				if(p.dick.type == Dick.DickType.BBC) moneyCounter += 10;
				cumCounter++;
			}
			money = money >= moneyCounter ? money - moneyCounter : 0;
		}
		KeksspielServer.checkFinished();
		System.out.println(name + " came!");
	}
	
	private float calculateDistance(float cookieX, float cookieY)
	{
		float[] list = new float[12];
		for (int i = 0; i < cum.length; i++)
		{
			Cum c = cum[i];
			if(c == null) continue;
			float dx = (c.px - c.w / 2) - cookieX, dy = (c.py - c.h / 2) - cookieY;
			list[0] = (float) Math.sqrt(dx * dx + dy * dy);
			dx = (c.px + c.w / 2) - cookieX;
			dy = (c.py - c.h / 2) - cookieY;
			list[1] = (float) Math.sqrt(dx * dx + dy * dy);
			dx = (c.px - c.w / 2) - cookieX;
			dy = (c.py + c.h / 2) - cookieY;
			list[2] = (float) Math.sqrt(dx * dx + dy * dy);
			dx = (c.px + c.w / 2) - cookieX;
			dy = (c.py + c.h / 2) - cookieY;
			list[3] = (float) Math.sqrt(dx * dx + dy * dy);
		}
		Arrays.sort(list);
		return list[11];
	}

	public void reset()
	{
		distanceFromCookie = -1;
		jerk = 0;
		ready = false;
	}
}
