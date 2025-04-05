package org.worldwides.command.KeyController;

import org.worldwides.command.Command;
import org.worldwides.presentation.Presentation;

/** Command.PreviousSlide, goes to the previous slide using the command pattern
 * @author Fajar Butt & Jordana Guilbride Capela
 */

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
