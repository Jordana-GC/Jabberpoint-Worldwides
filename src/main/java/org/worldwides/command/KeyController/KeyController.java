package org.worldwides.command.KeyController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import org.worldwides.command.Command;
import org.worldwides.presentation.Presentation;

/**
 * <p>This is the Command.KeyController.KeyController (KeyListener)</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2025/04/05 Fajar Butt & Jordana Guilbride Capela
 */

public class KeyController extends KeyAdapter
{
    private final Presentation presentation;

    public KeyController(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        Command operation;
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                operation = new NextSlide(this.presentation);
                operation.execute();
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                operation = new PreviousSlide(this.presentation);
                operation.execute();
                break;
            case 'q':
            case 'Q':
                operation = new Quit(this.presentation);
                operation.execute();
                break;
            default:
                break;
        }
    }
}
