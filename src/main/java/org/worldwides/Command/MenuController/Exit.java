package Command.MenuController;

import Command.Command;
import Presentation.Presentation;

public class Exit extends Command
{
    public Exit(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        this.presentation.exit(0);
    }
}
