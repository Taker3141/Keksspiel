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
	public int money;
	public float cumSize = 1;
	public float px, py, pw, ph;
	public final int id;
	public float distanceFromCookie = -1;
	public boolean came = false;
	public int jerkDuration = 10000;
	public Game game;
	
	private static int pcounter = 0;

	public Player(String name, int money, float px, float py, float pw, float ph, Game g)
	{
		super();
		this.name = name;
		this.money = money;
		this.px = px;
		this.py = py;
		this.pw = pw;
		this.ph = ph;
		this.game = g;

		id = pcounter;
		pcounter++;
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
