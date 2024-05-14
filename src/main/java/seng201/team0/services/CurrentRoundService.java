package seng201.team0.services;

import seng201.team0.models.Cart;
import seng201.team0.models.CurrentRound;

import java.util.ArrayList;

/**
 * Service class used to store and retrieve information about the round the user is currently on
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

    /**
     * Set difficulty for the current round
     * @param input the difficult selected
     */
    public void setDifficulty(String input) { currentRound.setDifficulty(input); }

    /**
     * Retrieve difficulty of the current round
     * @return difficulty
     */
    public String getDifficulty() { return currentRound.getDifficulty(); }

    /**
     * Retrieve the distance carts have to travel
     * @return distance
     */
    public Integer getDistance() { return currentRound.getDistance(); }

    /**
     * Retrieve number of carts in the round
     * @return number of carts
     */
    public Integer getNumCarts() { return currentRound.getNumCarts(); }

    /**
     * Sets the carts for the round by executing the setCarts method of currentRound
     */
    public void setCarts() { currentRound.setCarts(); }

    /**
     * Retrieve the list of carts for the round depending on the difficulty selected for the round
     * @return ArrayList of potential carts
     */
    public ArrayList<Cart> getPotentialCarts() { return currentRound.getPotentialCarts(); }

    /**
     * Stores the list of carts for the round
     * @param input ArrayList of the carts for the round
     */
    public void storeCarts(ArrayList<Cart> input) { currentRound.storeCarts(input); }

    /**
     * Retrieves the carts list
     * @return carts for the round
     */
    public ArrayList<Cart> getCarts() { return currentRound.getCarts(); }

    /**
     * Sets game success depending on whether user has won or lost
     */
    public void setGameSuccess() { currentRound.setGameSuccess(); }

    /**
     * Retrieves the game success boolean of whether the user has won the game or not
     * @return boolean for game success
     */
    public boolean getGameSuccess() { return currentRound.getGameSuccess(); }

    /**
     * Sets the total number of rounds for the game
     * @param rounds total rounds
     */
    public void setTotalRounds(int rounds) { currentRound.setTotalRounds(rounds); }

}
