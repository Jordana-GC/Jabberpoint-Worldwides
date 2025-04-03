package Command.KeyController;

import Command.Command;
import Presentation.Presentation;

public class PreviousSlide extends Command
{
    public PreviousSlide(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        this.presentation.prevSlide();
    }
}
