package seng201.team0.services;

import seng201.team0.models.ShopAvailability;
import seng201.team0.models.Tower;

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

}
