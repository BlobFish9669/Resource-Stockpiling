package seng201.team0.unittests.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seng201.team0.models.Cart;
import seng201.team0.models.CurrentRound;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CurrentRoundTest {
    private CurrentRound currentRoundTest;

    @BeforeEach
    void setupRound() {
        currentRoundTest = new CurrentRound();
    }

    @Test
    void simulateRound() {
        assertEquals(1, currentRoundTest.getRound());
        assertNull(currentRoundTest.getCarts());
        currentRoundTest.setTotalRounds(5);
        currentRoundTest.setDifficulty("Easy");
        assertEquals(currentRoundTest.getDifficulty(), "Easy");
        assertEquals(3, currentRoundTest.getNumCarts());
        assertEquals(3, currentRoundTest.getPotentialCarts().size());
        assertEquals(5000, currentRoundTest.getDistance());
        currentRoundTest.setDifficulty("Medium");
        assertEquals(5, currentRoundTest.getNumCarts());
        assertEquals(5, currentRoundTest.getPotentialCarts().size());
        assertEquals(3500, currentRoundTest.getDistance());
        currentRoundTest.setDifficulty("Hard");
        assertTrue(currentRoundTest.getPotentialCarts().size() >= 7 && currentRoundTest.getPotentialCarts().size() <= 10);
        assertEquals(2000, currentRoundTest.getDistance());
        currentRoundTest.storeCarts(currentRoundTest.getPotentialCarts());
        assertNotNull(currentRoundTest.getCarts());
        assertEquals(currentRoundTest.getCarts(), currentRoundTest.getPotentialCarts());
        currentRoundTest.setRound(5);
        assertEquals(5, currentRoundTest.getRound());
        assertFalse(currentRoundTest.getGameSuccess());
        currentRoundTest.setGameSuccess();
        assertTrue(currentRoundTest.getGameSuccess());
        currentRoundTest.setDifficulty("reset");
        assertNull(currentRoundTest.getDifficulty());
        assertEquals(0, currentRoundTest.getNumCarts());
    }
}
