package org.worldwides.Command.MenuController;

import org.worldwides.Command.Command;
import org.worldwides.Presentation.AboutBox;
import org.worldwides.Presentation.Presentation;

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
