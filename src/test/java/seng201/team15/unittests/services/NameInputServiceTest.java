package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.NameInputService;

import static org.junit.jupiter.api.Assertions.*;

class NameInputServiceTest {
    private NameInputService nameInputServiceTest;

    /**
     * Initialise a new NameInputService object before each test.
     */
    @BeforeEach
    void setUp() {
        nameInputServiceTest = new NameInputService();
    }

    /**
     * Test for setting a name. The test checks that the previous name is overwritten by the new name that is set.
     */
    @Test
    void testNameInput() {
        assertEquals("", nameInputServiceTest.getCurrentName());
        nameInputServiceTest.setNewName("Shaggy");
        nameInputServiceTest.setNewName("Scooby");
        assertNotEquals("Shaggy", nameInputServiceTest.getCurrentName());
        assertEquals("Scooby", nameInputServiceTest.getCurrentName());
    }
}
