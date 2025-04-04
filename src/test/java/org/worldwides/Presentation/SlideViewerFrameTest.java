package org.worldwides.Presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

class SlideViewerFrameTest
{
    private SlideViewerFrame slideViewerFrame;

    @BeforeEach
    void setUp()
    {
        Presentation mockPresentation = mock(Presentation.class);

        this.slideViewerFrame = new SlideViewerFrame("JabberPoint", mockPresentation);
    }

    @Test
    void testWindowTitleAndSize_valid_shouldPass()
    {
        assertEquals("Jabberpoint 1.6 - OU", this.slideViewerFrame.getTitle());

        assertEquals(1200, this.slideViewerFrame.getWidth());
        assertEquals(800, this.slideViewerFrame.getHeight());
    }

    @Test
    void testSlideViewerComponentAdded()
    {
        Component[] components = this.slideViewerFrame.getContentPane().getComponents();

        assertEquals(1, components.length, "Only one component");
        assertInstanceOf(SlideViewerComponent.class, components[0], "Component should be SlideViewerComponent");
    }
}