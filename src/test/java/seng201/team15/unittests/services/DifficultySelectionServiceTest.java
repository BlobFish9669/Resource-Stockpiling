package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.DifficultySelectionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifficultySelectionServiceTest {
    private DifficultySelectionService difficultySelectionServiceTest;

    /**
     * Initialise a DifficultlySelectionService object before each test.
     */
    @BeforeEach
    void setupTest() {
        difficultySelectionServiceTest = new DifficultySelectionService();
    }

    /**
     * Test for obtaining the difficultly selected. The test checks that the difficulty is initialised to Easy, and that
     * when the difficultly is updated, this is reflected in the difficulty selection attribute.
     */
    @Test
    void testDifficultySelection() {
        assertEquals("Easy", difficultySelectionServiceTest.getDifficultySelection());
        difficultySelectionServiceTest.setDifficultySelection("Medium");
        assertEquals("Medium", difficultySelectionServiceTest.getDifficultySelection());
    }
}
