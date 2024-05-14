package seng201.team0.models;
/**
 * Class used to store and retrieve difficulty
 * @author Caleb Cooper
 */
public class DifficultySelection {
    private String difficulty;

    /**
     * Constructor
     */
    public DifficultySelection() {difficulty = "Easy";}

    /**
     * Get current difficulty
     * @return Current difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Set difficulty to a difficulty value
     * @param input Value of the users desired difficulty
     */
    public void setDifficulty(String input) { this.difficulty = input; }
}