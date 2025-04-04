package org.worldwides.Command.KeyController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.worldwides.Presentation.Presentation;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

class KeyControllerTest
{
    @Mock
    private Presentation mockPresentation;

    private KeyController controller;
    private Component mockComponent;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        controller = new KeyController(mockPresentation);
        mockComponent = Mockito.mock(Component.class);
    }

    @Test
    void keyPressed_shouldHandleNextSlideKeys()
    {
        pressKey(KeyEvent.VK_PAGE_DOWN);
        pressKey(KeyEvent.VK_DOWN);
        pressKey(KeyEvent.VK_ENTER);
        pressKey('+');

        verify(mockPresentation, times(4)).nextSlide();

    }

    @Test
    void keyPressed_shouldHandlePreviousSlideKeys()
    {

        pressKey(KeyEvent.VK_PAGE_UP);
        pressKey(KeyEvent.VK_UP);
        pressKey('-');

        verify(mockPresentation, times(3)).prevSlide();
    }

    @Test
    void keyPressed_shouldIgnoreOtherKeys()
    {
        pressKey(KeyEvent.VK_A);

        verifyNoInteractions(mockPresentation);
    }

    // Helper method to test key press
    private void pressKey(int keyCode)
    {
        controller.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, keyCode, (char)keyCode));
    }
}