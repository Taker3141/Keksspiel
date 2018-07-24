package computercamp.Keksspiel;

import java.awt.Image;

public class Player {
	
    public Dick dick = new Dick();
	public String name;
	public int money;
	public float px;
	public float py;
	public float pw;
	public float ph;
	private static int pcounter = 0;
	public final int id;
	public float distanceFromCookie = -1;
	
	public Player(String name, int money, float px, float py, float pw, float ph) {
		super();
		this.name = name;
		this.money = money;
		this.px = px;
		this.py = py;
		this.pw = pw;
		this.ph = ph;
		
		id = pcounter;
		pcounter++;
	}
	
	

	public Image getTexture() {

		return Ressource.get("player" + id);
	}
	
	
	

}
