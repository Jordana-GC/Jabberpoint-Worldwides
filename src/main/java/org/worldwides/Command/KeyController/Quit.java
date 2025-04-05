package org.worldwides.Command.KeyController;

import org.worldwides.Command.Command;
import org.worldwides.Presentation.Presentation;

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
