package seng201.team0.services;
import seng201.team0.models.DifficultySelection;

/**
 * Service class used to store and retrieve the difficulty that the user decides
 * @author Caleb Cooper
 */
public class DifficultySelectionService {

    private final DifficultySelection difficultySelection;

    /**
     * Constructor
     */
    public DifficultySelectionService() {difficultySelection = new DifficultySelection();}

    /**
     * Set difficulty to be the value of the users input
     * @param input value of the users input
     */
    public void setDifficultySelection(String input) {difficultySelection.setDifficulty(input);}

    /**
     * Retrieve the current difficulty
     * @return current difficulty
     */
    public String getDifficultySelection() {return difficultySelection.getDifficulty();}
}