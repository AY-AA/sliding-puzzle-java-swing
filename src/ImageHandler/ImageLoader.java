package ImageHandler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * loads a picture from user's pc to the game
 *
 */
public class ImageLoader 
{
	
	public static BufferedImage loadImage(String path)
	{
		try 
		{
			return ImageIO.read(ImageLoader.class.getResource(path));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
		
	}

}
