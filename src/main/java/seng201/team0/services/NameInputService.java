package seng201.team0.services;
import seng201.team0.models.NameInput;
/**
 * Service class used to store and retrieve the name value that the user decides
 * Used seng201 teaching team's provided CounterService class as a reference
 * @author Caleb Cooper
 */
public class NameInputService {
    private final NameInput nameInput;
    /**
     * Constructor
     */
    public NameInputService() {
        nameInput = new NameInput();
    }
    /**
     * Set stored name to be the value of the users input
     * @param input value of the users input
     */
    public void setNewName(String input) {
        nameInput.setName(input);
    }
    /**
     * Retrieve the current stored name
     * @return current name
     */
    public String getCurrentName() {
        return nameInput.getName();
    }
}
