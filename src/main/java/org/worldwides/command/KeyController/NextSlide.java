package org.worldwides.command.KeyController;

import org.worldwides.command.Command;
import org.worldwides.presentation.Presentation;
/** Command.NextSlide, goes to the next slide using the command pattern
 * @author Fajar Butt & Jordana Guilbride Capela
 */
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
