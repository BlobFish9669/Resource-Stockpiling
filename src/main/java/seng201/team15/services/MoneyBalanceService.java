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
     * Set stored balance to be the value of the users input
     * @param input value of the users input
     */
    public void setNewBalance (Integer input) { moneyBalance.setMoney(input); }

    /**
     * Retrieve the current stored balance
     * @return current balance
     */
    public Integer getCurrentBalance() {
        return moneyBalance.getMoney();
    }
}
