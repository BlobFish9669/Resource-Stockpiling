package seng201.team0.models;

import javax.print.attribute.standard.JobKOctets;
import java.util.Objects;

/**
 * Class for towers that will be used to fill carts with resources.
 * @author Quinn Le Lievre
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

    /* public Tower() { // Constructor not needed if we just create a bunch of them manually?
        setResourceType("None");
        setResourceAmount(0);
        setReloadSpeed(0);
        setLevel(0);
        setCost(0);
    }*/

    public Tower(String resourceType, int resourceAmount, double reloadSpeed, int level, int cost) {
        setResourceType(resourceType);
        setResourceAmount(resourceAmount);
        setReloadSpeed(reloadSpeed); // In mins
        setLevel(level);
        setCost(cost);
        this.sellPrice = (int) (cost * 0.75); // Get 75% of cost back if sold
        this.towerPoints = 0;
        this.roundsUsed = 0;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceAmount(int resourceAmount) {
        this.resourceAmount = resourceAmount;
    }
    public int getResourceAmount() {
        return resourceAmount;
    }
    public void setReloadSpeed(double reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }
    public double getReloadSpeed() {
        return reloadSpeed;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public void setCost(int cost) {
        this.cost = cost;
        this.sellPrice = (int) (cost * 0.75);
    }
    public int getCost() {
        return cost;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getRoundsUsed() {
        return roundsUsed;
    }

    public void addRoundUsed() {
        this.roundsUsed++;
    }

    public void gainTowerPoints(int points) {
        this.towerPoints += points;
        checkIfTowerLevelUp();
    }

    private void checkIfTowerLevelUp() {
        if (towerPoints >= 100 * level) { // Good to go for a level up
            int temp = towerPoints;
            int i = 0;
            while (temp >= 100*level) { //Can do multiple level ups at once now
                temp -= 100*level;
                level += 1;
                i++;
            }
            towerPoints = 0;

            // Increase stats
            resourceAmount += 5*i;
            reloadSpeed -= (double) i /4;
            cost += 10*i;
            sellPrice = (int) (cost * 0.75);
        }
    }

    public void applyUpgrade(Upgrade upgrade) {
        if (Objects.equals(upgrade.getUpgradeType(), "Resource Type")) {
            setResourceType(upgrade.getResourceType());
        } else if (Objects.equals(upgrade.getUpgradeType(), "Tower Points")) {
            gainTowerPoints((upgrade.getUpgradeModifier().intValue()));
        } else if (Objects.equals(upgrade.getUpgradeType(), "Resource Amount")) {
            setResourceAmount((int) (getResourceAmount() * upgrade.getUpgradeModifier()));
        } else if (Objects.equals(upgrade.getUpgradeType(), "Reload Speed")) {
            setReloadSpeed(getReloadSpeed() * upgrade.getUpgradeModifier());
        } else if (Objects.equals(upgrade.getUpgradeType(), "Price")) {
            setCost((int) (getCost() * upgrade.getUpgradeModifier()));
        }
    }

    public int getFillRate() { return (int) (this.resourceAmount/this.reloadSpeed); }

}
