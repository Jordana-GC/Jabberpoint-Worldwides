package Command.MenuController;

import Command.Command;
import Presentation.Presentation;

import java.awt.*;

/**
 * <p>This is the NewFile Command, it's used in MenuController</p>
 *
 * @author Fajar Butt & Jordana Guilbride Capela
 * @version 0.0 2025/03/00
 */

public class NewFile extends Command
{
    //do I do getters and setters for frame?
    private Frame frame;

    public NewFile(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
      this.presentation.clear();
      this.frame.repaint();
    }
}
