package FactoryMethod;

import Composite.SlideItem;

public interface SlideItemFactory
{
    SlideItem createSlideItem(int level, String content);
}
