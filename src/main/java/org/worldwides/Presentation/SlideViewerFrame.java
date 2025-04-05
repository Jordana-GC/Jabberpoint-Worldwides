package org.worldwides.Presentation;

import org.worldwides.Command.KeyController.KeyController;
import org.worldwides.Command.MenuController.MenuController;


import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>Het applicatiewindow voor een slideviewcomponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2025/04/05 Fajar Butt & Jordana Guilbride Capela
 */

public class SlideViewerFrame extends JFrame
{
	//should it be caps?
	private static final long serialVersionUID = 3227L;

	private static final String JAB_TITLE = "Jabberpoint 1.6 - OU";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	public SlideViewerFrame(String title, Presentation presentation)
	{
		super(title);
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		presentation.setShowView(slideViewerComponent);
		setupWindow(slideViewerComponent, presentation);
	}

	public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation)
	{
		setTitle(JAB_TITLE);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		getContentPane().add(slideViewerComponent);
		addKeyListener(new KeyController(presentation));
		setMenuBar(new MenuController(this, presentation));
		setSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
	}
}
