package Composite;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/** <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide extends SlideItem{
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	private String title;
	protected Vector<SlideItem> items; // de slide-items worden in een Vector bewaard

	public Slide(String title, int level) {
        super(level);
		this.items = new Vector<>();
        this.title = title;
	}

	// Voeg een Composite.SlideItem toe
	public void append(SlideItem item)
	{
		this.items.addElement(item);
	}

	// Changed naming for clarity, append is for any SlideItem appendTextItem is specifically for Text Items and allows the creation by supplying teh string and level. Whereas append requires a created item to append.
	public void appendTextItem(int level, String message)
	{
		this.items.add(new TextItem(level, message));
	}

	// geef de titel van de slide
	public String getTitle()
	{
		return this.title;
	}

	// verander de titel van de slide
	public void setTitle(String newTitle)
	{
		this.title = newTitle;
	}

	// geef het betreffende Composite.SlideItem
	public SlideItem getSlideItem(int number)
	{
		return this.items.elementAt(number);//removed redundant cast
	}

	// geef alle SlideItems in een Vector
	public Vector<SlideItem> getSlideItems()
	{
		return this.items;
	}

	// geef de afmeting van de Composite.Slide
	public int getSize()
	{
		return this.items.size();
	}

	// draw the slide
//	public void draw(Graphics graphics, Rectangle area, ImageObserver imageObserver)
//	{
//		float scale = getScale(area);
//	    int y = area.y;
//	// De titel wordt apart behandeld
//	    SlideItem slideItem = new TextItem(0, getTitle());//changed name due to change in for loop and for consistency
//
//		int slideItemLevel = slideItem.getLevel();
//
//	    //slideItem.draw(area.x, y, scale, graphics, slideItemLevel, imageObserver);
//
//	    //y += slideItem.getBoundingBox(graphics, imageObserver, scale, slideItemLevel).height;
//
//	    for (int number=0; number<getSize(); number++)
//		{
//	      slideItem = getSlideItems().elementAt(number);
//		  slideItemLevel = slideItem.getLevel();
//
//	      slideItem.draw(area.x, y, scale, graphics, slideItemLevel, imageObserver);
//	      y += slideItem.getBoundingBox(graphics, imageObserver, scale, slideItemLevel).height;
//	    }
//	}
	@Override
	public void draw(int x, int y, float scale, Graphics graphics, int styleLevel, ImageObserver observer)
	{
		SlideItem slideItem = new TextItem(0, getTitle());

		slideItem.draw(x, y, scale, graphics, styleLevel, observer);
		y += slideItem.getBoundingBox(graphics, observer, scale, styleLevel).height;

		for (int number=0; number<getSize(); number++)
		{
			slideItem = getSlideItems().elementAt(number);

			slideItem.draw(x, y, scale, graphics, styleLevel, observer);

			y += slideItem.getBoundingBox(graphics, observer, scale, styleLevel).height;
		}

	}

	@Override
	public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, int styleLevel)
	{
		// Get bounding box for the title
		SlideItem titleItem = new TextItem(0, getTitle());

		Rectangle boundingBox = titleItem.getBoundingBox(graphics, observer, scale, styleLevel);

		int width = boundingBox.width;
		int height = boundingBox.height;

		// Iterate over all slide items and update width & height
		for (SlideItem item : this.items) {
			Rectangle itemBox = item.getBoundingBox(graphics, observer, scale, item.getLevel());
			width = Math.max(width, itemBox.width);
			height += itemBox.height;
		}

		return new Rectangle(0, 0, width, height);
	}

	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area)
	{
		return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
	}




}
