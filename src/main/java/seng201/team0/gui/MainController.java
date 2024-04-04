package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
//import seng201.team0.services.CounterService;
import seng201.team0.services.NameInputService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for the main.fxml window
 * @author seng201 teaching team, expanded upon by Caleb Cooper
 */
public class MainController {
    //New Testing
    @FXML
    public Button nameSubmitButton;
    @FXML
    public Label nameInputNoteLabel;
    @FXML
    public Label nameInstructionLabel;
    @FXML
    public Label gameTitle;
    @FXML
    public Label defaultLabel;
    @FXML
    private TextArea nameInput;
    @FXML
    private Label nameDisplay;

    private NameInputService nameInputService;
    /**
     * Initialize the window
     *
     * @param stage Top level container for this window
     */
    public void init(Stage stage) {
        nameInputService = new NameInputService();
    }


    /**
     * Method to call when the name submit button is clicked
     *
     */
    @FXML
    private void onButtonClicked() {
        System.out.println("Button has been clicked");
        /*
        Used code found on https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character to figure
        out how to find if a string contained special characters
        */
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(nameInput.getText());

        if(nameInput.getText().length() < 3 || nameInput.getText().length() > 15 || m.find()) {
            //If matcher finds a character not in a-z, A-Z or 0-9, or length is not between 3 and 15, run error
            //nameInputService.setNewName(nameInput.getText());
            System.out.println("ERROR");
            nameDisplay.setText("Error");
            nameInput.setText("");
        } else {
            nameInputService.setNewName(nameInput.getText());
            nameDisplay.setText(nameInput.getText());
            nameInput.setText("");
            System.out.println("Name is: " + nameInputService.getCurrentName());
        }
    }
}
