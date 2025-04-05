package org.worldwides.accessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.worldwides.composite.BitmapItem;
import org.worldwides.composite.Slide;
import org.worldwides.composite.SlideItem;
import org.worldwides.composite.TextItem;
import org.worldwides.presentation.Presentation;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DemoPresentationTest
{
    private Presentation presentation;
    private DemoPresentation demoPresentation;

    @BeforeEach
    void setUp()
    {
        this.presentation = new Presentation();
        this.demoPresentation = new DemoPresentation();
    }

    @Test
    void loadFile_shouldCreateDemoPresentation() throws IOException
    {
        this.demoPresentation.loadFile(this.presentation, "unused");

        assertEquals("Demo Presentation", this.presentation.getTitle());
        assertEquals(3, this.presentation.getSize());

        Slide firstSlide = this.presentation.getSlide(0);
        assertEquals("JabberPoint", firstSlide.getTitle());

        // First slide should have 10 text items as defined in DemoPresentation
        assertEquals(10, firstSlide.getSlideItems().size());

        assertTrue(firstSlide.getSlideItems().get(0) instanceof TextItem);

        Slide thirdSlide = this.presentation.getSlide(2);
        SlideItem lastItem = thirdSlide.getSlideItems().get(thirdSlide.getSlideItems().size() - 1);
        assertTrue(lastItem instanceof BitmapItem);
        assertEquals("JabberPoint.gif", ((BitmapItem) lastItem).getName());
    }

    @Test
    void saveFile_shouldThrowIllegalStateException()
    {
        assertThrows(IllegalStateException.class, () -> {
            this.demoPresentation.saveFile(this.presentation, "unused");
        });
    }
}