package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
//import seng201.team0.services.CounterService;
import seng201.team0.services.NameInputService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for the main.fxml window
 * @author seng201 teaching team, expanded upon by Caleb Cooper
 */
public class MainController {
    //New Testing
    @FXML
    public Label gameTitle;
    public Label nameInputLabel;
    public TextField nameInput;
    public Label nameInputNoteLabel;
    public Label roundsLabel;
    public Slider roundsSlider;
    public Label difficultyLabel;
    public ChoiceBox<String> difficultyDropdown;
    public Label selectTowersLabel;
    public Button towerButton1;
    public Button towerButton2;
    public Button towerButton3;
    public Button towerButton4;
    public Button towerButton5;
    public Button towerButton6;
    public Label errorsLabel;
    public Label errorsLabelResult;
    public Label selectedTowerTitle;
    public Label selectedTowerResourcesLabel;
    public Label selectedTowerReloadSpeedLabel;
    public Label selectedTowerResourceTypeLabel;
    public Label selectedTowerLevelLabel;
    public Label selectedTowerCostLabel;
    public Button submitButton;

    public int selectedRounds = 5;
    public List<Integer> selectedTowers = new ArrayList<>();


    private NameInputService nameInputService;
    /**
     * Initialize the window
     *
     * @param stage Top level container for this window
     */
    public void init(Stage stage) {
        nameInputService = new NameInputService();

        difficultyDropdown.getItems().addAll("Easy", "Medium", "Hard", "Impossible"); //https://www.youtube.com/watch?v=K3CenJ2bMok&ab_channel=thenewboston

        roundsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedRounds = newValue.intValue();
        });

        //Tutorial 2
        List<Button> towerButtons = List.of(towerButton1, towerButton2, towerButton3, towerButton4, towerButton5, towerButton6);

        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                if (selectedTowers.contains(finalI + 1)) {
                    selectedTowers.remove(Integer.valueOf(finalI + 1)); // Use Integer.valueOf to remove by object (the value) not index
                    towerButtons.get(finalI).setStyle(""); // Reset style
                } else {
                    if (selectedTowers.size() < 3) {
                        selectedTowers.add(finalI + 1);
                        towerButtons.get(finalI).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    }
                }
            });
        }
    }


    /**
     * Method to call when the name submit button is clicked
     *
     */
    @FXML
    private void onSubmitButtonClicked() {
        System.out.println("Button has been clicked");
        /*
        Used code found on https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character to figure
        out how to find if a string contained special characters
        */
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(nameInput.getText());

        if (nameInput.getText().length() < 3 || nameInput.getText().length() > 15 || m.find()) {
            //If matcher finds a character not in a-z, A-Z or 0-9, or length is not between 3 and 15, run error
            //nameInputService.setNewName(nameInput.getText());
            System.out.println("ERROR");
            errorsLabelResult.setText("Name Selection Error");
            nameInput.setText("");
        } else {
            nameInputService.setNewName(nameInput.getText());
            //nameDisplay.setText(nameInput.getText());
            nameInput.setText("");
            System.out.println("Name is: " + nameInputService.getCurrentName());
        }

        System.out.println("# of Rounds: " + selectedRounds);
        if (difficultyDropdown.getValue() != null) {
            System.out.println("Difficulty: " + difficultyDropdown.getValue());
        } else {
            System.out.println("ERROR");
            errorsLabelResult.setText("Difficulty Selection Error");
        }

        if (selectedTowers.size() == 3) {
            System.out.println("Towers Selected: " + selectedTowers);
        } else {
            System.out.println("ERROR");
            errorsLabelResult.setText("Tower Selection Error");
        }
    }

        /*
        @FXML
        private Label defaultLabel;

        @FXML
        private Button defaultButton;

        private CounterService counterService;

        *//**
         * Initialize the window
         *
         * @param stage Top level container for this window
         *//*
        public void init(Stage stage) {
            counterService = new CounterService();
        }

        *//**
         * Method to call when our counter button is clicked
         *
         *//*
        @FXML
        public void onButtonClicked() {
            System.out.println("Button has been clicked");
            counterService.incrementCounter();

            int count = counterService.getCurrentCount();
            defaultLabel.setText(Integer.toString(count));
        }
        */

}