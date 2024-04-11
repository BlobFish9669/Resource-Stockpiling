package seng201.team0.models;
import java.util.ArrayList;
/**
 * Class used to store and retrieve selected towers
 * @author Caleb Cooper
 */
public class TowerSelection {
    private ArrayList<Integer> towers;
    /**
     * Constructor
     */
    public TowerSelection() {towers = new ArrayList<>();}
    /**
     * Get list of towers
     * @return Current towers
     */
    public ArrayList<Integer> getTowers() {return towers;}
    /**
     * Set tower arraylist to an arraylist of desired towers
     * @param input Value of the users desired towers
     */
    public void setTowers(ArrayList<Integer> input) {this.towers = input;}
}