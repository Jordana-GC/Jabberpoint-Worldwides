package FactoryMethod;

import Composite.SlideItem;
import Composite.TextItem;

public class TextItemFactory implements SlideItemFactory
{
    public TextItemFactory()
    {

    }

    @Override
    public SlideItem createSlideItem(int level, String content)
    {
        return new TextItem(level, content);
    }
}
