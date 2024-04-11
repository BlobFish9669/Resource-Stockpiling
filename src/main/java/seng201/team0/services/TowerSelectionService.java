package seng201.team0.services;
import seng201.team0.models.TowerSelection;
import java.util.ArrayList;
/**
 * Service class used to store and retrieve the towers that the user decides
 * @author Caleb Cooper
 */
public class TowerSelectionService {
    private final TowerSelection towerSelection;
    /**
     * Constructor
     */
    public TowerSelectionService() {towerSelection = new TowerSelection();}
    /**
     * Set towerSelection to be the value of the users desired Towers
     * @param input value of the users input
     */
    public void setTowerSelection(ArrayList<Integer> input) {towerSelection.setTowers(input);}
    /**
     * Retrieve the current tower ArrayList
     * @return current tower ArrayList
     */
    public ArrayList<Integer> getTowerSelection() {return towerSelection.getTowers();}
}