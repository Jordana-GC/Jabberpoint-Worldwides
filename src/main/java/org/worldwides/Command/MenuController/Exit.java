package org.worldwides.Command.MenuController;

import org.worldwides.Command.Command;
import org.worldwides.Presentation.Presentation;

/** Command.Exit, exit the program through the menu
 * @author Fajar Butt & Jordana Guilbride Capela
 */

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
