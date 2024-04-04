package seng201.team0.models;
/**
 * Class used to prompt user to input name and then store input, used seng201 teaching team's provided Counter class as a reference
 * @author Caleb Cooper
 */
public class NameInput {
    private String name;
    /**
     * Constructor
     */
    public NameInput() {
        name = "";
    }
    /**
     * Get current name
     * @return Current name
     */
    public String getName() {
        return name;
    }
    /**
     * Set name equal to the value of the users input
     * @param input Value of the users input of name
     */
    public void setName(String input) {
        name = input;
    }
}
