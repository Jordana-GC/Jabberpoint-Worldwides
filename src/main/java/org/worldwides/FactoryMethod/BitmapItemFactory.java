package org.worldwides.FactoryMethod;

import org.worldwides.Composite.BitmapItem;
import org.worldwides.Composite.SlideItem;

public class BitmapItemFactory implements SlideItemFactory
{

    public BitmapItemFactory()
    {

    }

    @Override
    public SlideItem createSlideItem(int level, String content)
    {
        return new BitmapItem(level, content);
    }
}
