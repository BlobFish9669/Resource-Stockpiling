package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.services.RoundsSelectionService;

import static org.junit.jupiter.api.Assertions.*;

class RoundsSelectionServiceTest {
    private RoundsSelectionService roundsSelectionServiceTest;

    @BeforeEach
    void setUp() {
        roundsSelectionServiceTest = new RoundsSelectionService();
    }

    @Test
    void testRoundsSelection() {
        assertNotEquals(0, roundsSelectionServiceTest.getRoundsSelection());
        assertEquals(5, roundsSelectionServiceTest.getRoundsSelection());
        roundsSelectionServiceTest.setRoundsSelection(15);
        assertEquals(15, roundsSelectionServiceTest.getRoundsSelection());
    }

}
