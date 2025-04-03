package Composite;

import Composite.Slide;
import Composite.SlideItem;
import FactoryMethod.Style;
import FactoryMethod.StyleFactory;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/** <p>Een tekst item.</p>
 * <p>Een Composite.TextItem heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem
{
	private String text;

	public TextItem(int level, String text)
	{
		super(level);
		this.text = text;
	}

	public String getText()
	{
		return this.text == null ? "" : this.text;
	}


	// return the bounding box of the item
	public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, int styleLevel)
	{
		Style style = StyleFactory.createStyle(styleLevel);

		List<TextLayout> layouts = getLayouts(graphics, styleLevel, scale);

		int xsize = 0, ysize = (int) (style.getLeading() * scale);

        for (TextLayout layout : layouts)
        {
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > xsize)
            {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0)
            {
                ysize += (int) bounds.getHeight();
            }
            ysize += (int) (layout.getLeading() + layout.getDescent());
        }

		return new Rectangle((int) (style.getIndent() * scale), 0, xsize, ysize );
	}

	public void draw(int x, int y, float scale, Graphics graphics, int styleLevel, ImageObserver imageObserver)
	{
		Style style = StyleFactory.createStyle(styleLevel);

		List<TextLayout> layouts = getLayouts(graphics, styleLevel, scale);

		Point pen = new Point(x + (int)(style.getIndent() * scale),y + (int) (style.getLeading() * scale));

		Graphics2D g2d = (Graphics2D)graphics;

		g2d.setColor(style.getColor());

		Iterator<TextLayout> it = layouts.iterator();

		if (this.text == null || this.text.isEmpty())
		{
			return;
		}

		while (it.hasNext())
		{
			TextLayout layout = it.next();
			pen.y += (int) layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += (int) layout.getDescent();
		}
	}

	// provide the AttributedString for the item
	public AttributedString getAttributedString(int styleLevel, float scale)
	{
		Style style = StyleFactory.createStyle(styleLevel);

		AttributedString attrStr = new AttributedString(getText());

		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.text.length());

		return attrStr;
	}

	private List<TextLayout> getLayouts(Graphics graphics, int styleLevel, float scale)
	{
		Style style = StyleFactory.createStyle(styleLevel);

		List<TextLayout> layouts = new ArrayList<>();
		AttributedString attrStr = getAttributedString(styleLevel, scale);
    	Graphics2D g2d = (Graphics2D) graphics;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (Slide.WIDTH - style.getIndent()) * scale;

    	while (measurer.getPosition() < getText().length())
		{
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}

    	return layouts;
	}

	public String toString()
	{
		return "Composite.TextItem[" + getLevel()+","+getText()+"]";
	}
}
