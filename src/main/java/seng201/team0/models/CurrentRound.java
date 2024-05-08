package seng201.team0.models;

import java.util.*;

/**
 * Class used to store and retrieve the current round number
 * @author Caleb Cooper
 */
public class CurrentRound {
    private Integer round;
    private String difficulty;
    private Integer distance;
    private Integer cartsNum = 0;
    private Integer hardCartsNum = 0;
    private ArrayList<Cart> carts;
    private ArrayList<Cart> tempCarts;
    private boolean gameSuccess = false;
    private int totalRounds;

    /**
     * Constructor
     */
    public CurrentRound() {
        round = 1;
        setCarts();
    }

    /**
     * Get current round
     *
     * @return Current round
     */
    public Integer getRound() {
        return round;
    }

    /**
     * Set round equal to the value of the users input
     *
     * @param input Value of the round the user wants to set
     */
    public void setRound(Integer input) {
        round = input;
    }

    public void setDifficulty(String input) {
        Random r = new Random();
        difficulty = input;
        hardCartsNum = r.nextInt(7, 11);
        if (difficulty.equals("Easy")) {
            distance = 5000;
            cartsNum = 3;
        } else if (difficulty.equals("Medium")) {
            distance = 3500;
            cartsNum = 5;
        } else if (difficulty.equals("Hard")) {
            distance = 2000;
            cartsNum = hardCartsNum;
        } else if (difficulty.equals("reset")) {
            difficulty = null;
            cartsNum = 0;
        }
    }

    public Integer getDistance() { return distance; }

    public Integer getNumCarts() { return cartsNum; }

    public void setCarts() {
        carts = new ArrayList<>();
        //generate carts amount of carts random stats
        for (int i = 0; i < (3+5+11)-1; i++) {
            Random r = new Random();
            List<String> availableResourceTypes = new ArrayList<>();
            availableResourceTypes.addAll(Arrays.asList("Stone", "Coal", "Copper", "Silver"));
            if (round >= (totalRounds/3)+1) { // 1/3 through the game, add one to ensure that for 5 round game, gold is not available till 2nd round
                availableResourceTypes.add("Gold");
            }
            if (round >= ((totalRounds*2)/3)+2) { // 2/3 through the game, add two to ensure that 5 round game for same reason as above (4th round)
                availableResourceTypes.add("Diamond");
            }
            int randomSize = r.nextInt(5, 10+i + (round/2)); // Harder for later rounds and harder difficulty
            int randomResource = r.nextInt(0, availableResourceTypes.size());
            int randomSpeed = r.nextInt(1, 5 + (i/4)); // Harder for harder difficulty
            carts.add(new Cart(randomSize, availableResourceTypes.get(randomResource), randomSpeed));
        }
    }

    public ArrayList<Cart> getPotentialCarts() {
        tempCarts = new ArrayList<>();
        //Do this in order to have 3 different lists of potential carts
        if (Objects.equals(difficulty, "Easy")) {
            for (int i = 0; i < 3; i++) {
                tempCarts.add(carts.get(i));
            }
        } else if (Objects.equals(difficulty, "Medium")) {
            for (int i = 3; i < 8; i++) {
                tempCarts.add(carts.get(i));
            }
        } else if (Objects.equals(difficulty, "Hard")) {
            for (int i = 8; i < (3+5+hardCartsNum)-1; i++) {
                tempCarts.add(carts.get(i));
            }
        }

        return tempCarts;
    }

    public String getDifficulty() { return difficulty; }

    public void storeCarts(ArrayList<Cart> input) { tempCarts = input; }

    public ArrayList<Cart> getCarts() { return tempCarts; }

    public void setGameSuccess(boolean input) { gameSuccess = input; }

    public boolean getGameSuccess() { return gameSuccess; }

    public void setTotalRounds(int input) { totalRounds = input; }
}
