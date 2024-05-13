package seng201.team0.models;

/**
 * Class for upgrades that will be used to upgrade towers
 * @author Caleb Cooper
 */
public class Upgrade {
    private String upgradeTitle;
    private String upgradeModifying;
    private Integer cost;
    private Integer sellPrice;
    private Double upgradeModifier;
    private String resourceType;

    /**
     * Constructor for upgrades not altering resource type
     * @param upgradeTitle Display title of the upgrade
     * @param upgradeModifying What the upgrade is modifying
     * @param cost Cost of the upgrade
     * @param upgradeModifier Amount the upgrade affects the tower stat
     */
    public Upgrade(String upgradeTitle ,String upgradeModifying, Integer cost, Double upgradeModifier) {
        this.upgradeTitle = upgradeTitle;
        this.upgradeModifying = upgradeModifying;
        this.cost = cost;
        this.sellPrice = (int) (cost * 0.75);
        this.upgradeModifier = upgradeModifier;
    }

    /**
     * Constructor for upgrades that allows for altering resource type
     * @param upgradeTitle Display title of the upgrade
     * @param resourceType The new resource type
     * @param upgradeModifying What the upgrade is modifying
     * @param cost Cost of the upgrade
     * @param upgradeModifier Amount the upgrade affects the tower stat
     */
    public Upgrade(String upgradeTitle, String upgradeModifying, String resourceType, Integer cost, Double upgradeModifier) {
        this.upgradeTitle = upgradeTitle;
        this.upgradeModifying = upgradeModifying;
        this.cost = cost;
        this.sellPrice = (int) (cost * 0.75);
        this.upgradeModifier = upgradeModifier;
        this.resourceType = resourceType;
    }

    /**
     * Method to return the display title of the upgrade
     * @return upgrade title
     */
    public String getUpgradeTitle() { return upgradeTitle; }

    /**
     * Method to return the tower stat that the upgrade is modifying
     * @return tower stat to be modified
     */
    public String getUpgradeType() { return upgradeModifying; }

    /**
     * Method to return the cost of the upgrade
     * @return cost of the upgrade
     */
    public Integer getCost() { return cost;}

    /**
     * Method to return the amount that the upgrade will alter the tower stat by
     * @return upgrade modifier amount
     */
    public Double getUpgradeModifier() { return upgradeModifier; }

    /**
     * Method to return the new type of resource to change the tower to
     * @return the new resource type
     */
    public String getResourceType() { return resourceType; }

    public int getSellPrice() {
        return sellPrice;
    }

}
