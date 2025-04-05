package org.worldwides.Command;

import org.worldwides.Presentation.Presentation;

/** Command.Command, This is the command abstract class where all controllers inherit from to be part of the command pattern
 * @author Fajar Butt & Jordana Guilbride Capela
 */

public abstract class Command
{
    protected Presentation presentation;

    public Command(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public abstract void execute();
}
