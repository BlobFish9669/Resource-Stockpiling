package seng201.team15.models;
/**
 * Class used to store and retrieve the user's balance of money
 * @author Caleb Cooper
 */
public class MoneyBalance {
    private Integer money;

    /**
     * Constructor
     */
    public MoneyBalance() { money = 0; }

    /**
     * Get current money
     * @return Current money balance
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * Set money equal to the value of the users input
     * @param input Value of the users input of money
     */
    public void setMoney(Integer input) {
        money = input;
    }
}
