package org.worldwides.Command.MenuController;

import org.worldwides.Accessor.XMLAccessor;
import org.worldwides.Command.Command;
import org.worldwides.Presentation.Presentation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SaveFile extends Command
{
    protected static final String SAVE_FILE = "dump.xml";
    protected static final String IO_EXC = "IO Exception: ";
    protected static final String SAVE_ERROR = "Save Error";

    private final Frame frame;
    private String currentPath;

    public SaveFile(Presentation presentation, Frame frame)
    {
        super(presentation);
        this.frame = frame;
    }

    @Override
    public void execute()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Presentation");
        chooser.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));

        String defaultName = presentation.getTitle() != null ?
                presentation.getTitle() + ".xml" : "presentation.xml";
        chooser.setSelectedFile(new File(defaultName));

        if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();

            if (!path.toLowerCase().endsWith(".xml"))
            {
                path += ".xml";
                file = new File(path);
            }

            try
            {
                new XMLAccessor().saveFile(presentation, path);
                this.currentPath = path;

                JOptionPane.showMessageDialog(frame,
                        "Saved successfully to:\n" + path,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e)
            {
                JOptionPane.showMessageDialog(frame,
                        "Failed to save:\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getCurrentPath()
    {
        return this.currentPath;
    }
}
