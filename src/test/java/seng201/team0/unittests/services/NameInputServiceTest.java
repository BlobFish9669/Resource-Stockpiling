package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.services.NameInputService;

import static org.junit.jupiter.api.Assertions.*;

class NameInputServiceTest {
    private NameInputService nameInputServiceTest;

    @BeforeEach
    void setUp() {
        nameInputServiceTest = new NameInputService();
    }

    @Test
    void testNameInput() {
        assertEquals("", nameInputServiceTest.getCurrentName());
        nameInputServiceTest.setNewName("Shaggy");
        nameInputServiceTest.setNewName("Scooby");
        assertNotEquals("Shaggy", nameInputServiceTest.getCurrentName());
        assertEquals("Scooby", nameInputServiceTest.getCurrentName());
    }
}
