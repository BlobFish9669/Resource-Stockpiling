package seng201.team15.models;

/**
 * Class used to store and add point to the players score in order to track how well they did during the game
 * @author Caleb Cooper
 */
public class PlayerScore {
    private int score;

    /**
     * Constructor
     */
    public PlayerScore() { score = 0; }

    /**
     * Returns the players current score
     * @return stored player score
     */
    public int getScore() { return score; }

    /**
     * Sets the players score to a certain value
     * @param score the intended value to set stored score to
     */
    public void setScore(int score) { this.score = score; }

    /**
     * Adds an amount on top of the previously stored score
     * @param score to be added to stored score
     */
    public void addScore(int score) { this.score += score; }
}