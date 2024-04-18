package seng201.team0.models;

import java.util.Random;

public class ShopAvailability {
    Random r = new Random();
    private int towersAvailable = r.nextInt(3,6); //randomly generates a number between 3 and 5 indicating how many towers should be available in the shop
    private int upgradesAvailable = r.nextInt(3,7);

    public int getTowersAvailable() {
        return towersAvailable;
    }

    public int getUpgradesAvailable() {
        return upgradesAvailable;
    }
}
