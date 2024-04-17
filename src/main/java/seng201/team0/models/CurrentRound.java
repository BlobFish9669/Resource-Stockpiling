package seng201.team0.models;
/**
 * Class used to store and retrieve the current round number
 * @author Caleb Cooper
 */
public class CurrentRound {
    private Integer round;
    /**
     * Constructor
     */
    public CurrentRound() { round = 0; }
    /**
     * Get current round
     * @return Current round
     */
    public Integer getRound() {
            return round;
        }

    /**
     * Set round equal to the value of the users input
     * @param input Value of the round the user wants to set
     */
    public void setRound(Integer input) {
            round = input;
        }
}
