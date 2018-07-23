package computercamp.Keksspiel;

import java.awt.Image;

public class Button {

	public float bx;
	public float by;
	public float bl;
	public float bh;

	
	public Image getTexture() {

		return Ressource.get("Shopupgrades");
	}


	public Button(float bx, float by, float bl, float bh) {
		super();
		this.bx = bx;
		this.by = by;
		this.bl = bl;
		this.bh = bh;
	}
	

}
