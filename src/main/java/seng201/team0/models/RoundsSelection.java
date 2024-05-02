package seng201.team0.models;
/**
 * Class used to store and retrieve number of rounds
 * @author Caleb Cooper
 */
public class RoundsSelection {
    private Integer numberOfRounds;
    /**
     * Constructor
     */
    public RoundsSelection() {numberOfRounds = 5;}
    /**
     * Get current number of rounds
     * @return Current number of rounds
     */
    public Integer getNumberOfRounds() {return numberOfRounds;}
    /**
     * Set number of rounds to an inputted integer value
     * @param input Value of the users desired rounds
     */
    public void setNumberOfRounds(Integer input) {this.numberOfRounds = input;}
}