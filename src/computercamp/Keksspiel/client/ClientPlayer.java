package computercamp.Keksspiel.client;

import java.awt.Image;
import computercamp.Keksspiel.client.Dick;
import computercamp.Keksspiel.client.Ressource;

public class ClientPlayer
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
	public int jerk = 0;
	public GameDisplayPanel display;
	public boolean ready = false;
	
	public static int pcounter = 0;
	public static final float[] X_POSITION = {1f/10f, 2f/10f, 6f/10f, 7f/10f};

	public ClientPlayer(String name, int id, GameDisplayPanel display)
	{
		this.id = id;
		this.name = name;
		this.px = X_POSITION[id];
		this.py = 1f/3f;
		this.pw = 1f/4f;
		this.ph = 1f/2f;
		this.display = display;
	}
	
	public void cum(float cumX, float cumY)
	{
		
	}
	
	public Image getTexture()
	{
		return Ressource.get("player" + id);
	}
}
