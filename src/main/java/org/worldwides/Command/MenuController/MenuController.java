package org.worldwides.Command.MenuController;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.worldwides.Command.Command;
import org.worldwides.Command.KeyController.NextSlide;
import org.worldwides.Command.KeyController.PreviousSlide;
import org.worldwides.Presentation.Presentation;

/**
 * <p>The controller for the Menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class MenuController extends MenuBar
{
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	private Frame frame;
	private Presentation presentation;
	private Menu fileMenu;
	private Menu viewMenu;
	private Menu helpMenu;

	//getters and setters for frame and presentation?
	public MenuController(Frame frame, Presentation presentation)
	{
		this.frame = frame;
		this.presentation = presentation;
		this.fileMenu = new Menu(FILE);
		this.viewMenu = new Menu(VIEW);
		this.helpMenu = new Menu(HELP);

		addMenuItem(fileMenu, OPEN, new OpenFile(presentation, frame));
		addMenuItem(fileMenu, NEW, new NewFile(presentation, frame));
		addMenuItem(fileMenu, SAVE, new SaveFile(presentation, frame));
		fileMenu.addSeparator();
		addMenuItem(fileMenu, EXIT, new Exit(presentation));

		addMenuItem(viewMenu, NEXT, new NextSlide(presentation));
		addMenuItem(viewMenu, PREV, new PreviousSlide(presentation));
		addMenuItem(viewMenu, GOTO, new GoToSpecificSlide(presentation));

		addMenuItem(helpMenu, ABOUT, new ShowAboutBox(presentation, frame));

		add(fileMenu);
		add(viewMenu);
		setHelpMenu(helpMenu);
	}

	public void addMenuItem(Menu menu, String name, Command command)
	{
		MenuItem menuItem = createMenuItem(name);

		menuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				command.execute();
			}
		});

		menu.add(menuItem);
	}

	public MenuItem createMenuItem(String name)
	{
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
