package org.worldwides.command.MenuController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.worldwides.presentation.Presentation;
import org.worldwides.presentation.StyleChooser;

import java.awt.*;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

class NewFileTest
{
    @Mock
    private Presentation mockPresentation;
    private Frame mockFrame;
    private NewFile newFile;
    private MockedStatic<StyleChooser> mockedStyleChooser;

    @BeforeEach
    void setUp()
    {
        this.mockPresentation = mock(Presentation.class);
        this.mockFrame = mock(Frame.class);
        this.newFile = new NewFile(this.mockPresentation, this.mockFrame);
        this.mockedStyleChooser = Mockito.mockStatic(StyleChooser.class);
    }

    @AfterEach
    void tearDown()
    {
        this.mockedStyleChooser.close();
    }

    @Test
    void execute_whenStyleSelectionCancelled_shouldDoNothing()
    {
        this.mockedStyleChooser.when(() -> StyleChooser.showStyleDialog(this.mockFrame))
                .thenReturn(null);

        this.newFile.execute();

        verify(this.mockPresentation, never()).clear();
        verify(this.mockFrame, never()).repaint();
    }

    @Test
    void execute_whenSelected_shouldCreateProfessionalStyle()
    {
        testStyleCreation("Professional (Blue Theme)", "Professional Style", 2);
    }

    @Test
    void execute_whenSelected_shouldCreateCreativeStyle()
    {
        testStyleCreation("Creative (Colorful)", "Creative Style", 2);
    }

    @Test
    void execute_whenSelected_shouldCreateAcademicStyle()
    {
        testStyleCreation("Academic (Formal)", "Academic Style", 2);
    }

    @Test
    void execute_shouldIncludeCurrentDate()
    {
        this.mockedStyleChooser.when(() -> StyleChooser.showStyleDialog(this.mockFrame))
                .thenReturn("Professional (Blue Theme)");

        this.newFile.execute();

        verify(this.mockPresentation).append(argThat(slide ->
                slide.getSlideItems().stream()
                        .anyMatch(item -> item.toString().contains(LocalDate.now().toString()))
        ));
    }

    //Helper Method for testing Style Creation
    private void testStyleCreation(String styleName, String expectedTitle, int expectedItems)
    {
        this.mockedStyleChooser.when(() -> StyleChooser.showStyleDialog(this.mockFrame))
                .thenReturn(styleName);

        this.newFile.execute();

        // Verify common actions
        verify(this.mockPresentation).clear();
        verify(this.mockPresentation).setTitle("New Presentation - " + styleName);
        verify(this.mockPresentation).setSlideNumber(0);
        verify(this.mockFrame).repaint();

        // Verify slide creation
        verify(this.mockPresentation).append(argThat(slide ->
                slide.getTitle().equals(expectedTitle) &&
                        slide.getSlideItems().size() == expectedItems
        ));
    }
}