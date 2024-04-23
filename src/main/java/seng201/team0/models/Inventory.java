package seng201.team0.models;
import java.util.ArrayList;
/**
 * Class used to store and retrieve selected towers
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
     * Get list of towers
     * @return Current towers
     */
    public ArrayList<Tower> getMainTowers() {return mainTowers;}

    public ArrayList<Tower> getReserveTowers() {return reserveTowers;}
    /**
     * Set tower arraylist to an arraylist of desired towers
     * @param input Value of the users desired towers
     */
    public void setMainTowers(ArrayList<Tower> input) {this.mainTowers = input;}

    public void addMainTower(Tower input) {this.mainTowers.add(input);}

    public void addReserveTower(Tower input) {this.reserveTowers.add(input);}

    public ArrayList<Upgrade> getUpgrades() {return upgrades;}

    public void addUpgrade(Upgrade input) {this.upgrades.add(input);}

}