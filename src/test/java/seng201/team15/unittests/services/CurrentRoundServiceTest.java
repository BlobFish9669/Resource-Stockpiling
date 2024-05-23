package seng201.team15.unittests.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seng201.team15.services.CurrentRoundService;

import static org.junit.jupiter.api.Assertions.*;

class CurrentRoundServiceTest {
    private CurrentRoundService currentRoundServiceTest;

    /**
     * Initialise a CurrentRoundService object before each test.
     */
    @BeforeEach
    void setupRound() {
        currentRoundServiceTest = new CurrentRoundService();
    }

    /**
     * Test to simulate the initialisation of a round.
     * The test goes through each possible difficulty and ensures that the following attributes are assigned
     * appropriate values: number of carts, size of potential carts list, and track distance.
     * The test also checks that when the game is won, or reset, tha this is reflected appropriately in the current round service.
     * When the game is reset, the number of carts should also be reset.
     */
    @Test
    void simulateRound() {
        currentRoundServiceTest.setCarts();
        assertEquals(1, currentRoundServiceTest.getCurrentRound());
        assertNull(currentRoundServiceTest.getCarts());
        currentRoundServiceTest.setTotalRounds(5);
        currentRoundServiceTest.setDifficulty("Easy");
        assertEquals(currentRoundServiceTest.getDifficulty(), "Easy");
        assertEquals(3, currentRoundServiceTest.getNumCarts());
        assertEquals(3, currentRoundServiceTest.getPotentialCarts().size());
        assertEquals(5000, currentRoundServiceTest.getDistance());
        currentRoundServiceTest.setDifficulty("Medium");
        assertEquals(5, currentRoundServiceTest.getNumCarts());
        assertEquals(5, currentRoundServiceTest.getPotentialCarts().size());
        assertEquals(3500, currentRoundServiceTest.getDistance());
        currentRoundServiceTest.setDifficulty("Hard");
        assertTrue(currentRoundServiceTest.getPotentialCarts().size() >= 7 && currentRoundServiceTest.getPotentialCarts().size() <= 10);
        assertEquals(2000, currentRoundServiceTest.getDistance());
        currentRoundServiceTest.storeCarts(currentRoundServiceTest.getPotentialCarts());
        assertNotNull(currentRoundServiceTest.getCarts());
        assertEquals(currentRoundServiceTest.getCarts(), currentRoundServiceTest.getPotentialCarts());
        currentRoundServiceTest.setCurrentRound(5);
        assertEquals(5, currentRoundServiceTest.getCurrentRound());
        assertFalse(currentRoundServiceTest.getGameSuccess());
        currentRoundServiceTest.setGameSuccess();
        assertTrue(currentRoundServiceTest.getGameSuccess());
        currentRoundServiceTest.setDifficulty("reset");
        assertNull(currentRoundServiceTest.getDifficulty());
        assertEquals(0, currentRoundServiceTest.getNumCarts());
    }
}