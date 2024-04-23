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
    public void setTowerSelection(ArrayList<Tower> input) {inventory.setTowers(input);}
    /**
     * Retrieve the current tower ArrayList
     * @return current tower ArrayList
     */
    public ArrayList<Tower> getTowerSelection() {return inventory.getTowers();}

    public void addToTowerSelection(Tower input) {inventory.addTower(input);}

    public ArrayList<Upgrade> getUserUpgrades() {return inventory.getUpgrades();}

    public void addUserUpgrade(Upgrade input) {inventory.addUpgrade(input);}
}