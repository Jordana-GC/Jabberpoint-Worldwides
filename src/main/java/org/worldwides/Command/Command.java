package org.worldwides.Command;

import org.worldwides.Presentation.Presentation;

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
