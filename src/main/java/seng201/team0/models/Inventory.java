package seng201.team0.models;
import java.util.ArrayList;
/**
 * Class used to store and retrieve selected towers
 * @author Caleb Cooper
 */
public class Inventory {
    private ArrayList<Tower> towers;
    private ArrayList<Upgrade> upgrades;
    /**
     * Constructor
     */
    public Inventory() {
        towers = new ArrayList<>();
        upgrades = new ArrayList<>();
    }
    /**
     * Get list of towers
     * @return Current towers
     */
    public ArrayList<Tower> getTowers() {return towers;}
    /**
     * Set tower arraylist to an arraylist of desired towers
     * @param input Value of the users desired towers
     */
    public void setTowers(ArrayList<Tower> input) {this.towers = input;}

    public void addTower(Tower input) {this.towers.add(input);}

    public ArrayList<Upgrade> getUpgrades() {return upgrades;}

    public void addUpgrade(Upgrade input) {this.upgrades.add(input);}

}