package seng201.team15.models;

import java.util.Objects;

/**
 * Class for towers that will be used to fill carts with resources.
 * @author Quinn Le Lievre, Caleb Cooper
 */
public class Tower {
    private String resourceType;
    private int resourceAmount;
    private double reloadSpeed;
    private int level;
    private int cost;
    private int sellPrice;
    private int towerPoints;
    private int roundsUsed;
    private boolean isBroken;

    /**
     * Constructor
     * @param resourceType the resource type of the tower
     * @param resourceAmount how many resources the tower will fill the cart with each period
     * @param reloadSpeed the period between filling the cart up with resourceAmount (In mins)
     * @param level the current level of the tower
     * @param cost the cost value associated to the tower
     */
    public Tower(String resourceType, int resourceAmount, double reloadSpeed, int level, int cost) {
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.reloadSpeed = reloadSpeed;
        this.level = level;
        this.cost = cost;
        this.sellPrice = (int) (cost * 0.75); // Get 75% of cost back if sold
        this.towerPoints = 0;
        this.roundsUsed = 0;
        this.isBroken = false;
    }

    /**
     * Trivial setter to set resource type to a provided input
     * @param resourceType the input of what resource type to set the tower to
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Getter to retrieve the resource type of the tower
     * @return the resource type of the tower
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Sets the resource amount that the tower will produce to the provided input
     * @param resourceAmount the amount of resource the tower should produce
     */
    public void setResourceAmount(int resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    /**
     * Returns the amount of resources the tower produces
     * @return the resource amount of the tower
     */
    public int getResourceAmount() {
        return resourceAmount;
    }

    /**
     * Sets the towers reload speed to the provided input while checking that the reload speed doesn't go below 0.1
     * @param reloadSpeed the speed the tower can fill up the carts
     */
    public void setReloadSpeed(double reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
        if (this.reloadSpeed < 0.1) {
            this.reloadSpeed = 0.1;
        }
    }

    /**
     * Reduces the towers reload speed by a certain amount
     * @param reloadSpeed the amount to reduce reload speed by
     */
    public void reduceReloadSpeed(double reloadSpeed) {
        this.reloadSpeed -= reloadSpeed;
        if (this.reloadSpeed < 0.1) {
            this.reloadSpeed = 0.1;
        }

    }

    /**
     * Returns the reload speed the tower has been set to
     * @return the reload speed of the tower
     */
    public double getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Returns the current level of the tower
     * @return tower level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the cost of the tower to an inputted amount and then sets the selling price value to 0.75x the cost
     * @param cost the intended new cost of the tower
     */
    public void setCost(int cost) {
        this.cost = cost;
        this.sellPrice = (int) (cost * 0.75);
    }

    /**
     * Adds to the current cost of the tower and then sets the sell price to 0.75x the new cost
     * @param cost the cost to add to the current cost of the tower
     */
    private void addCost(int cost) {
        this.cost += cost;
        this.sellPrice = (int) (this.cost * 0.75);
    }

    /**
     * Returns the cost that the tower has been set to
     * @return cost of the tower
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the selling price value of the tower - 0.75x the cost
     * @return the selling price the tower has been set to
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Returns the amount of rounds that the tower has been used in the main tower selection
     * @return the total number of round that the tower has been used
     */
    public int getRoundsUsed() {
        return roundsUsed;
    }

    /**
     * Add a round used to the total number of rounds the tower has been used in
     */
    public void addRoundUsed() {
        this.roundsUsed++;
    }

    /**
     * Returns the current tower point of the tower (xp level)
     * @return the number of tower points the tower has
     */
    public int getTowerPoints() {
        return towerPoints;
    }

    /**
     * Adds an inputted value of tower points of the tower and checks to see if the tower needs a level change
     * @param points the number of points to add
     */
    public void gainTowerPoints(int points) {
        this.towerPoints += points;
        checkIfTowerLevelChange();
    }

    /**
     * Checks to see if the tower requires either level ups or level downs and adjusts the towers stats accordingly
     */
    private void checkIfTowerLevelChange() {
        while (towerPoints >= 100 * level) { // Good to go for a level up, can do multiple level ups
            towerPoints -= 100 * level;
            level++;
            // Increase stats
            resourceAmount += 5;
            reduceReloadSpeed(0.25);
            addCost(10);
        }
        while (towerPoints < 0) {
            level--;
            if (level < 1) { // Make sure that 1 is always the lowest level a tower can go
                level = 1;
                towerPoints = 0;
                break;
            }
            // Reverse the stats increase
            towerPoints += 100 * level;
            resourceAmount -= 5;
            reduceReloadSpeed(-0.25);
            addCost(-10);
        }
    }

    /**
     * Applies an inputted upgrade to the tower, adjusting the intended tower stat by a modifier amount
     * @param upgrade the upgrade to apply to the tower
     */
    public void applyUpgrade(Upgrade upgrade) {
        if (Objects.equals(upgrade.getUpgradeType(), "Resource Type")) {
            setResourceType(upgrade.getResourceType());
        } else if (Objects.equals(upgrade.getUpgradeType(), "Tower Points")) {
            gainTowerPoints((upgrade.getUpgradeModifierAmount().intValue()));
        } else if (Objects.equals(upgrade.getUpgradeType(), "Resource Amount")) {
            setResourceAmount((int) (getResourceAmount() * upgrade.getUpgradeModifierAmount()));
        } else if (Objects.equals(upgrade.getUpgradeType(), "Reload Speed")) {
            setReloadSpeed(getReloadSpeed() * upgrade.getUpgradeModifierAmount());
        } else if (Objects.equals(upgrade.getUpgradeType(), "Price")) {
            setCost((int) (getCost() * upgrade.getUpgradeModifierAmount()));
        } else if (Objects.equals(upgrade.getUpgradeType(), "Broken Tower")) {
            fixTower();
        }
    }

    /**
     * Returns the integer rate of how fast the tower will fill up a cart in order to do calculations for whether the cart
     * will be filled up in time before it reaches the end of the track
     * @return the fill rate of the tower
     */
    public int getFillRate() { return (int) (this.resourceAmount/this.reloadSpeed); }

    public void breakTower() { this.isBroken = true; }

    public void fixTower() { this.isBroken = false; }

    public boolean getBrokenStatus() { return isBroken; }
}
