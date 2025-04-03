package FactoryMethod;

import java.awt.Color;
import java.awt.Font;

/** <p>FactoryMethod.Style staat voor Indent, Color, Font and Leading.</p>
 * <p>De koppeling tussen style-nummer en item-level is nu direct:
 * in Composite.Slide wordt de style opgehaald voor een item
 * met als style-nummer het item-level.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style {

	private int indent;
	private Color color;
	private Font font;
	private int fontSize;
	private int leading;

	public Style(int indent, Color color, int fontSize, int leading)
	{
		this.indent = indent;
		this.color = color;
		this.font = new Font("Helvetica", Font.BOLD,this.fontSize);
		this.fontSize = fontSize;
		this.leading = leading;
	}

	public int getIndent()
	{
		return this.indent;
	}

	public Color getColor()
	{
		return this.color;
	}

	public Font getFont()
	{
		return this.font;
	}

	public int getFontSize()
	{
		return this.fontSize;
	}

	public int getLeading()
	{
		return this.leading;
	}

	public String toString()
	{
		return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
	}

	public Font getFont(float scale)
	{
		return this.font.deriveFont(this.fontSize * scale);
	}
}
