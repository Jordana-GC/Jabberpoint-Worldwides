package org.worldwides.Command;

import org.worldwides.Presentation.Presentation;

/** Command.Command, This is the command abstract class where all controllers inherit from to be part of the command pattern
 * @author Fajar Butt & Jordana Guilbride Capela
 */

/**
 * Encapsulates presentation commands.
 * (OCP: New commands can be added without modifying existing code)
 * (DIP: MenuController depends on Command abstraction)
 * (LSP: All subclasses are substitutable for Command)
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
