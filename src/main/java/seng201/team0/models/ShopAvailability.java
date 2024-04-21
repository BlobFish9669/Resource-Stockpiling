package seng201.team0.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import seng201.team0.models.Tower;
import seng201.team0.models.towertypes.*;
import seng201.team0.models.upgradetypes.*;

/**
 * Class used to create and store a random number of shop items within given bounds
 * @author Caleb Cooper
 */
public class ShopAvailability {
    Random r = new Random();
    private int numTowersAvailable = r.nextInt(3, 6); //randomly generates a number between 3 and 5 indicating how many towers should be available in the shop
    private int numUpgradesAvailable = r.nextInt(3, 7);

    private List<Tower> potentialShopTowers = new ArrayList<>();
    private List<Tower> tempShopTowers = new ArrayList<>();
    private List<Upgrade> potentialShopUpgrades = new ArrayList<>();
    private List<Upgrade> tempShopUpgrades = new ArrayList<>();
    private List<Tower> shopTowers = setAvailableTowers();
    private List<Upgrade> shopUpgrades = setAvailableUpgrades();


    /**
     * Retrieve the randomised number between 3 and 5 inclusive to see how many towers are available in the shop
     * @return the number of towers available
     */
    public int getNumberTowersAvailable() {
        return numTowersAvailable;
    }

    /**
     * Retrieve the randomised number between 3 and 6 inclusive to see how many upgrades are available in the shop
     * @return the number of upgrades available
     */
    public int getNumberUpgradesAvailable() {
        return numUpgradesAvailable;
    }

    /**
     * Chooses a set number of unique towers from a provided selection
     * @return a list of available towers for the shop
     */
    private List<Tower> setAvailableTowers() {
        Collections.addAll(potentialShopTowers, new ShopTower1(), new ShopTower2(), new ShopTower3(), new ShopTower4(), new ShopTower5(), new ShopTower6(), new ShopTower7(), new ShopTower8(), new ShopTower9(), new ShopTower10());
        for (int i = 0; i < numTowersAvailable; i++) {
            Random r = new Random();
            int randomShopTower = r.nextInt(0, potentialShopTowers.size());
            while (tempShopTowers.contains(potentialShopTowers.get(randomShopTower))) {
                randomShopTower = r.nextInt(0, potentialShopTowers.size());
            }
            tempShopTowers.add(potentialShopTowers.get(randomShopTower));
        }
        return tempShopTowers;
    }

    public List<Upgrade> setAvailableUpgrades() {
        Collections.addAll(potentialShopUpgrades, new Upgrade1(), new Upgrade2(), new Upgrade3(), new Upgrade4());
        for (int i = 0; i < numUpgradesAvailable; i++) {
            Random r = new Random();
            int randomShopUpgrade = r.nextInt(0, potentialShopUpgrades.size());
            tempShopUpgrades.add(potentialShopUpgrades.get(randomShopUpgrade));
        }
        return tempShopUpgrades;
    }

    /**
     * Method to retrieve the list of available shop towers
     * @return the list of available shop towers
     */
    public List<Tower> getShopTowers() {
        return shopTowers;
    }

    public List<Upgrade> getShopUpgrades() {
        return shopUpgrades;
    }
}