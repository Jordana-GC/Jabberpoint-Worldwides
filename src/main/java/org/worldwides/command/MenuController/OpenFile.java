package org.worldwides.command.MenuController;

import org.worldwides.accessor.Accessor;
import org.worldwides.accessor.XMLAccessor;
import org.worldwides.command.Command;
import org.worldwides.presentation.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/** Command.OpenFile, opens the Demo Presentation
 * @author Fajar Butt & Jordana Guilbride Capela
 */

public class OpenFile extends Command
{
    protected static final String TEST_FILE = "test.xml";
    protected static final String LOAD_ERR = "Load Error";
    protected static final String IO_EXC = "IO Exception: ";

    private final Frame frame;

    public OpenFile(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        this.presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();

        try
        {
            xmlAccessor.loadFile(this.presentation, TEST_FILE);
            this.presentation.setSlideNumber(0);
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(this.frame, IO_EXC + exc,
                    LOAD_ERR, JOptionPane.ERROR_MESSAGE);
        }

        this.frame.repaint();
    }
}
