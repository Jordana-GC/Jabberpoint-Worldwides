package org.worldwides.Presentation;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;

import static org.mockito.Mockito.*;

class AboutBoxTest
{
    @Test
    void testShow_valid_shouldDisplayMessageDialog()
    {
        Frame mockFrame = mock(Frame.class);
        JOptionPane mockJOptionPane = mock(JOptionPane.class);

        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class))
        {
            AboutBox.show(mockFrame);

            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(
                    eq(mockFrame),
                    contains("JabberPoint is a primitive slide-show program in Java(tm)."),
                    eq("About JabberPoint"),
                    eq(JOptionPane.INFORMATION_MESSAGE)
            ), times(1));
        }
    }

}