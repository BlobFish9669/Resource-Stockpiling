package seng201.team15.models;

import java.util.*;

import seng201.team15.models.towertypes.*;
import seng201.team15.models.upgradetypes.*;

/**
 * Class used to create and store a random number of shop items within given bounds
 * @author Caleb Cooper
 */
public class ShopAvailability {

    private Integer numTowersAvailable;
    private Integer numUpgradesAvailable;
    private int totalRounds;

    private List<Tower> shopTowers;
    private List<Upgrade> shopUpgrades;

    private List<Boolean> purchasedTowers;
    private List<Boolean> purchasedUpgrades;

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

    /**
     * Resets the store when called based on inputted round. The store is proportional to what round the user is currently on, with better items
     * available in the store later on in the game.
     * @param round the round the user is currently on
     */
    public void resetStore(int round) {
        List<Tower> potentialShopTowers = new ArrayList<>();
        List<Upgrade> potentialShopUpgrades = new ArrayList<>();

        Random r = new Random();
        numTowersAvailable = r.nextInt(3, 6); //randomly generates a number between 3 and 5 indicating how many towers should be available in the shop
        numUpgradesAvailable = r.nextInt(3, 7);

        shopTowers = new ArrayList<>();
        shopUpgrades = new ArrayList<>();

        // Change tower availability based on round the user is currently on, better tower later on, variable based on what third of the game the user is on
        Collections.addAll(potentialShopTowers, new ShopTower1(), new ShopTower2(), new ShopTower3(), new ShopTower4(), new ShopTower5());
        if (round > totalRounds/3+1) {
            Collections.addAll(potentialShopTowers, new ShopTower6(), new ShopTower7(), new ShopTower8(), new ShopTower9(), new ShopTower10(), new ShopTower11());
        }
        if (round > (totalRounds*2)/3+1) {
            Collections.addAll(potentialShopTowers, new ShopTower12());
        }

        for (int i = 0; i < numTowersAvailable; i++) {
            int randomShopTower = r.nextInt(0, potentialShopTowers.size());
            while (shopTowers.contains(potentialShopTowers.get(randomShopTower))) {
                randomShopTower = r.nextInt(0, potentialShopTowers.size());
            }
            shopTowers.add(potentialShopTowers.get(randomShopTower));
        }

        // Change upgrade availability based on round the user is currently on, better upgrades later on
        Collections.addAll(potentialShopUpgrades, new Upgrade6(), new Upgrade7(), new Upgrade8(), new Upgrade9(), new Upgrade10(), new Upgrade12()); // worse upgrades to start with - also cheaper
        if (round > totalRounds/2) {
            Collections.addAll(potentialShopUpgrades, new Upgrade1(), new Upgrade2(), new Upgrade3(), new Upgrade4(), new Upgrade5(), new Upgrade11());
        }


        for (int i = 0; i < numUpgradesAvailable; i++) {
            int randomShopUpgrade = r.nextInt(0, potentialShopUpgrades.size());
            shopUpgrades.add(potentialShopUpgrades.get(randomShopUpgrade));
        }

        purchasedTowers = new ArrayList<>(Arrays.asList(new Boolean[numTowersAvailable]));
        purchasedUpgrades = new ArrayList<>(Arrays.asList(new Boolean[numUpgradesAvailable]));
        Collections.fill(purchasedTowers, false);
        Collections.fill(purchasedUpgrades, false);
    }

    /**
     * Sets the total number of round to the value that the user chooses to play
     * @param input number of round the user decides to play
     */
    public void setTotalRounds(int input) { totalRounds = input; }
}