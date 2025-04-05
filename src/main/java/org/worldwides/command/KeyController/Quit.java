package org.worldwides.command.KeyController;

import org.worldwides.command.Command;
import org.worldwides.presentation.Presentation;

/** Command.QUit, quits the program
 * @author Fajar Butt & Jordana Guilbride Capela
 */

public class Quit extends Command
{
    public Quit(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        System.exit(0);
    }
}
