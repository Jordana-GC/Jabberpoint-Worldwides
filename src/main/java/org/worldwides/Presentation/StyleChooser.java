package org.worldwides.Presentation;

import javax.swing.*;
import java.awt.*;

public class StyleChooser
{
    public static String showStyleDialog(Frame frame)
    {
        String[] styles = {
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
