package org.worldwides.Composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.worldwides.FactoryMethod.Style;
import org.worldwides.FactoryMethod.StyleFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;

class TextItemTest
{
    private Graphics2D graphics;
    private ImageObserver observer;

    @BeforeEach
    void setUp()
    {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = image.createGraphics();
        this.observer = (Image img, int infoFlags, int x, int y, int width, int height) -> true;
    }

    @Test
    void testGetBoundingBox_shouldReturnExpectedRectangle()
    {
        Style styleMock = mock(Style.class);
        when(styleMock.getIndent()).thenReturn(10);
        when(styleMock.getLeading()).thenReturn(20);
        when(styleMock.getFont(anyFloat())).thenReturn(new Font("Serif", Font.PLAIN, 12));

        try (MockedStatic<StyleFactory> styleFactoryMock = mockStatic(StyleFactory.class))
        {
            styleFactoryMock.when(() -> StyleFactory.createStyle(2)).thenReturn(styleMock);

            TextItem item = new TextItem(2, "Hello world");

            Rectangle box = item.getBoundingBox(this.graphics, this.observer, 2.0f);

            assertNotNull(box);
        }
    }

    @Test
    void testDraw_shouldRenderWithoutErrors()
    {
        Style styleMock = mock(Style.class);
        when(styleMock.getIndent()).thenReturn(10);
        when(styleMock.getLeading()).thenReturn(20);
        when(styleMock.getFont(anyFloat())).thenReturn(new Font("Serif", Font.PLAIN, 12));
        when(styleMock.getColor()).thenReturn(Color.BLACK);

        try (MockedStatic<StyleFactory> styleFactoryMock = mockStatic(StyleFactory.class))
        {
            styleFactoryMock.when(() -> StyleFactory.createStyle(2)).thenReturn(styleMock);

            TextItem item = new TextItem(2, "Slayyyy");

            item.draw(0, 0, 1.0f, this.graphics, this.observer);
        }
    }

    @Test
    void testGetAttributedString_shouldReturnAttributedString()
    {
        TextItem textItem = new TextItem(1, "Testttt");
        AttributedString result = textItem.getAttributedString(1, 1.0f);

        assertNotNull(result, "AttributedString should not be null");
    }

}