package org.worldwides.Command.MenuController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.worldwides.Presentation.Presentation;

import javax.swing.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GoToSpecificSlideTest
{
    @Mock
    private Presentation mockPresentation;
    private MockedStatic<JOptionPane> mockedJOptionPane;

    @BeforeEach
    void setUp()
    {
        this.mockPresentation = mock(Presentation.class);
        this.mockedJOptionPane = Mockito.mockStatic(JOptionPane.class);
    }

    @AfterEach
    void tearDown()
    {
        this.mockedJOptionPane.close();
    }

    @Test
    void execute_whenValidNumberEntered_shouldSetSlide()
    {
        when(this.mockPresentation.getSize()).thenReturn(5);

        this.mockedJOptionPane.when(() -> JOptionPane.showInputDialog(any()))
                .thenReturn("3");

        new GoToSpecificSlide(this.mockPresentation).execute();

        verify(this.mockPresentation).setSlideNumber(2);
    }

    @Test
    void execute_whenInvalidNumberEntered_shouldShowError()
    {
        this.mockedJOptionPane.when(() -> JOptionPane.showInputDialog(any()))
                .thenReturn("abc");

        new GoToSpecificSlide(this.mockPresentation).execute();

        verify(this.mockPresentation, never()).setSlideNumber(anyInt());
    }

    @Test
    void execute_whenDialogCancelled_shouldDoNothing()
    {
        this.mockedJOptionPane.when(() -> JOptionPane.showInputDialog(any()))
                .thenReturn(null);

        new GoToSpecificSlide(this.mockPresentation).execute();

        verify(this.mockPresentation, never()).setSlideNumber(anyInt());
    }

    @Test
    void execute_whenNumberTooHigh_shouldShowRangeError()
    {
        when(this.mockPresentation.getSize()).thenReturn(3);
        this.mockedJOptionPane.when(() -> JOptionPane.showInputDialog(any()))
                .thenReturn("5");

        new GoToSpecificSlide(this.mockPresentation).execute();

        verify(this.mockPresentation, never()).setSlideNumber(anyInt());
    }
}