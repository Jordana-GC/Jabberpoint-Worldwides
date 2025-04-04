package org.worldwides.FactoryMethod;

import org.worldwides.Composite.SlideItem;

public interface SlideItemFactory
{
    SlideItem createSlideItem(int level, String content);
}
