package org.worldwides.command.MenuController;

import org.worldwides.command.Command;
import org.worldwides.presentation.AboutBox;
import org.worldwides.presentation.Presentation;

import java.awt.*;

/** Command.ShowAboutBox, shows the about box
 * @author Fajar Butt & Jordana Guilbride Capela
 */

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
