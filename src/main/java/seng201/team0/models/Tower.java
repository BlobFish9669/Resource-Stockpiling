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
    private int fillRate;
    public Tower() {
        setResourceType("None");
        setResourceAmount(0);
        setReloadSpeed(0);
        setLevel(0);
        setCost(0);
    }
    public Tower(String resourceType, int resourceAmount, double reloadSpeed, int level, int cost) {
        setResourceType(resourceType);
        setResourceAmount(resourceAmount);
        setReloadSpeed(reloadSpeed); // In mins
        setLevel(level);
        setCost(cost);
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
    }
    public int getCost() {
        return cost;
    }

    public void applyUpgrade(Upgrade upgrade) {
        if (Objects.equals(upgrade.getUpgradeType(), "Resource Type")) {
            setResourceType(upgrade.getResourceType());
        } else if (Objects.equals(upgrade.getUpgradeType(), "Tower Level")) {
            setLevel((int) (getLevel() + upgrade.getUpgradeModifier()));
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
