package org.worldwides.command.KeyController;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class QuitTest
{
    /**
     * This test is disabled because it involves System.exit() which cannot be properly tested in JUnit.
     * The Quit command class contains a System.exit() call that terminates the JVM,
     * making automated test verification impossible.

     * Manual verification steps:
     * 1. Run the application
     * 2. Trigger the quit command
     * 3. Verify the application closes properly
     */

    @Test
    @Disabled("System.exit() in Quit command cannot be tested in JUnit - " +
            "it terminates the JVM. Requires manual verification.")
    void execute_shouldTerminateApplication()
    {
        // Test intentionally left blank - see disabled reason above
    }
}