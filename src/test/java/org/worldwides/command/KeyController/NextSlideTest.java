package org.worldwides.command.KeyController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.worldwides.presentation.Presentation;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class NextSlideTest
{
    @Mock
    private Presentation mockPresentation;
    private NextSlide nextSlide;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        this.nextSlide = new NextSlide(this.mockPresentation);
    }

    @Test
    void execute_shouldCallNextSlideOnPresentation()
    {
        this.nextSlide.execute();

        verify(this.mockPresentation).nextSlide();
        verifyNoMoreInteractions(this.mockPresentation);
    }

    @Test
    public void execute_shouldReturnCorrectPresentation()
    {
        assertEquals(this.mockPresentation, this.nextSlide.getPresentation());
    }
}