package org.worldwides.FactoryMethod;

import org.worldwides.Composite.SlideItem;
import org.worldwides.Composite.TextItem;

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
