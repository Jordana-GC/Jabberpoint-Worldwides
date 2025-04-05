package org.worldwides.presentation;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

class StyleChooserTest
{
    @Test
    void showStyleDialog_shouldReturnSelectedStyle()
    {
        try (MockedStatic<JOptionPane> mockedOptionPane = Mockito.mockStatic(JOptionPane.class))
        {
            Frame mockFrame = mock(Frame.class);
            String[] expectedStyles = {
                    "Professional (Blue Theme)",
                    "Creative (Colorful)",
                    "Academic (Formal)"
            };

            mockedOptionPane.when(() ->
                    JOptionPane.showInputDialog(
                            any(Frame.class),
                            anyString(),
                            anyString(),
                            eq(JOptionPane.QUESTION_MESSAGE),
                            isNull(),
                            eq(expectedStyles),
                            eq(expectedStyles[0])
                    )
            ).thenReturn(expectedStyles[0]);

            String result = StyleChooser.showStyleDialog(mockFrame);

            assertEquals(expectedStyles[0], result);
            mockedOptionPane.verify(() ->
                    JOptionPane.showInputDialog(
                            eq(mockFrame),
                            eq("Choose a presentation style:"),
                            eq("New Presentation Style"),
                            eq(JOptionPane.QUESTION_MESSAGE),
                            isNull(),
                            eq(expectedStyles),
                            eq(expectedStyles[0])
                    )
            );
        }
    }

    @Test
    void showStyleDialog_shouldReturnNullWhenDialogCancelled()
    {
        try (MockedStatic<JOptionPane> mockedOptionPane = Mockito.mockStatic(JOptionPane.class))
        {
            Frame mockFrame = mock(Frame.class);

            mockedOptionPane.when(() ->
                    JOptionPane.showInputDialog(any(), any(), any(), anyInt(), any(), any(), any())
            ).thenReturn(null);

            String result = StyleChooser.showStyleDialog(mockFrame);

            assertNull(result);
        }
    }
}