package seng201.team0.services;

import seng201.team0.models.Cart;
import seng201.team0.models.CurrentRound;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public void setDifficulty(String input) { currentRound.setDifficulty(input); }

    public Integer getDistance() { return currentRound.getDistance(); }

    public Integer getNumCarts() { return currentRound.getNumCarts(); }

    public void setCarts() { currentRound.setCarts(); }

    public ArrayList<Cart> getPotentialCarts() { return currentRound.getPotentialCarts(); }

    public String getDifficulty() { return currentRound.getDifficulty(); }

    public void storeCarts(ArrayList<Cart> input) { currentRound.storeCarts(input); }

    public ArrayList<Cart> getCarts() { return currentRound.getCarts(); }

}
