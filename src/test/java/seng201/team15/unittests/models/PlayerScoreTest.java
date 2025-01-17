package seng201.team15.unittests.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import seng201.team15.models.PlayerScore;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerScoreTest {
    private PlayerScore testPlayerScore;

    /**
     * Initialise a PlayerScore object before each test.
     */
    @BeforeEach
    void setupTest() {
        testPlayerScore = new PlayerScore();
    }

    /**
     * Test that ensures adding score updates the player score correctly.
     */
    @Test
    void testAddScore() {
        testPlayerScore.setScore(3);
        assertEquals(3, testPlayerScore.getScore());
        testPlayerScore.addScore(5);
        assertEquals(8, testPlayerScore.getScore());
    }

}
