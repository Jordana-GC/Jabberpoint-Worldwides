package org.worldwides.Command.KeyController;

import org.worldwides.Command.Command;
import org.worldwides.Presentation.Presentation;
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
