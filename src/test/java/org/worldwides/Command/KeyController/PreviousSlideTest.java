package org.worldwides.Command.KeyController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.worldwides.Presentation.Presentation;

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

        previousSlide = new PreviousSlide(mockPresentation);
    }

    @Test
    void execute_shouldCallPrevSlideOnPresentation()
    {
        previousSlide.execute();

        verify(mockPresentation).prevSlide();
        verifyNoMoreInteractions(mockPresentation);
    }

    @Test
    void execute_shouldNotInteractWithPresentationBeyondPrevSlide() {

        previousSlide.execute();

        verify(mockPresentation, times(1)).prevSlide();
        verify(mockPresentation, never()).setSlideNumber(anyInt());
        verify(mockPresentation, never()).getSlideNumber();
    }

}
