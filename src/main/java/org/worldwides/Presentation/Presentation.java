package org.worldwides.Presentation;

import org.worldwides.Composite.Slide;

import java.util.ArrayList;


/**
 * <p>Presentation.Presentation houdt de slides in de presentatie bij.</p>
 * <p>Er is slechts ��n instantie van deze klasse aanwezig.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String title;
	private ArrayList<Slide> slides = null;
	private int currentSlideNumber = 0;
	private SlideViewerComponent slideViewComponent;

	public Presentation()
	{
		this.slideViewComponent = null;
		clear();
	}

	public Presentation(SlideViewerComponent slideViewerComponent)
	{
		this.slideViewComponent = slideViewerComponent;
		clear();
	}

	public int getSize()
	{
		return this.slides.size();
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent)
	{
		this.slideViewComponent = slideViewerComponent;
	}

	public int getSlideNumber()
	{
		return this.currentSlideNumber;
	}

	public void setSlideNumber(int number)
	{
		this.currentSlideNumber = number;
		if (this.slideViewComponent != null)
		{
			this.slideViewComponent.update(this, getCurrentSlide());
		}
	}

	public void prevSlide() {
		if (this.currentSlideNumber > 0)
		{
			setSlideNumber(this.currentSlideNumber - 1);
	    }
	}

	public void nextSlide()
	{
		if (this.currentSlideNumber < (slides.size()-1))
		{
			setSlideNumber(this.currentSlideNumber + 1);
		}
	}

	public void clear()
	{
		this.slides = new ArrayList<Slide>();
		setSlideNumber(-1);
	}

	public void append(Slide slide)
	{
		this.slides.add(slide);
	}

	public Slide getSlide(int number)
	{
		if (number < 0 || number >= getSize())
		{
			return null;
	    }

		return this.slides.get(number);
	}

	public Slide getCurrentSlide()
	{
		return getSlide(currentSlideNumber);
	}

	public void exit(int n)
	{
		System.exit(n);
	}
}
