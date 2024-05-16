package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seng201.team0.services.CurrentRoundService;

import static org.junit.jupiter.api.Assertions.*;

class CurrentRoundServiceTest {
    private CurrentRoundService currentRoundServiceTest;

    @BeforeEach
    void setupRound() {
        currentRoundServiceTest = new CurrentRoundService();
    }

    @Test
    void simulateRound() {
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