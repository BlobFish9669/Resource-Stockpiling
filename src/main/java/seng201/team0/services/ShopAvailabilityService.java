package seng201.team0.services;

import seng201.team0.models.ShopAvailability;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.List;

/**
 * Service class used to store and retrieve the number of items in the shop
 * @author Caleb Cooper
 */
public class ShopAvailabilityService {
    private final ShopAvailability shopAvailability;

    /**
     * Constructor
     */
    public ShopAvailabilityService() {shopAvailability = new ShopAvailability();}

    /**
     * Retrieve the number of shop towers available
     * @return the number of shop towers
     */
    public int getNumberTowersAvailable() { return shopAvailability.getNumberTowersAvailable(); }

    /**
     * Retrieve the number of shop upgrades available
     * @return the number of shop upgrades
     */
    public int getNumberUpgradesAvailable() { return shopAvailability.getNumberUpgradesAvailable(); }

    /**
     * Retrieve the list of towers available in the shop
     * @return the list of towers available
     */
    public List<Tower> getAvailableTowers() { return shopAvailability.getShopTowers(); }

    /**
     * Retrieve the list of upgrades in the shop
     * @return list of available upgrades
     */
    public List<Upgrade> getAvailableUpgrades() { return shopAvailability.getShopUpgrades(); }

    /**
     * Retrieve the list of towers that have or haven't been purchased yet
     * @return purchased tower list
     */
    public List<Boolean> getPurchasedTowers() { return shopAvailability.getPurchasedTowers();}

    /**
     * Retrieve the list of upgrades that have or haven't been purchased yet
     * @return purchased upgrades list
     */
    public List<Boolean> getPurchasedUpgrades() { return shopAvailability.getPurchasedUpgrades(); }

    /**
     * Set the index of a specific tower to be purchased
     * @param input index of the tower to be set as purchased
     */
    public void setTowerPurchased(Integer input) { shopAvailability.setPurchasedTower(input); }

    /**
     * Set the index of a specific upgrade to be purchased
     * @param input index of the upgrade to be set as purchased
     */
    public void setUpgradePurchased(Integer input) { shopAvailability.setPurchasedUpgrade(input); }

    public void resetStore(int round) { shopAvailability.resetStore(round); }

}
