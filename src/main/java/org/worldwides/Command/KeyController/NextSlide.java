package org.worldwides.Command.KeyController;

import org.worldwides.Command.Command;
import org.worldwides.Presentation.Presentation;

public class NextSlide extends Command
{
    public NextSlide(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        this.presentation.nextSlide();
    }
}
