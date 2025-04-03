package Command.KeyController;

import Command.Command;
import Presentation.Presentation;

public class Quit extends Command
{
    public Quit(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        System.exit(0);
    }
}
