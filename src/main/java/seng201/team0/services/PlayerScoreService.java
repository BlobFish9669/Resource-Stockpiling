package seng201.team0.services;

import seng201.team0.models.PlayerScore;

/**
 * Service class used to store the value of the player's score throughout the game
 * @author Quinn Le Lievre, Caleb Cooper
 */
public class PlayerScoreService {

    private final PlayerScore playerScore;

    /**
     * Constructor
     */
    public PlayerScoreService() { playerScore = new PlayerScore(); }

    /**
     * Retrieve the player's current score
     * @return player's score
     */
    public int getPlayerScore() { return playerScore.getScore(); }

    /**
     * Sets the player's score to an integer value
     * @param score the integer to set the score as
     */
    public void setPlayerScore(int score) { playerScore.setScore(score); }

    /**
     * Add value to the player's score
     * @param score the amount to be added
     */
    public void addPlayerScore(int score) { playerScore.addScore(score); }
}
