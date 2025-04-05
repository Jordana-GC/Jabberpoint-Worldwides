package org.worldwides.jabberPoint;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.worldwides.accessor.Accessor;
import org.worldwides.accessor.XMLAccessor;
import org.worldwides.presentation.Presentation;
import org.worldwides.presentation.SlideViewerFrame;

import javax.swing.*;
import java.io.IOException;

class JabberPointTest
{
    @Test
    void testMain_withNoArguments_shouldLoadDemoPresentation() throws IOException
    {
        Presentation mockPresentation = mock(Presentation.class);
        Accessor mockAccessor = mock(Accessor.class);

        try (MockedStatic<Accessor> accessorStaticMock = mockStatic(Accessor.class);
             MockedConstruction<Presentation> presentationConstruction = mockConstruction(Presentation.class, (mock, context) -> {
                 when(mock.getSlideNumber()).thenReturn(0);
             });

             MockedConstruction<SlideViewerFrame> frameConstruction = mockConstruction(SlideViewerFrame.class))
        {
            accessorStaticMock.when(Accessor::getDemoAccessor).thenReturn(mockAccessor);
            doNothing().when(mockAccessor).loadFile(any(Presentation.class), anyString());
            JabberPoint.main(new String[]{});
            accessorStaticMock.verify(Accessor::getDemoAccessor, times(1));
            verify(mockAccessor).loadFile(any(Presentation.class), eq(""));
        }
    }

    @Test
    void testMain_withFileArgument_shouldLoadFile() throws IOException
    {
        XMLAccessor xmlAccessorSpy = spy(new XMLAccessor());

        try (MockedConstruction<XMLAccessor> accessorConstruction = mockConstruction(XMLAccessor.class, (mock, context) -> {
                         doNothing().when(mock).loadFile(any(Presentation.class), eq("test.xml"));
        });
         MockedConstruction<Presentation> presentationConstruction = mockConstruction(Presentation.class);
         MockedConstruction<SlideViewerFrame> frameConstruction = mockConstruction(SlideViewerFrame.class))
        {
            JabberPoint.main(new String[]{"test.xml"});

            verify(accessorConstruction.constructed().get(0)).loadFile(any(Presentation.class), eq("test.xml"));
        }
    }

    @Test
    void testMain_whenIOExceptionOccurs_shouldShowErrorDialog()
    {
        try (MockedConstruction<XMLAccessor> accessorConstruction = mockConstruction(XMLAccessor.class, (mock, context) -> {
            doThrow(new IOException("Test IO failure")).when(mock).loadFile(any(Presentation.class), anyString());
        });
         MockedConstruction<SlideViewerFrame> frameConstruction = mockConstruction(SlideViewerFrame.class);
         MockedConstruction<Presentation> presentationConstruction = mockConstruction(Presentation.class);

         MockedStatic<JOptionPane> optionPaneMock = mockStatic(JOptionPane.class))
        {
            JabberPoint.main(new String[]{"blankFile.xml"});

            optionPaneMock.verify(() -> JOptionPane.showMessageDialog(
                    isNull(),
                    contains("IO Error"),
                    contains("Jabberpoint Error"),
                    eq(JOptionPane.ERROR_MESSAGE)), times(1));
        }
    }
}