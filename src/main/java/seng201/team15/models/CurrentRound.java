package seng201.team15.models;

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
     * Get the number of the current round
     * @return Current round number
     */
    public Integer getRound() {
        return round;
    }

    /**
     * Returns the difficulty of the current round
     * @return the stored difficulty
     */
    public String getDifficulty() { return difficulty; }

    /**
     * Returns the distance that the cart has to travel
     * @return the track distance
     */
    public Integer getDistance() { return distance; }

    /**
     * Returns the number of carts
     * @return number of carts
     */
    public Integer getNumCarts() { return cartsNum; }

    /**
     * Returns whether the game is a success or not
     * @return boolean value indicating game success
     */
    public boolean getGameSuccess() { return gameSuccess; }

    /**
     * Returns the list of carts to be displayed to the user
     * @return list of carts
     */
    public ArrayList<Cart> getCarts() { return tempCarts; }

    /**
     * Returns the list of carts - tempCarts based on what the difficulty is currently selected
     * @return list of carts based on difficulty selection
     */
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
            for (int i = 8; i < (3+5+hardCartsNum); i++) {
                tempCarts.add(carts.get(i));
            }
        }

        return tempCarts;
    }


    /**
     * Set round equal to the value of the users input
     * @param input Value of the round the user wants to set
     */
    public void setRound(Integer input) {
        round = input;
    }

    /**
     * Based on the users decided difficulty, which is passed through as a String input, changes how far the carts will travel and how many carts
     * there will be for that difficulty
     * @param input the difficulty the user decides on
     */
    public void setDifficulty(String input) {
        difficulty = input;
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

    /**
     * When called, generates a number of carts which increase with difficulty as the user completes more rounds, and with i - the higher the value of i, the tougher the cart
     * is. The carts are retrieved based on difficulty, harder difficulty will retrieve carts from the lists at higher indexes, leading to tougher carts. Rarer resource types
     * are available further throughout the game proportionate to the total number of rounds the user chooses.
     * Also sets the number of hard carts, so it doesn't change every time the difficulty is changed from the dropdown on the main page
     */
    public void setCarts() {
        Random r = new Random();
        hardCartsNum = r.nextInt(7, 11);

        carts = new ArrayList<>();
        for (int i = 0; i < (3+5+hardCartsNum); i++) { // Generate 19 possible carts as there are 3 easy, 5 medium and up to hardCartsNum (7-10) hard carts
            List<String> availableResourceTypes = getAvailableResourceTypes();
            int randomSize = r.nextInt(5, 10+i + (round/2)); // Harder for later rounds and harder difficulty
            int randomResource = r.nextInt(0, availableResourceTypes.size());
            int randomSpeed = r.nextInt(1, 5 + (i/4)); // Harder for harder difficulty
            carts.add(new Cart(randomSize, availableResourceTypes.get(randomResource), randomSpeed));
        }
    }

    /**
     * Based on what round the user is currently on, sets up the available pool of carts that could be put up against
     * the user
     * @return a list of strings that represent the available resource types
     */
    private List<String> getAvailableResourceTypes() {
        List<String> availableResourceTypes = new ArrayList<>(Arrays.asList("Stone", "Coal", "Copper", "Silver"));
        if (round >= (totalRounds/3)+1) { // 1/3 through the game, add one to ensure that for 5 round game, gold is not available till 2nd round
            availableResourceTypes.add("Gold");
        }
        if (round >= ((totalRounds*2)/3)+2) { // 2/3 through the game, add two to ensure that 5 round game for same reason as above (4th round)
            availableResourceTypes.add("Diamond");
        }
        return availableResourceTypes;
    }

    /**
     * Sets the game success value to true, indicating that the game has been completed successfully
     */
    public void setGameSuccess() { gameSuccess = true; }

    /**
     * Sets the total rounds value to be the chosen number of rounds
     * @param input the value of how many round the user wants to play
     */
    public void setTotalRounds(int input) { totalRounds = input; }

    /**
     * Locks in the current selection of carts, based on the difficulty the user has chosen
     * @param input the current choice of carts to be locked in.
     */
    public void storeCarts(ArrayList<Cart> input) { tempCarts = input; }
}
