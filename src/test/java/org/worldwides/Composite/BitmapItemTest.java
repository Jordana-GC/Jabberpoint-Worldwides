package org.worldwides.Composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.worldwides.FactoryMethod.Style;
import org.worldwides.FactoryMethod.StyleFactory;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BitmapItemTest
{
    private BufferedImage dummyImage;
    private ImageObserver observer;
    private Graphics graphics;
    private Style styleMock;

    @BeforeEach
    void setUp()
    {
        this.dummyImage = mock(BufferedImage.class);
        this.observer = mock(ImageObserver.class);
        this.graphics = mock(Graphics.class);
        this.styleMock = mock(Style.class);

        when(this.dummyImage.getWidth(this.observer)).thenReturn(100);
        when(this.dummyImage.getHeight(this.observer)).thenReturn(50);
    }

    @Test
    void testConstructorAndGetName_valid_shouldReturnName()
    {
        BitmapItem item = new BitmapItem(1, "src/test/resources/terribleTerrible.png");

        assertEquals("src/test/resources/terribleTerrible.png", item.getName());
    }

    @Test
    void testBoundingBoxCalculation_valid_shouldReturn()
    {
        try (MockedStatic<StyleFactory> styleFactoryMock = mockStatic(StyleFactory.class))
        {
            styleFactoryMock.when(() -> StyleFactory.createStyle(2)).thenReturn(styleMock);
            when(this.styleMock.getIndent()).thenReturn(10);
            when(this.styleMock.getLeading()).thenReturn(20);

            BitmapItem item = new BitmapItem(2, "src/test/resources/terribleTerrible.png")
            {
                { this.bufferedImage = dummyImage; }
            };

            Rectangle box = item.getBoundingBox(this.graphics, this.observer, 2.0f);

            assertEquals(20, box.x);
            assertEquals(0, box.y);
            assertEquals(200, box.width);
            assertEquals(140, box.height);
        }
    }

    @Test
    void testDraw_valid_shouldDraw()
    {
        try (MockedStatic<StyleFactory> styleFactoryMock = mockStatic(StyleFactory.class))
        {
            styleFactoryMock.when(() -> StyleFactory.createStyle(3)).thenReturn(styleMock);
            when(this.styleMock.getIndent()).thenReturn(5);
            when(this.styleMock.getLeading()).thenReturn(15);

            BitmapItem item = new BitmapItem(3, "src/test/resources/terribleTerrible.png")
            {
                { this.bufferedImage = dummyImage; }
            };

            item.draw(50, 60, 1.5f, this.graphics, this.observer);

            verify(this.graphics).drawImage(
                    eq(this.dummyImage),
                    eq(50 + (int)(5 * 1.5f)),
                    eq(60 + (int)(15 * 1.5f)),
                    eq((int)(100 * 1.5f)),
                    eq((int)(50 * 1.5f)),
                    eq(this.observer)
            );
        }
    }

    @Test
    void testToString_valid_shouldPass()
    {
        BitmapItem item = new BitmapItem(4, "src/test/resources/terribleTerrible.png")
        {
            { this.bufferedImage = dummyImage; }
        };

        assertEquals("BitmapItem[4,src/test/resources/terribleTerrible.png]", item.toString());
    }
}