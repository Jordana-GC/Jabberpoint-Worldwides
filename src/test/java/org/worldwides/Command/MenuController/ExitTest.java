package org.worldwides.Command.MenuController;

import org.junit.jupiter.api.Test;
import org.worldwides.Presentation.Presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ExitTest
{
    @Test
    void execute_shouldCallPresentationExit()
    {
        Presentation mockPresentation = mock(Presentation.class);
        Exit command = new Exit(mockPresentation);

        command.execute();

        verify(mockPresentation).exit(0);
    }
}