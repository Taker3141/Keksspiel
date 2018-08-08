package computercamp.Keksspiel.client;

import java.awt.Image;
import computercamp.Keksspiel.client.Dick;
import computercamp.Keksspiel.client.Ressource;
import computercamp.Keksspiel.server.Cum;

public class ClientPlayer
{
	public Dick dick = new Dick(Dick.DickType.BASIC);
	public String name = "";
	public int money = 0;
	public Cum[] cum = new Cum[3];
	public float cumSize = 1f/20f;
	public float px, py, pw, ph;
	public final int id;
	public float distanceFromCookie = -1;
	public boolean came = false, cameLast = false;
	public int jerkDuration = 10000;
	public int jerk = 0;
	public GameDisplayPanel display;
	public boolean ready = false;
	
	public static final float[] X_POSITION = {1f/10f, 2f/10f, 6f/10f, 7f/10f};

	public ClientPlayer(int id, GameDisplayPanel display)
	{
		this.id = id;
		this.px = X_POSITION[id];
		this.py = 1f/3f;
		this.pw = 1f/4f;
		this.ph = 1f/2f;
		this.display = display;
	}
	
	public Image getTexture()
	{
		return Ressource.get("player" + id);
	}
}
