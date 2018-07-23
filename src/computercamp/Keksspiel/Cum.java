package computercamp.Keksspiel;

import java.awt.Image;

public class Cum
{
	public float px, py;
	public float w, h;
	
	public Cum(float px, float py)
	{
		this.px = px;
		this.py = py;
		w = 1f/20f;
		h = 1f/10f;
	}

	public Image getTexture()
	{
		return Ressource.get("cum");
	}
}
