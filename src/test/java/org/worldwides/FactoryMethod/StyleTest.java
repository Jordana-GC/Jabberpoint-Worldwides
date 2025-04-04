package org.worldwides.FactoryMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Font;

import static org.junit.jupiter.api.Assertions.*;

class StyleTest
{
    private Style style;
    private int indent;
    private Color color;
    private int fontSize;
    private int leading;

    @BeforeEach
    void setUp()
    {
        this.indent = 20;
        this.color = Color.RED;
        this.fontSize = 12;
        this.leading = 15;

        this.style = new Style(this.indent, this.color, this.fontSize, this.leading);
    }

    @Test
    void testGetIndent()
    {
        assertEquals(this.indent, this.style.getIndent());
    }

    @Test
    void testGetColor()
    {
        assertEquals(this.color, this.style.getColor());
    }

    @Test
    void testGetFontSize()
    {
        assertEquals(this.fontSize, this.style.getFontSize());
    }

    @Test
    void testGetLeading()
    {
        assertEquals(this.leading, this.style.getLeading());
    }

    @Test
    void testGetFont()
    {
        assertNotNull(this.style.getFont());
        assertEquals("Helvetica", this.style.getFont().getName());
        assertEquals(Font.BOLD, this.style.getFont().getStyle());
        assertEquals(this.fontSize, this.style.getFont(1).getSize());
    }

}