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
    private ArrayList<Cart> carts = new ArrayList<>();
    private ArrayList<Cart> tempCarts = new ArrayList<>();

    /**
     * Constructor
     */
    public CurrentRound() {
        round = 0;
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
        difficulty = input;
        if (difficulty.equals("Easy")) {
            distance = 100;
            cartsNum = 3;
        }
        else if (difficulty.equals("Medium")) {
            distance = 250;
            cartsNum = 6;
        } else {
            distance = 500;
            cartsNum = 10;
        }
    }

    public Integer getDistance() { return distance; }

    public Integer getNumCarts() { return cartsNum; }

    public void setCarts() {
        //generate carts amount of carts random stats
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            List<String> availableResourceTypes = new ArrayList<>();
            availableResourceTypes.addAll(Arrays.asList("Stone", "Coal", "Silver", "Gold", "Diamond"));

            int randomSize = r.nextInt(5,31);
            int randomResource = r.nextInt(0,5);
            double randomSpeed = r.nextInt(5,51);
            carts.add(new Cart(randomSize, availableResourceTypes.get(randomResource), randomSpeed));
        }
    }

    public ArrayList<Cart> getCarts() {
        if (tempCarts.size() != 0) {
            tempCarts.clear();
        }
        for (int i = 0; i < cartsNum; i++) {
            tempCarts.add(carts.get(i));
        }
        return tempCarts;
    }
}
