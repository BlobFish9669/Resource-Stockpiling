package seng201.team0.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class used to store and retrieve the current round number
 * @author Caleb Cooper
 */
public class CurrentRound {
    private Integer round;
    private String difficulty;
    private Integer distance;
    private Integer cartsNum;
    private ArrayList<Cart> carts;
    private ArrayList<Cart> tempCarts;
    private boolean gameSuccess = false;

    /**
     * Constructor
     */
    public CurrentRound() {
        round = 1;
        setCarts();
    }
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
    public void setRound(Integer input) { round = input; }

    public void setDifficulty(String input) {
        Random r = new Random();
        difficulty = input;
        if (difficulty.equals("Easy")) {
            distance = 5000;
            cartsNum = 3;
        } else if (difficulty.equals("Medium")) {
            distance = 2500;
            cartsNum = r.nextInt(4,7);
        } else if (difficulty.equals("Hard")) {
            distance = 1000;
            cartsNum = r.nextInt(7,11);
        } else if (difficulty.equals("reset")) {
            difficulty = null;
        }
    }

    public Integer getDistance() { return distance; }

    public Integer getNumCarts() { return cartsNum; }

    public void setCarts() {
        carts = new ArrayList<>();
        //generate carts amount of carts random stats
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            List<String> availableResourceTypes = new ArrayList<>();
            availableResourceTypes.addAll(Arrays.asList("Stone", "Coal", "Copper"));
            if (round >= 3) {
                availableResourceTypes.add("Silver");
            }
            if (round >= 4) {
                availableResourceTypes.add("Gold");
            }
            if (round >= 5) {
                availableResourceTypes.add("Diamond");
            }

            int randomSize = r.nextInt(5,31);
            int randomResource = r.nextInt(0, availableResourceTypes.size());
            int randomSpeed = r.nextInt(1,11);
            carts.add(new Cart(randomSize, availableResourceTypes.get(randomResource), randomSpeed));
        }
    }

    public ArrayList<Cart> getPotentialCarts() {
        tempCarts = new ArrayList<>();
        for (int i = 0; i < cartsNum; i++) {
            tempCarts.add(carts.get(i));
        }
        return tempCarts;
    }

    public String getDifficulty() { return difficulty; }

    public void storeCarts(ArrayList<Cart> input) { tempCarts = input; }

    public ArrayList<Cart> getCarts() { return tempCarts; }

    public void setGameSuccess(boolean input) { gameSuccess = input; }

    public boolean getGameSuccess() { return gameSuccess; }
}
