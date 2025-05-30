package org.worldwides.factoryMethod;

import java.awt.*;

/** FactoryMethod.StyleFactory, This is the styleFactory, it allows for the modular creation of styles using the factory method design pattern
 * @author Fajar Butt & Jordana Guilbride Capela
 */

/**
 * Creates style objects based on level.
 * (SRP: Only handles style creation)
 * (OCP: New styles can be added via new case)
 */

public class StyleFactory
{
    //Was changed from interface to concrete class with a static method to allow for integration with composite pattern
    public static Style createStyle(int level)
    {
        return switch (level)
        {
            case 0 -> new Style(0, Color.red, 48, 20);
            case 1 -> new Style(20, Color.blue, 40, 10);
            case 2 -> new Style(50, Color.black, 36, 10);
            case 3 -> new Style(70, Color.black, 30, 10);
            case 4 -> new Style(90, Color.black, 24, 10);
            default -> new Style(90, Color.black, 24, 10); // Default switch is being kept so that it can still be accessed as a level and stay default, even if appears 'redundant'
        };
    }
}
