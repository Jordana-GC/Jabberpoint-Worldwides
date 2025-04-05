package org.worldwides.command.MenuController;

import org.worldwides.command.Command;
import org.worldwides.presentation.Presentation;

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
