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
import java.io.IOException;

import static org.mockito.Mockito.*;

class OpenFileTest
{
    private Presentation mockPresentation;
    private Frame mockFrame;
    private OpenFile openFile;

    @BeforeEach
    void setUp()
    {
        this.mockPresentation = mock(Presentation.class);
        this.mockFrame = mock(Frame.class);
        this.openFile = new OpenFile(this.mockPresentation, this.mockFrame);
    }

    @Test
    void execute_shouldLoadFileSuccessfully() throws IOException
    {
        try (MockedConstruction<XMLAccessor> mockedAccessor = Mockito.mockConstruction(XMLAccessor.class,
                (mock, context) ->
                {
                    doNothing().when(mock).loadFile(any(Presentation.class), anyString());
                }))
        {

            this.openFile.execute();

            XMLAccessor mockAccessor = mockedAccessor.constructed().get(0);
            verify(mockAccessor).loadFile(this.mockPresentation, "test.xml");
            verify(this.mockPresentation).clear();
            verify(this.mockPresentation).setSlideNumber(0);
            verify(this.mockFrame).repaint();
        }
    }

    @Test
    void execute_shouldHandleIOException()
    {
        try (MockedConstruction<XMLAccessor> mockedAccessor = Mockito.mockConstruction(XMLAccessor.class,
                (mock, context) ->
                {
                    doThrow(new IOException("Test error"))
                            .when(mock).loadFile(any(), anyString());
                });
             MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class))
        {

            mockedJOptionPane.when(() ->
                    JOptionPane.showMessageDialog(any(), anyString(), anyString(), anyInt())
            ).thenAnswer(invocation -> null);

            this.openFile.execute();

            verify(this.mockPresentation).clear();
            verify(this.mockFrame).repaint();
        }
    }

    @Test
    void execute_shouldShowErrorDialogOnIOException()
    {
        try (MockedConstruction<XMLAccessor> mockedAccessor = Mockito.mockConstruction(XMLAccessor.class,
                (mock, context) ->
                {
                    doThrow(new IOException("Test error"))
                            .when(mock).loadFile(any(), anyString());
                });
             MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class))
        {

            this.openFile.execute();

            mockedJOptionPane.verify(() ->
                    JOptionPane.showMessageDialog(
                            eq(this.mockFrame),
                            contains("IO Exception:"),
                            eq("Load Error"),
                            eq(JOptionPane.ERROR_MESSAGE)
                    )
            );
            verify(this.mockPresentation).clear();
            verify(this.mockFrame).repaint();
        }
    }
}