package Composite;

import Composite.SlideItem;
import FactoryMethod.Style;
import FactoryMethod.StyleFactory;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items hebben de verantwoordelijkheid om zichzelf te tekenen.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem extends SlideItem
{
	private BufferedImage bufferedImage;
	private String imageName;


	// level represents the item level; name for the name of the file containing the image

	public BitmapItem(int level, String imageName)
	{
		super(level);
		this.imageName = imageName;
		try
		{
			this.bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error loading image: " + imageName, e);
		}
	}

	// geef de bestandsnaam van de afbeelding
	public String getName()
	{
		return imageName;
	}

	@Override
	public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, int styleLevel)
	{
		Style style = StyleFactory.createStyle(styleLevel);

		return new Rectangle((int) (style.getIndent() * scale), 0, (int) (this.bufferedImage.getWidth(observer) * scale), ((int) (style.getLeading() * scale)) + (int) (this.bufferedImage.getHeight(observer) * scale));
	}

	@Override
	public void draw(int x, int y, float scale, Graphics graphics, int styleLevel, ImageObserver observer)
	{
		Style style = StyleFactory.createStyle(styleLevel);
		int width = x + (int) (style.getIndent() * scale);
		int height = y + (int) (style.getLeading() * scale);
		graphics.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale), (int) (bufferedImage.getHeight(observer)*scale), observer);

	}

	public String toString()
	{
		return "Composite.BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}
