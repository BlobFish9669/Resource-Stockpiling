package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import seng201.team0.gui.MenuWindow;
//import seng201.team0.services.CounterService;

import javafx.scene.layout.*;
import seng201.team0.GameManager;
import seng201.team0.models.Tower;
import seng201.team0.models.towertypes.*;

import seng201.team0.services.*;

import java.util.Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for the menu.fxml window
 * @author Caleb Cooper, Quinn Le Lievre
 */
public class MenuController {

    private GameManager gameManager;
    private DifficultySelectionService difficultyService;
    private NameInputService nameService;
    private RoundsSelectionService roundsService;
    private InventoryService inventoryService;
    private MoneyBalanceService moneyService;

    @FXML
    public BorderPane menuBorderPane;
    public GridPane errorsGridPane;
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
    public List<Tower> towerTypes = new ArrayList<>();
    public ArrayList<Integer> tempSelectedTowers = new ArrayList<>();
    public ArrayList<Tower> selectedTowers = new ArrayList<>();


    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public MenuController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.difficultyService = gameManager.getDifficultyService();
        this.nameService = gameManager.getNameService();
        this.roundsService = gameManager.getRoundsService();
        this.inventoryService = gameManager.getInventoryService();
        this.moneyService = gameManager.getMoneyService();

    }

    /**
     * Initialize the window
     */
    public void initialize() {
        // Binds the width and height of the grid to the size of the window.
        menuBorderPane.prefWidthProperty().bind(MenuWindow.getWidth());
        menuBorderPane.prefHeightProperty().bind(MenuWindow.getHeight());


        difficultyDropdown.getItems().addAll("Easy", "Medium", "Hard", "Impossible"); //https://www.youtube.com/watch?v=K3CenJ2bMok&ab_channel=thenewboston

        roundsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedRounds = newValue.intValue();
        });

        Collections.addAll(towerTypes, new TowerOne(), new TowerTwo(), new TowerThree(), new TowerFour(), new TowerFive(), new TowerSix());

        //Tutorial 2
        List<Button> towerButtons = List.of(towerButton1, towerButton2, towerButton3, towerButton4, towerButton5, towerButton6);

        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                showStats(finalI);
                if (tempSelectedTowers.contains(finalI)) {
                    tempSelectedTowers.remove(Integer.valueOf(finalI)); // Use Integer.valueOf to remove by object (the value) not index
                    towerButtons.get(finalI).setStyle(""); // Reset style
                } else {
                    if (tempSelectedTowers.size() < 3) {
                        tempSelectedTowers.add(finalI); // Add tower to the ArrayList
                        towerButtons.get(finalI).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;"); // Set button to look like it has been selected
                    }
                }
            });
        }
    }
    /**
     * Method to show stats of the selected tower
     * @param towerIndex the index of the intended tower to view
     */
    private void showStats(int towerIndex) {
        Tower selectedTower = towerTypes.get(towerIndex);
        selectedTowerResourcesLabel.setText("Resources: " + selectedTower.getResourceAmount());
        selectedTowerReloadSpeedLabel.setText("Reload Speed: " + selectedTower.getReloadSpeed());
        selectedTowerResourceTypeLabel.setText("Resource Type: " + selectedTower.getResourceType());
        selectedTowerLevelLabel.setText("Level: " + selectedTower.getLevel());
        selectedTowerCostLabel.setText("Cost: " + selectedTower.getCost());
    }


    /**
     * Method to call when the name submit button is clicked
     */
    @FXML
    private void onSubmitButtonClicked() {
        System.out.println("Button has been clicked");
        // Resets error label each time button is clicked
        errorsLabelResult.setText("");
        /*
        Used code found on https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character to figure
        out how to find if a string contained special characters
        */
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(nameInput.getText());

        /*
        Stores an int to check how many errors there are.
        Displays all current error messages if there are any.
        */
       int errors = 0;
        if (nameInput.getText().length() < 3 || nameInput.getText().length() > 15 || m.find()) {
            //If matcher finds a character not in a-z, A-Z or 0-9, or length is not between 3 and 15, run error
            errorsLabelResult.setText(errorsLabelResult.getText() + "Error - Please enter a valid name\n");
            nameInput.setText("");
            errors += 1;
        }
        if (difficultyDropdown.getValue() == null) {
            errorsLabelResult.setText(errorsLabelResult.getText() + "Error - Please select a difficulty\n");
            errors += 1;
        }
        if (tempSelectedTowers.size() != 3) {
            errorsLabelResult.setText(errorsLabelResult.getText() + "Error - Please select at least 3 Towers");
            errors += 1;
        }
        if (errors == 0) {
            nameService.setNewName(nameInput.getText());
            roundsService.setRoundsSelection(selectedRounds);
            difficultyService.setDifficultySelection(difficultyDropdown.getValue());
            for (Integer tempSelectedTower : tempSelectedTowers) {
                selectedTowers.add(towerTypes.get(tempSelectedTower));
            }
            inventoryService.setTowerSelection(selectedTowers);

            String difficulty = difficultyService.getDifficultySelection();

            if (Objects.equals(difficulty, "Easy")) { //Used Object.equals instead of == just in case of null value
                moneyService.setNewBalance(100);
            } else if (Objects.equals(difficulty, "Medium")) {
                moneyService.setNewBalance(75);
            } else if (Objects.equals(difficulty, "Hard")) {
                moneyService.setNewBalance(50);
            } else  {
                moneyService.setNewBalance(25);
            }


            System.out.println("--------------------------------------");
            System.out.println("Name is: " + nameService.getCurrentName());
            System.out.println("# of Rounds: " + roundsService.getRoundsSelection());
            System.out.println("Difficulty: " + difficultyService.getDifficultySelection());
            System.out.println("Towers Selected: " + inventoryService.getTowerSelection());
            System.out.println("--------------------------------------");


            gameManager.resetAndLaunchMainScreen();
        }
    }
}