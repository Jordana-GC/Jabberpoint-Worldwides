package org.worldwides.Command.MenuController;

import org.worldwides.Command.Command;
import org.worldwides.Presentation.Presentation;
import org.worldwides.Composite.Slide;
import org.worldwides.Composite.TextItem;
import org.worldwides.Presentation.StyleChooser;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * <p>This is the NewFile Command, it's used in MenuController</p>
 *
 * @author Fajar Butt & Jordana Guilbride Capela
 * @version 0.0 2025/03/00
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
        presentation.setTitle("New Presentation - " + chosenStyle);

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

        presentation.setSlideNumber(0);
        frame.repaint();
    }

    private void createProfessionalStyle() {

        Slide slide = new Slide("Professional Style", 1);
        slide.append(new TextItem(1, "Professionalism is slay"));
        slide.append(new TextItem(3, "Date: " + LocalDate.now()));
        presentation.append(slide);
    }

    private void createCreativeStyle() {

        Slide slide = new Slide("Creative Style", 0);
        slide.append(new TextItem(0, "Let your creativity flow"));
        slide.append(new TextItem(1, "Date: " + LocalDate.now()));
        presentation.append(slide);
    }

    private void createAcademicStyle()
    {
        Slide slide = new Slide("Academic Style", 2);
        slide.append(new TextItem(2, "Be an Academic Weapon!"));
        slide.append(new TextItem(4, "Date: " + LocalDate.now()));
        presentation.append(slide);
    }
}
