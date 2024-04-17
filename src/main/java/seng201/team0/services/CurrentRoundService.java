package seng201.team0.services;

import seng201.team0.models.CurrentRound;
/**
 * Service class used to store and retrieve the round the user is currently on
 * @author Caleb Cooper
 */
public class CurrentRoundService {

    private final CurrentRound currentRound;
    /**
     * Constructor
     */
    public CurrentRoundService() { currentRound = new CurrentRound(); }
    /**
     * Set stored round to be a certain value
     * @param input value of the users input
     */
    public void setCurrentRound (Integer input) { currentRound.setRound(input); }
    /**
     * Retrieve the current round number
     * @return current round
     */
    public Integer getCurrentRound() {
        return currentRound.getRound();
    }
}
