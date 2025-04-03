package Command.MenuController;

import Command.Command;
import Presentation.AboutBox;
import Presentation.Presentation;

import java.awt.*;

public class ShowAboutBox extends Command
{
    private Frame frame;

    public ShowAboutBox(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        AboutBox.show(this.frame);
    }
}
