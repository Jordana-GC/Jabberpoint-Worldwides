package org.worldwides.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SlideTest
{
    private Slide slide;
    private ImageObserver observer;

    @BeforeEach
    void setUp()
    {
        this.slide = new Slide("Test Slide", 1);
        this.observer = (image, x, y, width, height, infoflags) -> true;
    }

    @Test
    void testGetTitle_valid_shouldReturnCorrectTitle()
    {
        assertEquals("Test Slide", this.slide.getTitle());
    }

    @Test
    void testSetTitle_valid_shouldUpdateTitle()
    {
        this.slide.setTitle("Updated Slide");
        assertEquals("Updated Slide", this.slide.getTitle());
    }

    @Test
    void testAppend_valid_shouldAddSlideItem()
    {
        SlideItem item = new TextItem(1, "New Item");
        this.slide.append(item);
        assertEquals(1, this.slide.getSize());
        assertEquals(item, this.slide.getSlideItem(0));
    }

    @Test
    void testAppend_nullValue_shouldThrow()
    {
        assertThrows(IllegalArgumentException.class, ()-> this.slide.append(null));
    }

    @Test
    void testAppendTextItem_valid_shouldAddTextItem()
    {
        this.slide.appendTextItem(2, "New Text Item");
        assertEquals(1, this.slide.getSize());
        assertInstanceOf(TextItem.class, this.slide.getSlideItem(0));
    }

    @Test
    void testGetBoundingBox_valid_shouldReturnCorrectBoundingBox()
    {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        SlideItem titleItem = new TextItem(0, this.slide.getTitle());
        Rectangle expectedBoundingBox = titleItem.getBoundingBox(graphics, this.observer, 1.0f);
        Rectangle boundingBox = this.slide.getBoundingBox(graphics, this.observer, 1.0f);

        assertEquals(expectedBoundingBox.width, boundingBox.width);
        assertTrue(boundingBox.height >= expectedBoundingBox.height);

        graphics.dispose();
    }

    @Test
    void testDraw_valid_shouldCallDrawOnAllSlideItems()
    {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        SlideItem item1 = new TextItem(1, "Item 1");
        SlideItem item2 = new TextItem(2, "Item 2");
        this.slide.append(item1);
        this.slide.append(item2);

        this.slide.draw(0, 0, 1.0f, graphics, this.observer);

        graphics.dispose();
    }

    @Test
    void testGetSize_valid_shouldReturnCorrectSize()
    {
        assertEquals(0, this.slide.getSize());
        this.slide.append(new TextItem(1, "Test Text"));

        assertEquals(1, this.slide.getSize());
    }

    @Test
    void testGetSlideItems_valid_shouldReturnCorrectSlideItems()
    {
        SlideItem item = new TextItem(1, "Test Text");
        this.slide.append(item);
        Vector<SlideItem> items = this.slide.getSlideItems();

        assertEquals(1, ((Vector<?>) items).size());
        assertEquals(item, items.get(0));
    }
}
