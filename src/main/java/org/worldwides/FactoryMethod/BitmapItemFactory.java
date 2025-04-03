package FactoryMethod;

import Composite.BitmapItem;
import Composite.SlideItem;

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
