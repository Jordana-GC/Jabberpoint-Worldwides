package org.worldwides.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.worldwides.Composite.Slide;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PresentationTest
{
    private Slide slide;
    private Presentation presentation;

    @BeforeEach
    void setup()
    {
        this.slide = new Slide("Slay Slide",2);
        this.presentation = new Presentation();
    }

    @Test
    void testInitialPresentationIsEmpty()
    {
        assertEquals(0, this.presentation.getSize());
        assertNull(this.presentation.getTitle());
        assertNull(this.presentation.getCurrentSlide());
    }

    @Test
    void testAppendSlide_validSlide_shouldIncreaseSize()
    {
        this.presentation.append(this.slide);

        assertEquals(1, this.presentation.getSize());
        assertEquals(this.slide, this.presentation.getSlide(0));
    }

    @Test
    void testAppendSlide_nullSlide_shouldThrow()
    {
        assertThrows(IllegalArgumentException.class, ()-> this.presentation.append(null));
    }

    @Test
    void testSetSlideNumber_newNumber_shouldUpdateViewerComponent()
    {
        SlideViewerComponent mockViewer = mock(SlideViewerComponent.class);
        Presentation mockPresentation = new Presentation(mockViewer);

        mockPresentation.append(this.slide);

        mockPresentation.setSlideNumber(0);

        verify(mockViewer).update(mockPresentation, this.slide);
    }

    @Test
    void testPrevSlide_decreaseSlideNumber_shouldDecrease()
    {
        SlideViewerComponent mockViewer = mock(SlideViewerComponent.class);
        Presentation presentation = new Presentation(mockViewer);
        presentation.append(new Slide("Test slide",2));
        presentation.append(new Slide("Another test slide",3));

        presentation.setSlideNumber(1);
        presentation.prevSlide();

        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testNextSlide_increaseSlideNumber_shouldIncrease()
    {
        SlideViewerComponent mockViewer = mock(SlideViewerComponent.class);
        Presentation presentation = new Presentation(mockViewer);
        presentation.append(new Slide("Test slide",2));
        presentation.append(new Slide("Another test slide",3));

        presentation.setSlideNumber(0);
        presentation.nextSlide();

        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testGetSlide_invalidIndex_shouldReturnNull()
    {
        Presentation presentation = new Presentation();
        presentation.append(new Slide("Another test slide",3));

        assertNull(presentation.getSlide(-1));
        assertNull(presentation.getSlide(10));
    }

    @Test
    void testSetTitle_nullTitle_shouldNotThrow()
    {
        assertDoesNotThrow(() -> this.presentation.setTitle(null));
        assertNull(this.presentation.getTitle());
    }
}