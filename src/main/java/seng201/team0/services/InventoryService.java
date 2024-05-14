package seng201.team0.services;
import seng201.team0.models.Inventory;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;

/**
 * Service class used to store and retrieve the towers and upgrades in the users inventory
 * @author Caleb Cooper
 */
public class InventoryService {

    private final Inventory inventory;

    /**
     * Constructor
     */
    public InventoryService() {inventory = new Inventory();}

    /**
     * Set towerSelection to be the value of the users desired Towers
     * @param input value of the users input
     */
    public void setMainTowerSelection(ArrayList<Tower> input) {inventory.setMainTowers(input);}

    /**
     * Retrieve the list of current main towers in the users inventory
     * @return current main towers
     */
    public ArrayList<Tower> getMainTowerSelection() {return inventory.getMainTowers();}

    /**
     * Retrieve the list of current reserve towers in the users inventory
     * @return current reserve towers
     */
    public ArrayList<Tower> getReserveTowerSelection() {return inventory.getReserveTowers();}

    /**
     * Add a tower to the main tower list
     * @param input tower to be added
     */
    public void addToMainTowerSelection(Tower input) {inventory.addMainTower(input);}

    /**
     * Add a tower to the reserve tower list
     * @param input tower to be added
     */
    public void addToReserveTowerSelection(Tower input) {inventory.addReserveTower(input);}

    /**
     * Return the list of current upgrades in the users inventory
     * @return current upgrades
     */
    public ArrayList<Upgrade> getUserUpgrades() {return inventory.getUpgrades();}

    /**
     * Add an upgrade to the upgrade list
     * @param input upgrade to be added
     */
    public void addUserUpgrade(Upgrade input) {inventory.addUpgrade(input);}

    /**
     * Remove a certain tower from the main towers in the users inventory
     * @param input tower to be removed
     */
    public void removeMainTower(Tower input) {inventory.removeMainTower(input);}

    /**
     * Remove a certain tower from the reserve towers in the users inventory
     * @param input tower to be removed
     */
    public void removeReserveTower(Tower input) {inventory.removeReserveTower(input);}

    /**
     * Remove a certain upgrade from the upgrades in the users inventory
     * @param input upgrade to be removed
     */
    public void removeUserUpgrade(Upgrade input) {inventory.removeUpgrade(input);}

    /**
     * Reset the users inventory
     */
    public void resetInventory() {
        inventory.resetInventory();
    }
}