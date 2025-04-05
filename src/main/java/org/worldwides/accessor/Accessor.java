package org.worldwides.accessor;

import org.worldwides.presentation.Presentation;

import java.io.IOException;

/**
 * <p>Een Accessor.Accessor maakt het mogelijk om gegevens voor een presentatie
 * te lezen of te schrijven.</p>
 * <p>Niet-abstracte subklassen moeten de load en de save methode implementeren.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2025/04/05 Fajar Butt & Jordana Guilbride Capela
 */

/**
 * Abstract base for file access operations.
 * (OCP: Open for extension via new Accessor implementations)
 * (DIP: High-level Presentation depends on this abstraction)
 */

public abstract class Accessor
{
	public Accessor()
	{
	}

	public static Accessor getDemoAccessor()
	{
		return new DemoPresentation(); // Demonstrating DIP
	}

	public abstract void loadFile(Presentation presentation, String fileName) throws IOException;

	public abstract void saveFile(Presentation presentation, String fileName) throws IOException;
}
