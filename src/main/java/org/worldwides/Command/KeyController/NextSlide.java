package Command.KeyController;

import Command.Command;
import Presentation.Presentation;

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
