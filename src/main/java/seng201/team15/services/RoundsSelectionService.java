package seng201.team15.services;
import seng201.team15.models.RoundsSelection;

/**
 * Service class used to store and retrieve the number of rounds that the user decides
 * @author Caleb Cooper
 */
public class RoundsSelectionService {

    private final RoundsSelection roundsSelection;

    /**
     * Constructor
     */
    public RoundsSelectionService() {roundsSelection = new RoundsSelection();}

    /**
     * Set stored rounds number to be the value of the users input
     * @param input value of the users input
     */
    public void setRoundsSelection(Integer input) {roundsSelection.setNumberOfRounds(input);}

    /**
     * Retrieve the current stored number of rounds
     * @return current rounds number
     */
    public Integer getRoundsSelection() {return roundsSelection.getNumberOfRounds();}
}