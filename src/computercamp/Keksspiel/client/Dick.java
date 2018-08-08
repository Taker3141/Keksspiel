package computercamp.Keksspiel.client;

import java.awt.Image;

public class Dick 
{	
	public float dw = 1f / 20f;
	public float dh = 1f / 10f;
	public DickType type;
	
	public Dick(DickType type) 
	{
		this.type = type;
	}
	
	public Dick(DickType type, float width, float height) 
	{
		this(type);
		dw = width; dh = height;
	}
	
	public Image getTexture() 
	{
		return type.texture;
	}
	
	public static enum DickType
	{
		BASIC("penis_basic"), BBC("penis_bbc"), LONGSCHLONG("penis_longschlong"), TRIANGLE("penis_triangle");
		
		private Image texture;
		public String textureName;
		
		private DickType(String textureName)
		{
			this.textureName = textureName;
			texture = Ressource.get(textureName);
		}
	}
}
