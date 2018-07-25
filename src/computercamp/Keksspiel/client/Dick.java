package computercamp.Keksspiel.client;

import java.awt.Color;
import java.awt.Image;

public class Dick {
	
	public float dw = 1f / 20f;
	public float dh = 1f / 10f;
	Color color = new Color(0);
	private Image texture;
	
	public Image getTexture() 
	{
		
		return texture;
	}
	
	public Dick(String texturename) 
	{
		texture = Ressource.get(texturename);
	}
	
	

}
