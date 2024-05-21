package seng201.team15.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team15.services.PlayerScoreService;

import static org.junit.jupiter.api.Assertions.*;

class PlayerScoreServiceTest {
    private PlayerScoreService playerScoreServiceTest;

    /**
     * Initialise a new PlayerScoreService object before each test.
     */
    @BeforeEach
    void setUp() {
        playerScoreServiceTest = new PlayerScoreService();
    }

    /**
     * Test for adding points to the player's score. Checks that the score is updated correctly.
     */
    @Test
    void testScoreSystem() {
        assertEquals(0, playerScoreServiceTest.getPlayerScore());
        playerScoreServiceTest.setPlayerScore(100);
        playerScoreServiceTest.addPlayerScore(200);
        assertEquals(300, playerScoreServiceTest.getPlayerScore());
    }
}
