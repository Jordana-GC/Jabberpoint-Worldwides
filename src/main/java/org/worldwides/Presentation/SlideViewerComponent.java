package org.worldwides.Presentation;

import org.worldwides.Composite.Slide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;


/** <p>Presentation.SlideViewerComponent is een grafische component die Slides kan laten zien.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {
		
	private Slide slide;
	private Font labelFont; // removed redundant initializer
	private Presentation presentation; // removed redundant initializer
	private JFrame frame;// removed redundant initializer
	
	private static final long serialVersionUID = 227L;
	
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONT_NAME = "Dialog";
	private static final int FONT_STYLE = Font.BOLD;
	private static final int FONT_HEIGHT = 10;
	private static final int X_POS = 1100;
	private static final int Y_POS = 20;

	public SlideViewerComponent(Presentation presentation, JFrame frame)
	{
		this.slide = new Slide("Current slide", 0);
		setBackground(BGCOLOR);
		this.presentation = presentation;
		this.labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_HEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	public void update(Presentation presentation, Slide data)
	{
		if (data == null) {
			repaint();
			return;
		}
		this.presentation = presentation;
		this.slide = data;
		repaint();
		this.frame.setTitle(presentation.getTitle());
	}

// teken de slide
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics); // Call superclass method to ensure proper rendering

		graphics.setColor(BGCOLOR);
		graphics.fillRect(0, 0, getSize().width, getSize().height);

		if (this.presentation.getSlideNumber() < 0 || slide == null)
		{
			return;
		}

		graphics.setFont(this.labelFont);
		graphics.setColor(COLOR);
		graphics.drawString("Composite.Slide " + (1 + this.presentation.getSlideNumber()) + " of " + this.presentation.getSize(), X_POS, Y_POS);

		Rectangle area = new Rectangle(0, Y_POS, getWidth(), (getHeight() - Y_POS));

		float scale = Math.min((float) area.width / Slide.WIDTH, (float) area.height / Slide.HEIGHT);

		this.slide.draw(area.x, area.y, scale, graphics, this.slide.getLevel(), this);
	}
}
