package org.worldwides.Accessor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.worldwides.Composite.BitmapItem;
import org.worldwides.Composite.Slide;
import org.worldwides.Composite.SlideItem;
import org.worldwides.Composite.TextItem;
import org.worldwides.Presentation.Presentation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

class XMLAccessorTest
{
    private static final String TEST_FILE = "test_presentation.xml";
    private static final String TEST_IMAGE = "test_image.jpg";
    private XMLAccessor xmlAccessor;
    private Presentation presentation;
    private Presentation emptyPresentation;
    private PrintStream originalErr;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    void setUp() throws IOException
    {
        this.xmlAccessor = new XMLAccessor();
        this.presentation = new Presentation();
        this.emptyPresentation = new Presentation();

        // Create a dummy image file for testing
        File imageFile = new File(TEST_IMAGE);
        if (!imageFile.exists()) {
            imageFile.createNewFile();
        }

        // Set up System.err capture
        originalErr = System.err;
        errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        this.presentation.setTitle("Test Presentation");
        Slide slide = new Slide("Test Slide", 1);
        slide.append(new TextItem(1, "Test Text"));
        slide.append(new BitmapItem(1, TEST_IMAGE));
        this.presentation.append(slide);
    }

    @AfterEach
    void tearDown() throws IOException
    {
        Files.deleteIfExists(new File(TEST_FILE).toPath());
        Files.deleteIfExists(new File(TEST_IMAGE).toPath());

        System.setErr(originalErr);
    }

    @Test
    void loadFile_shouldLogErrorForNonExistentFile() throws IOException
    {
        xmlAccessor.loadFile(emptyPresentation, "nonexistent.xml");

        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("nonexistent.xml") ||
                        errorOutput.contains("No such file"),
                "Expected error message about missing file");
    }

    @Test
    void saveAndLoad_shouldRoundTripCorrectly() throws IOException
    {
        xmlAccessor.saveFile(presentation, TEST_FILE);

        Presentation loadedPresentation = new Presentation();
        xmlAccessor.loadFile(loadedPresentation, TEST_FILE);

        assertPresentationsEqual(presentation, loadedPresentation);
    }

    @Test
    void constants_shouldHaveCorrectValues()
    {
        assertEquals("dom", XMLAccessor.DEFAULT_API_TO_USE);
        assertEquals("showtitle", XMLAccessor.SHOW_TITLE);
        assertEquals("title", XMLAccessor.SLIDE_TITLE);
        assertEquals("slide", XMLAccessor.SLIDE);
        assertEquals("item", XMLAccessor.ITEM);
        assertEquals("level", XMLAccessor.LEVEL);
        assertEquals("kind", XMLAccessor.KIND);
        assertEquals("text", XMLAccessor.TEXT);
        assertEquals("image", XMLAccessor.IMAGE);
    }

    private void assertPresentationsEqual(Presentation expected, Presentation actual)
    {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getSize(), actual.getSize());

        for (int i = 0; i < expected.getSize(); i++) {
            Slide expectedSlide = expected.getSlide(i);
            Slide actualSlide = actual.getSlide(i);

            assertEquals(expectedSlide.getTitle(), actualSlide.getTitle());
            assertEquals(expectedSlide.getSlideItems().size(), actualSlide.getSlideItems().size());

            for (int j = 0; j < expectedSlide.getSlideItems().size(); j++) {
                SlideItem expectedItem = expectedSlide.getSlideItems().get(j);
                SlideItem actualItem = actualSlide.getSlideItems().get(j);

                assertEquals(expectedItem.getClass(), actualItem.getClass());
                assertEquals(expectedItem.getLevel(), actualItem.getLevel());

                if (expectedItem instanceof TextItem) {
                    assertEquals(((TextItem) expectedItem).getText(),
                            ((TextItem) actualItem).getText());
                } else if (expectedItem instanceof BitmapItem) {
                    assertEquals(((BitmapItem) expectedItem).getName(),
                            ((BitmapItem) actualItem).getName());
                }
            }
        }
    }
}