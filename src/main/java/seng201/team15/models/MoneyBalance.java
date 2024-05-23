package seng201.team15.models;
/**
 * Class used to store and retrieve the user's balance of money
 * @author Caleb Cooper
 */
public class MoneyBalance {
    private Integer money;
    private Integer totalMoneyGained;

    /**
     * Constructor
     */
    public MoneyBalance() {
        money = 0;
        totalMoneyGained = 0;
    }

    /**
     * Get users current money
     * @return Current money balance
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * Adds an inputted amount of money to the users current money balance and adds the same amount to the users
     * total money gained to track how much the user gains throughout the game
     * @param input value of the input of money to add
     */
    public void addMoney(Integer input) {
        money += input;
        totalMoneyGained += input;
    }

    /**
     * Subtracts an inputted amount of money to the users current money balance
     * @param input value of the input of money to subtract
     */
    public void minusBalance(Integer input) {
        money -= input;
    }

    /**
     * Returns how much money the user has earned throughout playing the game
     * @return the value of the users total earnings
     */
    public Integer getTotalMoneyGained() { return totalMoneyGained; }
}
