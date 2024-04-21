package seng201.team0.models;

public class Upgrade {
    private String upgradeType;
    private Integer cost;

    public Upgrade() {
        setUpgradeType("None");
        setCost(0);
    }
    public Upgrade(String upgradeType, int cost) {
        setUpgradeType(upgradeType);
        setCost(cost);
    }

    public void setUpgradeType(String upgradeTypeInput) {
        upgradeType = upgradeTypeInput;
    }
    public void setCost(Integer costInput) {
        cost = costInput;
    }
    public String getUpgradeType() {
        return upgradeType;
    }

    public Integer getCost() {
        return cost;
    }

}
