package seng201.team0.models;
import java.util.ArrayList;
/**
 * Class used to store and retrieve information about what main towers, reserve towers and upgrades the user has in their inventory
 * @author Caleb Cooper
 */
public class Inventory {
    private ArrayList<Tower> mainTowers;
    private ArrayList<Tower> reserveTowers;
    private ArrayList<Upgrade> upgrades;
    /**
     * Constructor
     */
    public Inventory() {
        mainTowers = new ArrayList<>();
        reserveTowers = new ArrayList<>();
        upgrades = new ArrayList<>();
    }
    /**
     * Method to reset towers and upgrades
     */
    public void resetInventory() {
        mainTowers = new ArrayList<>();
        reserveTowers = new ArrayList<>();
        upgrades = new ArrayList<>();
    }
    /**
     * Get list of main towers
     * @return Current main towers in inventory
     */
    public ArrayList<Tower> getMainTowers() {return mainTowers;}

    /**
     * Method to return a list of reserve towers
     * @return Current reserve towers in inventory
     */
    public ArrayList<Tower> getReserveTowers() {return reserveTowers;}

    /**
     * Method to return a list of upgrades
     * @return current upgrades in inventory
     */
    public ArrayList<Upgrade> getUpgrades() {return upgrades;}

    /**
     * Set tower arraylist to an arraylist of desired towers
     * @param input Value of the users desired towers
     */
    public void setMainTowers(ArrayList<Tower> input) {this.mainTowers = input;}

    /**
     * Add a tower to the users main tower list
     * @param input tower to be added
     */
    public void addMainTower(Tower input) {this.mainTowers.add(input);}

    /**
     * Add a tower to the users reserve tower list
     * @param input tower to be added
     */
    public void addReserveTower(Tower input) {this.reserveTowers.add(input);}

    /**
     * Add an upgrade to the users inventory
     * @param input upgrade to be added
     */
    public void addUpgrade(Upgrade input) {this.upgrades.add(input);}

    /**
     * Remove a tower from the users main tower list
     * @param input tower to be removed
     */
    public void removeMainTower(Tower input) {mainTowers.remove(input);}

    /**
     * Remove a tower from the users reserve tower list
     * @param input tower to be removed
     */
    public void removeReserveTower(Tower input) {reserveTowers.remove(input);}

    /**
     * Remove an upgrade from the users inventory
     * @param input upgrade to be removed
     */
    public void removeUpgrade(Upgrade input) {upgrades.remove(input);}

}