package org.worldwides.Accessor;

import org.worldwides.Composite.BitmapItem;
import org.worldwides.Composite.Slide;
import org.worldwides.Presentation.Presentation;

/**
 * Een ingebouwde demo-presentatie
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2025/04/05 Fajar Butt & Jordana Guilbride Capela
 */

class DemoPresentation extends Accessor
{

    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Demo Presentation");
        Slide slide;
        slide = new Slide("JabberPoint", 1);
        slide.appendTextItem(1, "Het Java Presentatie Tool");
        slide.appendTextItem(2, "Copyright (c) 1996-2000: Ian Darwin");
        slide.appendTextItem(2, "Copyright (c) 2000-now:");
        slide.appendTextItem(2, "Gert Florijn en Sylvia Stuurman");
        slide.appendTextItem(4, "JabberPoint aanroepen zonder bestandsnaam");
        slide.appendTextItem(4, "laat deze presentatie zien");
        slide.appendTextItem(1, "Navigeren:");
        slide.appendTextItem(3, "Volgende slide: PgDn of Enter");
        slide.appendTextItem(3, "Vorige slide: PgUp of up-arrow");
        slide.appendTextItem(3, "Stoppen: q or Q");
        presentation.append(slide);

        slide = new Slide("Demonstratie van levels en stijlen", 1);

        slide.appendTextItem(1, "Level 1");
        slide.appendTextItem(2, "Level 2");
        slide.appendTextItem(1, "Nogmaals level 1");
        slide.appendTextItem(1, "Level 1 heeft stijl nummer 1");
        slide.appendTextItem(2, "Level 2 heeft stijl nummer 2");
        slide.appendTextItem(3, "Zo ziet level 3 er uit");
        slide.appendTextItem(4, "En dit is level 4");
        presentation.append(slide);

        slide = new Slide("De derde slide", 1);
        slide.appendTextItem(1, "Om een nieuwe presentatie te openen,");
        slide.appendTextItem(2, "gebruik File->Open uit het menu.");
        slide.appendTextItem(1, " ");
        slide.appendTextItem(1, "Dit is het einde van de presentatie.");
        slide.append(new BitmapItem(1, "JabberPoint.gif"));
        presentation.append(slide);
    }

    public void saveFile(Presentation presentation, String unusedFilename)
    {
        throw new IllegalStateException("Save As->Demo! aangeroepen");
    }
}
