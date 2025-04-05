package org.worldwides.factoryMethod;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StyleFactoryTest
{
    @Test
    void testCreateStyleLevel0()
    {
        Style style = StyleFactory.createStyle(0);
        assertNotNull(style);
        assertEquals(0, style.getIndent());
        assertEquals(Color.red, style.getColor());
        assertEquals(48, style.getFontSize());
        assertEquals(20, style.getLeading());
    }

    @Test
    void testCreateStyleLevel1()
    {
        Style style = StyleFactory.createStyle(1);
        assertNotNull(style);
        assertEquals(20, style.getIndent());
        assertEquals(Color.blue, style.getColor());
        assertEquals(40, style.getFontSize());
        assertEquals(10, style.getLeading());
    }

    @Test
    void testCreateStyleLevel2()
    {
        Style style = StyleFactory.createStyle(2);
        assertNotNull(style);
        assertEquals(50, style.getIndent());
        assertEquals(Color.black, style.getColor());
        assertEquals(36, style.getFontSize());
        assertEquals(10, style.getLeading());
    }

    @Test
    void testCreateStyleLevel3()
    {
        Style style = StyleFactory.createStyle(3);
        assertNotNull(style);
        assertEquals(70, style.getIndent());
        assertEquals(Color.black, style.getColor());
        assertEquals(30, style.getFontSize());
        assertEquals(10, style.getLeading());
    }

    @Test
    void testCreateStyleLevel4()
    {
        Style style = StyleFactory.createStyle(4);
        assertNotNull(style);
        assertEquals(90, style.getIndent());
        assertEquals(Color.black, style.getColor());
        assertEquals(24, style.getFontSize());
        assertEquals(10, style.getLeading());
    }

    @Test
    void testCreateStyleDefault()
    {
        Style style = StyleFactory.createStyle(999);
        assertNotNull(style);
        assertEquals(Color.black, style.getColor());
        assertEquals(24, style.getFontSize());
        assertEquals(10, style.getLeading());
    }
}