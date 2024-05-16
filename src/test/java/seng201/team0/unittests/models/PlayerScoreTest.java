package seng201.team0.unittests.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import seng201.team0.models.PlayerScore;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerScoreTest {
    private PlayerScore testPlayerScore;

    @BeforeEach
    void setupTest() {
        testPlayerScore = new PlayerScore();
    }
    @Test
    void testAddScore() {
        testPlayerScore.setScore(3);
        assertEquals(3, testPlayerScore.getScore());
        testPlayerScore.addScore(5);
        assertEquals(8, testPlayerScore.getScore());
    }

}
