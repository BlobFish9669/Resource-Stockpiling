package seng201.team0.models;
/**
 * Class used to store and retrieve number of rounds
 * @author Caleb Cooper
 */
public class RoundsSelection {
    private int numberOfRounds;
    /**
     * Constructor
     */
    public RoundsSelection() {numberOfRounds = 5;}
    /**
     * Get current number of rounds
     * @return Current number of rounds
     */
    public int getNumberOfRounds() {return numberOfRounds;}
    /**
     * Set number of rounds to an inputted integer value
     * @param input Value of the users desired rounds
     */
    public void setNumberOfRounds(int input) {this.numberOfRounds = input;}
}