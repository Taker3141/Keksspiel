package computercamp.Keksspiel.server;

import java.awt.Image;
import java.util.Arrays;

import computercamp.Keksspiel.client.Cum;
import computercamp.Keksspiel.client.Dick;
import computercamp.Keksspiel.client.Ressource;

public class Player
{
	public Dick dick = new Dick("penis_basic");
	public String name;
	public int money = 0;
	public float cumSize = 1;
	public float px, py, pw, ph;
	public final int id;
	public float distanceFromCookie = -1;
	public boolean came = false;
	public int jerkDuration = 10000;
	public Game game;
	public boolean ready = false;
	
	public static int pcounter = 0;
	public static final float[] X_POSITION = {1f/10f, 2f/10f, 6f/10f, 7f/10f};

	public Player(String name, Game g)
	{
		super();
		id = pcounter;
		pcounter++;
		this.name = name;
		this.px = X_POSITION[id];
		this.py = 1f/3f;
		this.pw = 1f/4f;
		this.ph = 1f/2f;
		this.game = g;
		game.players[id] = this;
	}
	
	public void cum(float cumX, float cumY)
	{
		came = true;
		Cum cum = new Cum(cumX, cumY, cumSize);
		distanceFromCookie = calculateDistance(cum, 1f/2f, 4f/5f);
		System.out.println("Distance from Cookie: " + distanceFromCookie);
		game.cumList.add(cum);
		if(distanceFromCookie < 0.05) money += 35;
		else if(distanceFromCookie < 0.07) money += 20;
		else if(distanceFromCookie < 0.08) money += 10;
	}
	
	private static float calculateDistance(Cum cum, float cookieX, float cookieY)
	{
		float[] list = new float[4];
		float dx = cum.px - cookieX, dy = cum.py - cookieY;
		list[0] = (float)Math.sqrt(dx * dx + dy * dy);
		dx = (cum.px + cum.w) - cookieX; dy = cum.py - cookieY;
		list[1] = (float)Math.sqrt(dx * dx + dy * dy);
		dx = cum.px - cookieX; dy = (cum.py + cum.h) - cookieY;
		list[2] = (float)Math.sqrt(dx * dx + dy * dy);
		dx = (cum.px + cum.w) - cookieX; dy = (cum.py - cum.h) - cookieY;
		list[3] = (float)Math.sqrt(dx * dx + dy * dy);
		Arrays.sort(list);
		return list[3];
	}
	
	public Image getTexture()
	{
		return Ressource.get("player" + id);
	}
}
