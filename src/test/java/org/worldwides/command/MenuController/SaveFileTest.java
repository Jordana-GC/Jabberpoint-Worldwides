package org.worldwides.command.MenuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.worldwides.accessor.XMLAccessor;
import org.worldwides.presentation.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveFileTest
{
    private Presentation mockPresentation;
    private Frame mockFrame;
    private SaveFile saveFile;

    @BeforeEach
    void setUp()
    {
        this.mockPresentation = mock(Presentation.class);
        this.mockFrame = mock(Frame.class);
        this.saveFile = new SaveFile(this.mockPresentation, this.mockFrame);
    }

    @Test
    void execute_shouldSaveWhenUserApproves() throws IOException
    {
        try (MockedConstruction<JFileChooser> ignored = Mockito.mockConstruction(JFileChooser.class,
                (mock, context) ->
                {
                    when(mock.showSaveDialog(any())).thenReturn(JFileChooser.APPROVE_OPTION);
                    File mockFile = mock(File.class);
                    when(mockFile.getAbsolutePath()).thenReturn("test.xml");
                    when(mock.getSelectedFile()).thenReturn(mockFile);
                });
             MockedConstruction<XMLAccessor> mockedAccessor = Mockito.mockConstruction(XMLAccessor.class);
             MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class))
        {

            this.saveFile.execute();

            XMLAccessor accessor = mockedAccessor.constructed().get(0);
            verify(accessor).saveFile(this.mockPresentation, "test.xml");

            mockedJOptionPane.verify(() ->
                    JOptionPane.showMessageDialog(
                            eq(this.mockFrame),
                            contains("Saved successfully"),
                            eq("Success"),
                            eq(JOptionPane.INFORMATION_MESSAGE)
                    )
            );
        }
    }

    @Test
    void execute_shouldHandleSaveFailure()
    {
        try (MockedConstruction<JFileChooser> ignored = Mockito.mockConstruction(JFileChooser.class,
                (mock, context) ->
                {
                    when(mock.showSaveDialog(any())).thenReturn(JFileChooser.APPROVE_OPTION);
                    when(mock.getSelectedFile()).thenReturn(new File("test.xml"));
                });
             MockedConstruction<XMLAccessor> mockedAccessor = Mockito.mockConstruction(XMLAccessor.class,
                     (mock, context) ->
                     {
                         doThrow(new IOException()).when(mock).saveFile(any(), anyString());
                     });
             MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class))
        {

            this.saveFile.execute();

            mockedJOptionPane.verify(() ->
                    JOptionPane.showMessageDialog(
                            eq(this.mockFrame),
                            contains("Failed to save"),
                            eq("Error"),
                            eq(JOptionPane.ERROR_MESSAGE)
                    )
            );
        }
    }

    @Test
    void getCurrentPath_shouldReturnSavedPath()
    {
        try
        {
            Field currentPathField = SaveFile.class.getDeclaredField("currentPath");
            currentPathField.setAccessible(true);
            currentPathField.set(this.saveFile, "test.xml");

            assertEquals("test.xml", this.saveFile.getCurrentPath());
        }
        catch (Exception e)
        {
            fail("Failed to test getCurrentPath due to reflection error", e);
        }
    }
}