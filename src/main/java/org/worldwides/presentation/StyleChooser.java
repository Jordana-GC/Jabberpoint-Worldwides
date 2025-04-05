package org.worldwides.presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Presentation.StyleChooser, This is the styleChooser, it allows users to pick a style type when creating a new Presentation
 *
 * @author Fajar Butt & Jordana Guilbride Capela
 */

public class StyleChooser
{
    public static String showStyleDialog(Frame frame)
    {
        String[] styles =
                {
                        "Professional (Blue Theme)",
                        "Creative (Colorful)",
                        "Academic (Formal)"
                };

        return (String) JOptionPane.showInputDialog(
                frame,
                "Choose a presentation style:",
                "New Presentation Style",
                JOptionPane.QUESTION_MESSAGE,
                null,
                styles,
                styles[0]
        );
    }
}
