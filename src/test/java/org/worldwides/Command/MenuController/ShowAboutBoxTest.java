package org.worldwides.Command.MenuController;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.worldwides.Presentation.AboutBox;
import org.worldwides.Presentation.Presentation;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

class ShowAboutBoxTest
{
    @Test
    void execute_shouldShowAboutBox()
    {
        Presentation mockPresentation = mock(Presentation.class);
        Frame mockFrame = mock(Frame.class);

        ShowAboutBox showAboutBox = new ShowAboutBox(mockPresentation, mockFrame);

        try (MockedStatic<AboutBox> mockedAboutBox = Mockito.mockStatic(AboutBox.class))
        {
            showAboutBox.execute();

            mockedAboutBox.verify(() ->
                    AboutBox.show(eq(mockFrame))
            );
        }
    }
}