package org.worldwides.command.KeyController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.worldwides.presentation.Presentation;

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
        this.controller = new KeyController(this.mockPresentation);
        this.mockComponent = Mockito.mock(Component.class);
    }

    @Test
    void keyPressed_shouldHandleNextSlideKeys()
    {
        pressKey(KeyEvent.VK_PAGE_DOWN);
        pressKey(KeyEvent.VK_DOWN);
        pressKey(KeyEvent.VK_ENTER);
        pressKey('+');

        verify(this.mockPresentation, times(4)).nextSlide();

    }

    @Test
    void keyPressed_shouldHandlePreviousSlideKeys()
    {

        pressKey(KeyEvent.VK_PAGE_UP);
        pressKey(KeyEvent.VK_UP);
        pressKey('-');

        verify(this.mockPresentation, times(3)).prevSlide();
    }

    @Test
    void keyPressed_shouldIgnoreOtherKeys()
    {
        pressKey(KeyEvent.VK_A);

        verifyNoInteractions(this.mockPresentation);
    }

    // Helper method to test key press
    private void pressKey(int keyCode)
    {
        this.controller.keyPressed(new KeyEvent(this.mockComponent, 0, 0, 0, keyCode, (char)keyCode));
    }
}