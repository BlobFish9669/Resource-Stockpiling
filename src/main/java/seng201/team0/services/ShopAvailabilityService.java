package seng201.team0.services;

import seng201.team0.models.ShopAvailability;

public class ShopAvailabilityService {
    private final ShopAvailability shopAvailability;
    /**
     * Constructor
     */
    public ShopAvailabilityService() {shopAvailability = new ShopAvailability();}

    public int getTowersAvailable() { return shopAvailability.getTowersAvailable(); }

    public int getUpgradesAvailable() { return shopAvailability.getUpgradesAvailable(); }

}
