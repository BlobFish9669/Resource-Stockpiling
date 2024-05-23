package seng201.team15.services;
import seng201.team15.models.MoneyBalance;

/**
 * Service class used to store and retrieve the balance of money the user has
 * @author Caleb Cooper
 */
public class MoneyBalanceService {

    private final MoneyBalance moneyBalance;

    /**
     * Constructor
     */
    public MoneyBalanceService() { moneyBalance = new MoneyBalance(); }

    /**
     * Retrieve the current stored balance
     * @return current balance
     */
    public Integer getCurrentBalance() {
        return moneyBalance.getMoney();
    }

    /**
     * Adds an inputted amount of money to the users current money balance
     * @param input value of the input of money to add
     */
    public void addBalance(Integer input) { moneyBalance.addMoney(input); }

    /**
     * Subtracts an inputted amount of money to the users current money balance
     * @param input value of the input of money to subtract
     */
    public void subtractBalance(Integer input) { moneyBalance.minusMoney(input); }

    /**
     * Returns how much money the user has earned throughout the game
     * @return the users total earnings
     */
    public Integer getTotalEarnings() { return moneyBalance.getTotalMoneyGained(); }
}
