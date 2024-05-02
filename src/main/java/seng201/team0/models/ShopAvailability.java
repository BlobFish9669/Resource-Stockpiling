package seng201.team0.models;

import java.util.*;

import seng201.team0.models.towertypes.*;
import seng201.team0.models.upgradetypes.*;

/**
 * Class used to create and store a random number of shop items within given bounds
 * @author Caleb Cooper
 */
public class ShopAvailability {

    private Integer numTowersAvailable;
    private Integer numUpgradesAvailable;

    private List<Tower> potentialShopTowers = new ArrayList<>();
    private List<Tower> shopTowers;
    private List<Upgrade> potentialShopUpgrades = new ArrayList<>();
    private List<Upgrade> shopUpgrades;

    private List<Boolean> purchasedTowers;
    private List<Boolean> purchasedUpgrades;

    /**
     * Constructor to randomise the shop towers and upgrades
     */
    public ShopAvailability() {
        resetStore();
    }


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
    public int getNumberUpgradesAvailable() { return numUpgradesAvailable; }

    /**
     * Method to retrieve the list of available shop towers
     * @return the list of available shop towers
     */
    public List<Tower> getShopTowers() {
        return shopTowers;
    }

    /**
     * Method to retrieve the list of available shop upgrades
     * @return the list of available shop upgrades
     */
    public List<Upgrade> getShopUpgrades() { return shopUpgrades; }

    /**
     * Method to retrieve the currently purchased towers from the shop in order to make them not purchasable again that round
     * @return currently purchased towers
     */
    public List<Boolean> getPurchasedTowers() { return purchasedTowers; }

    /**
     * Method to retrieve the currently purchased upgrades from the shop in order to make them not purchasable again that round
     * @return currently purchased upgrades
     */
    public List<Boolean> getPurchasedUpgrades() { return purchasedUpgrades; }

    /**
     * Method to set a tower to purchased in order to track what is allowed to be purchased from the shop and what isn't
     * @param input of the index of the tower that has been purchased
     */
    public void setPurchasedTower(Integer input) { purchasedTowers.set(input, true); }

    /**
     * Method to set an upgrade to purchased in order to track what is and isn't allowed to be purchased from the shop that round
     * @param input of the index of the upgrade that has been purchased
     */
    public void setPurchasedUpgrade(Integer input) { purchasedUpgrades.set(input, true); }

    public void resetStore() {
        Random r = new Random();
        numTowersAvailable = r.nextInt(3, 6); //randomly generates a number between 3 and 5 indicating how many towers should be available in the shop
        numUpgradesAvailable = r.nextInt(3, 7);

        shopTowers = new ArrayList<>();
        shopUpgrades = new ArrayList<>();

        Collections.addAll(potentialShopTowers, new ShopTower1(), new ShopTower2(), new ShopTower3(), new ShopTower4(), new ShopTower5(), new ShopTower6(), new ShopTower7(), new ShopTower8(), new ShopTower9(), new ShopTower10());
        for (int i = 0; i < numTowersAvailable; i++) {
            int randomShopTower = r.nextInt(0, potentialShopTowers.size());
            while (shopTowers.contains(potentialShopTowers.get(randomShopTower))) {
                randomShopTower = r.nextInt(0, potentialShopTowers.size());
            }
            shopTowers.add(potentialShopTowers.get(randomShopTower));
        }

        Collections.addAll(potentialShopUpgrades, new Upgrade1(), new Upgrade2(), new Upgrade3(), new Upgrade4(), new Upgrade5());
        for (int i = 0; i < numUpgradesAvailable; i++) {
            int randomShopUpgrade = r.nextInt(0, potentialShopUpgrades.size());
            shopUpgrades.add(potentialShopUpgrades.get(randomShopUpgrade));
        }

        purchasedTowers = new ArrayList<>(Arrays.asList(new Boolean[numTowersAvailable]));
        purchasedUpgrades = new ArrayList<>(Arrays.asList(new Boolean[numUpgradesAvailable]));
        Collections.fill(purchasedTowers, false);
        Collections.fill(purchasedUpgrades, false);
    }
}