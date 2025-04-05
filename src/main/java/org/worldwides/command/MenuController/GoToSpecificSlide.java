package org.worldwides.command.MenuController;

import org.worldwides.command.Command;
import org.worldwides.presentation.Presentation;

import javax.swing.*;

/** Command.GoToSpecificSlide, changes to a specific slide through the menu
 * @author Fajar Butt & Jordana Guilbride Capela
 */

public class GoToSpecificSlide extends Command
{
    protected static final String PAGE_NR = "Page number?";
    private static final String INVALID_INPUT = "Please enter a valid number";
    private static final String OUT_OF_RANGE = "Slide number doesn't exist. Please enter a number between 1 and %d";

    public GoToSpecificSlide(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute()
    {
        try
        {
            String pageNumberStr = JOptionPane.showInputDialog(PAGE_NR);

            if (pageNumberStr == null)
            {
                return;
            }

            int pageNumber = Integer.parseInt(pageNumberStr);
            int slideCount = presentation.getSize();

            if (pageNumber < 1 || pageNumber > slideCount)
            {
                String message = String.format(OUT_OF_RANGE, slideCount);
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                presentation.setSlideNumber(pageNumber - 1);
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, INVALID_INPUT, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
