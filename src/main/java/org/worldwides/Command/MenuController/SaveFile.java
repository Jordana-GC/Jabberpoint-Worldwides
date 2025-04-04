package org.worldwides.Command.MenuController;

import org.worldwides.Accessor.Accessor;
import org.worldwides.Accessor.XMLAccessor;
import org.worldwides.Command.Command;
import org.worldwides.Presentation.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SaveFile extends Command
{
    protected static final String SAVE_FILE = "dump.xml";
    protected static final String IO_EXC = "IO Exception: ";
    protected static final String SAVE_ERROR = "Save Error";

    private final Frame frame;

    public SaveFile(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        Accessor xmlAccessor = new XMLAccessor();

        try
        {
            xmlAccessor.saveFile(this.presentation, SAVE_FILE);
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(this.frame, IO_EXC + exc,
                    SAVE_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}
