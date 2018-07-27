package computercamp.Keksspiel.server;

import java.awt.Image;

import computercamp.Keksspiel.client.Ressource;

public class Cum
{
	public float px, py;
	public float w, h;
	
	public Cum(float px, float py, float size)
	{
		this.px = px;
		this.py = py;
		w = 1f/20f * size;
		h = 1f/10f * size;
	}

	public Image getTexture()
	{
		return Ressource.get("cum");
	}
}
