package org.worldwides.command.MenuController;

import org.worldwides.command.Command;
import org.worldwides.composite.Slide;
import org.worldwides.composite.TextItem;
import org.worldwides.presentation.Presentation;
import org.worldwides.presentation.StyleChooser;

import java.awt.*;
import java.time.LocalDate;

/**
 * <p>This is the NewFile Command, it's used in MenuController</p>
 * @author Fajar Butt & Jordana Guilbride Capela
 */

public class NewFile extends Command
{
    private Frame frame;

    public NewFile(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        String chosenStyle = StyleChooser.showStyleDialog(frame);
        if (chosenStyle == null) return;

        this.presentation.clear();
        this.presentation.setTitle("New Presentation - " + chosenStyle);

        switch (chosenStyle)
        {
            case "Professional (Blue Theme)":
                createProfessionalStyle();
                break;
            case "Creative (Colorful)":
                createCreativeStyle();
                break;
            case "Academic (Formal)":
                createAcademicStyle();
                break;
        }

        this.presentation.setSlideNumber(0);
        this.frame.repaint();
    }

    private void createProfessionalStyle()
    {

        Slide slide = new Slide("Professional Style", 1);
        slide.append(new TextItem(1, "Professionalism is slay"));
        slide.append(new TextItem(3, "Date: " + LocalDate.now()));
        this.presentation.append(slide);
    }

    private void createCreativeStyle()
    {

        Slide slide = new Slide("Creative Style", 0);
        slide.append(new TextItem(0, "Let your creativity flow"));
        slide.append(new TextItem(1, "Date: " + LocalDate.now()));
        this.presentation.append(slide);
    }

    private void createAcademicStyle()
    {
        Slide slide = new Slide("Academic Style", 2);
        slide.append(new TextItem(2, "Be an Academic Weapon!"));
        slide.append(new TextItem(4, "Date: " + LocalDate.now()));
        this.presentation.append(slide);
    }
}
