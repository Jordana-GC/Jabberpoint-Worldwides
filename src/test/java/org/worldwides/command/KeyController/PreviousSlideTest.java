package org.worldwides.command.KeyController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.worldwides.presentation.Presentation;

import static org.mockito.Mockito.*;

class PreviousSlideTest
{
    @Mock
    private Presentation mockPresentation;
    private PreviousSlide previousSlide;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        this.previousSlide = new PreviousSlide(this.mockPresentation);
    }

    @Test
    void execute_shouldCallPrevSlideOnPresentation()
    {
        this.previousSlide.execute();

        verify(this.mockPresentation).prevSlide();
        verifyNoMoreInteractions(this.mockPresentation);
    }

    @Test
    void execute_shouldNotInteractWithPresentationBeyondPrevSlide() {

        this.previousSlide.execute();

        verify(this.mockPresentation, times(1)).prevSlide();
        verify(this.mockPresentation, never()).setSlideNumber(anyInt());
        verify(this.mockPresentation, never()).getSlideNumber();
    }

}
