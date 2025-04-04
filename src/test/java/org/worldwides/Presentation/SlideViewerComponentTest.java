package org.worldwides.Presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.worldwides.Composite.Slide;


import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class SlideViewerComponentTest
{
    private SlideViewerComponent component;
    private Presentation mockPresentation;
    private Slide mockSlide;
    private JFrame mockFrame;
    private Graphics mockGraphics;

    @BeforeEach
    void setUp()
    {
        this.mockPresentation = mock(Presentation.class);
        this.mockSlide = mock(Slide.class);
        this.mockFrame = mock(JFrame.class);
        this.mockGraphics = mock(Graphics.class);

        this.component = new SlideViewerComponent(this.mockPresentation, this.mockFrame);
    }

    @Test
    void testGetPreferredSize_shouldReturnSlideDimensions()
    {
        Dimension preferredSize = this.component.getPreferredSize();

        assertEquals(Slide.WIDTH, preferredSize.width);
        assertEquals(Slide.HEIGHT, preferredSize.height);
    }

    @Test
    void testUpdate_nullSlide_shouldNotThrow()
    {
        assertDoesNotThrow(() -> component.update(mockPresentation, null));
    }

    @Test
    void testUpdate_withValidSlide_shouldUpdateStateAndSetTitle()
    {
        when(this.mockPresentation.getTitle()).thenReturn("I have so many tests Presentation");

        this.component.update(this.mockPresentation, this.mockSlide);


        assertEquals(this.mockPresentation, this.component.getPresentation());
        assertEquals(this.mockSlide, this.component.getSlide());

        verify(mockFrame).setTitle("I have so many tests Presentation");
    }

    @Test
    void testPaintComponent_withNoSlide_shouldThrow()
    {
        when(mockPresentation.getSlideNumber()).thenReturn(-1);
        assertThrows(IllegalArgumentException.class, ()-> this.component.paintComponent(this.mockGraphics));
    }

    @Test
    void testPaintComponent_withValidSlide_shouldDrawSlide()
    {

        when(this.mockPresentation.getSlideNumber()).thenReturn(0);
        when(this.mockPresentation.getSize()).thenReturn(1);
        this.component.update(this.mockPresentation, this.mockSlide);

        this.component.paintComponent(this.mockGraphics);

        verify(this.mockGraphics).drawString(contains("Slide"), anyInt(), anyInt());
        verify(this.mockSlide).draw(anyInt(), anyInt(), anyFloat(), eq(this.mockGraphics), eq(this.component));
    }

    @Test
    void testConstructor_validFields_shouldInitialize()
    {
        assertNotNull(this.component);
        assertEquals("Current slide", this.component.getSlide().getTitle());
        assertEquals(this.mockPresentation, this.component.getPresentation());
        assertEquals(this.mockFrame, this.component.getFrame());
    }
}