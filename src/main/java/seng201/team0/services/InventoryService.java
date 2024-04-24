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
     * Retrieve the current tower ArrayList
     * @return current tower ArrayList
     */
    public ArrayList<Tower> getMainTowerSelection() {return inventory.getMainTowers();}

    public ArrayList<Tower> getReserveTowerSelection() {return inventory.getReserveTowers();}

    public void addToMainTowerSelection(Tower input) {inventory.addMainTower(input);}

    public void addToReserveTowerSelection(Tower input) {inventory.addReserveTower(input);}

    public ArrayList<Upgrade> getUserUpgrades() {return inventory.getUpgrades();}

    public void addUserUpgrade(Upgrade input) {inventory.addUpgrade(input);}

    public void removeMainTower(Tower input) {inventory.removeMainTower(input);}

    public void removeReserveTower(Tower input) {inventory.removeReserveTower(input);}

    public void removeUserUpgrade(Upgrade input) {inventory.removeUpgrade(input);}
}