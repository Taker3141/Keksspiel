package computercamp.Keksspiel;

import java.awt.Color;
import java.awt.Image;

public class Dick {
	
	public float dw = 1f / 20f;
	public float dh = 1f / 10f;
	Color color = new Color(0);
	
	
	public Image getTexture() {
		
		return Ressource.get("penis_basic");
	}
	
	

}
