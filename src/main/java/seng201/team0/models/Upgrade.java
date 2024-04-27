package seng201.team0.models;

public class Upgrade {
    private String upgradeTitle;
    private String upgradeModifying;
    private Integer cost;
    private Double upgradeModifier;
    private String resourceType;

    public Upgrade(String upgradeTitle ,String upgradeModifying, Integer cost, Double upgradeModifier) {
        this.upgradeTitle = upgradeTitle;
        this.upgradeModifying = upgradeModifying;
        this.cost = cost;
        this.upgradeModifier = upgradeModifier;
    }
    public Upgrade(String upgradeTitle, String upgradeModifying, String resourceType, Integer cost, Double upgradeModifier) {
        this.upgradeTitle = upgradeTitle;
        this.upgradeModifying = upgradeModifying;
        this.cost = cost;
        this.upgradeModifier = upgradeModifier;
        this.resourceType = resourceType;
    }

    public String getUpgradeTitle() { return upgradeTitle; }
    public String getUpgradeType() {
        return upgradeModifying;
    }
    public Integer getCost() {
        return cost;
    }
    public Double getUpgradeModifier() { return upgradeModifier; }
    public String getResourceType() { return resourceType; }

}
