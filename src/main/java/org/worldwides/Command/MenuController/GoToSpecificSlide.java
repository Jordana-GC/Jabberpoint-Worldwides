package Command.MenuController;

import Command.Command;
import Presentation.Presentation;

import javax.swing.*;

public class GoToSpecificSlide extends Command
{
    protected static final String PAGE_NR = "Page number?";

    public GoToSpecificSlide(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        String pageNumberStr = JOptionPane.showInputDialog((Object)PAGE_NR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        this.presentation.setSlideNumber(pageNumber - 1);
    }
}
