package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.RoundsSelectionService;

import static org.junit.jupiter.api.Assertions.*;

class RoundsSelectionServiceTest {
    private RoundsSelectionService roundsSelectionServiceTest;

    /**
     * Initialise a new RoundsSelectionService object before each test.
     */
    @BeforeEach
    void setUp() {
        roundsSelectionServiceTest = new RoundsSelectionService();
    }

    /**
     * The test asserts that the number of rounds selected is initially set to 5, and that the number of rounds selected
     * is updated correctly when it is changed.
     */
    @Test
    void testRoundsSelection() {
        assertNotEquals(0, roundsSelectionServiceTest.getRoundsSelection());
        assertEquals(5, roundsSelectionServiceTest.getRoundsSelection());
        roundsSelectionServiceTest.setRoundsSelection(15);
        assertEquals(15, roundsSelectionServiceTest.getRoundsSelection());
    }

}
