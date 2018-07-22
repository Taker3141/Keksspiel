package computercamp.Keksspiel;

import java.awt.Image;
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
			map.put(f.getName().split("\\.")[0], loadImage(f));
		}
	}
	
	public static Image get(String key)
	{
		return map.get(key);
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
