package computercamp.Keksspiel.client;

import java.awt.Image;

public class Cookie
{
	public int px, py;
	
	public Image getTexture()
	{
		return Ressource.get("Keks");
	}
	
}
