package computercamp.Keksspiel.client;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Ressource
{
	public static Map<String, Image> map = new HashMap<String, Image>();
	
	static
	{
		File[] resFiles = new File("res/texture").listFiles();
		for(File f : resFiles)
		{
			map.put(f.getName().split("\\.")[0].toLowerCase(), loadImage(f));
			if(f.getName().equals("Figur1.png"))
			{
				try
				{
					BufferedImage playerImage = ImageIO.read(f);
					final Color[] colors = new Color[] {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
					BufferedImage[] generatedTextures = new BufferedImage[colors.length];
					for(int i = 0; i < colors.length; i++)
						generatedTextures[i] = new BufferedImage(playerImage.getWidth(), playerImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
					for(int i = 0; i < playerImage.getWidth(); i++) for(int j = 0; j < playerImage.getHeight(); j++)
						for(int k = 0; k < colors.length; k++)
						{
							int rgb = playerImage.getRGB(i, j);
							if(((rgb & 0xFF0000) >> 16) == 255 && ((rgb & 0xFF00) >> 8) < 5 && (rgb & 0xFF) < 5)
							{
								generatedTextures[k].setRGB(i, j, colors[k].getRGB());
							}
							else generatedTextures[k].setRGB(i, j, playerImage.getRGB(i, j));
						}
					for(int i = 0; i < colors.length; i++) map.put("player" + i, generatedTextures[i]);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Image get(String key)
	{
		return map.get(key.toLowerCase());
	}
	
	private static Image loadImage(File file)
	{
		try
		{
			return ImageIO.read(file);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
