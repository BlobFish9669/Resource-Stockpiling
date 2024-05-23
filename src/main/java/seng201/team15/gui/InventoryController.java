package seng201.team15.gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import seng201.team15.GameManager;
import seng201.team15.gui.cellfactories.TowerCellFactory;
import seng201.team15.gui.cellfactories.UpgradeCellFactory;
import seng201.team15.models.Tower;
import seng201.team15.models.Upgrade;
import seng201.team15.services.CurrentRoundService;
import seng201.team15.services.MoneyBalanceService;
import seng201.team15.services.RoundsSelectionService;
import seng201.team15.services.InventoryService;

/**
 * Controller for the inventory.fxml window
 * @author Caleb Cooper
 */
public class InventoryController {

    private final GameManager gameManager;
    private final RoundsSelectionService roundsService;
    private final MoneyBalanceService moneyService;
    private final CurrentRoundService currentRoundService;
    private final InventoryService inventoryService;

    private Tower selectedMainTower;
    private Tower selectedReserveTower;
    private Upgrade selectedUpgrade;

    @FXML
    private Label currentMoneyLabel;
    @FXML
    private Label currentRoundLabel;
    @FXML
    private Label roundsRemainingLabel;
    @FXML
    private ListView<Tower> mainTowerList;
    @FXML
    private ListView<Tower> reserveTowerList;
    @FXML
    private ListView<Upgrade> upgradeList;
    @FXML
    private Button moveTowerButton;
    @FXML
    private Button useUpgradeButton;
    @FXML
    private Button backButton;

    private String towerSelected;
    private boolean upgradeSelected = false;

    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public InventoryController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.roundsService = gameManager.getRoundsService();
        this.moneyService = gameManager.getMoneyService();
        this.currentRoundService = gameManager.getCurrentRoundService();
        this.inventoryService = gameManager.getInventoryService();
    }
    /**
     * Initialize the window, sets up the buttons to change colour when hovered over, displays the users current money, round and rounds remaining.
     * Displays users current main towers, reserve towers and upgrades using list views and cell factories.
     */
    public void initialize() {

        backButton.setOnMouseEntered(event -> backButton.setStyle("-fx-background-color: #999999"));
        backButton.setOnMouseExited(event -> backButton.setStyle(""));

        moveTowerButton.setOnMouseEntered(event -> moveTowerButton.setStyle("-fx-background-color: #999999"));
        moveTowerButton.setOnMouseExited(event -> moveTowerButton.setStyle(""));

        useUpgradeButton.setOnMouseEntered(event -> useUpgradeButton.setStyle("-fx-background-color: #999999"));
        useUpgradeButton.setOnMouseExited(event -> useUpgradeButton.setStyle(""));

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

        mainTowerList.setCellFactory(new TowerCellFactory());
        mainTowerList.setItems(FXCollections.observableArrayList(inventoryService.getMainTowerSelection()));
        // Used Tut 3 code here as a template
        mainTowerList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) r -> {
            selectedMainTower = mainTowerList.getSelectionModel().getSelectedItem();
            towerSelected = "Main";
        });

        reserveTowerList.setCellFactory(new TowerCellFactory());
        reserveTowerList.setItems(FXCollections.observableArrayList(inventoryService.getReserveTowerSelection()));
        reserveTowerList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) r -> {
            selectedReserveTower = reserveTowerList.getSelectionModel().getSelectedItem();
            towerSelected = "Reserve";
        });

        upgradeList.setCellFactory(new UpgradeCellFactory());
        upgradeList.setItems(FXCollections.observableArrayList(inventoryService.getUserUpgrades()));
        upgradeList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Upgrade>) r -> {
            selectedUpgrade = upgradeList.getSelectionModel().getSelectedItem();
            upgradeSelected = true;
        });
    }

    /**
     * Method called when the back button is clicked, resets the pane and displays the main screen
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndLaunchMainScreen();
    }

    /**
     * Method called when the move tower button is clicked, ensures that a tower is selected, number of main towers is greater
     * than one and whether a main or reserve tower is selected then moves the tower accordingly
     */
    @FXML
    private void onMoveTowerButtonClicked() {
        if (towerSelected != null) {
            if (towerSelected.equals("Main")) {
                if (inventoryService.getMainTowerSelection().size() == 1) {
                    openErrorDialog("There must always be at least one main deposit");
                } else if(inventoryService.getReserveTowerSelection().size() < 5) {
                    inventoryService.addToReserveTowerSelection(selectedMainTower);
                    reserveTowerList.getItems().add(selectedMainTower);

                    inventoryService.removeMainTower(selectedMainTower);
                    mainTowerList.getItems().remove(selectedMainTower);
                } else {
                    openErrorDialog("Too many deposits in reserve deposits");
                }
                    clearSelections();
            } else {
                if (inventoryService.getMainTowerSelection().size() < 5) {
                    inventoryService.addToMainTowerSelection(selectedReserveTower);
                    mainTowerList.getItems().add(selectedReserveTower);

                    inventoryService.removeReserveTower(selectedReserveTower);
                    reserveTowerList.getItems().remove(selectedReserveTower);
                } else {
                    openErrorDialog("Too many deposits in main deposits");
                }
                clearSelections();
            }
        } else {
            openErrorDialog("Please select a deposit");
        }
    }

    /**
     * Clears the users previous selection in order to indicate that no tower or upgrade is selected
     */
    private void clearSelections() {
        mainTowerList.getSelectionModel().clearSelection();
        reserveTowerList.getSelectionModel().clearSelection();
        upgradeList.getSelectionModel().clearSelection();
        selectedMainTower = null;
        selectedReserveTower = null;
        selectedUpgrade = null;
        towerSelected = null;
        upgradeSelected = false;
    }

    /**
     * Method called when the use upgrade button is clicked, ensures that both a tower and upgrade are selected and whether
     * it is a main or reserve tower selected and then applies the selected upgrade to the selected tower
     */
    @FXML
    private void onUseUpgradeButtonClicked() {
        if (upgradeSelected && towerSelected != null) {
            if (towerSelected.equals("Main")) {
                if (selectedUpgrade.getResourceType().equals(selectedMainTower.getResourceType())) {
                    openErrorDialog("Deposit already has resource type " + selectedMainTower.getResourceType());
                } else {
                    selectedMainTower.applyUpgrade(selectedUpgrade);
                    inventoryService.removeUserUpgrade(selectedUpgrade);
                    upgradeList.getItems().remove(selectedUpgrade);
                }
            } else {
                if (selectedUpgrade.getResourceType().equals(selectedReserveTower.getResourceType())) {
                    openErrorDialog("Deposit already has resource type " + selectedReserveTower.getResourceType());
                } else {
                    selectedReserveTower.applyUpgrade(selectedUpgrade);
                    inventoryService.removeUserUpgrade(selectedUpgrade);
                    upgradeList.getItems().remove(selectedUpgrade);
                }
            }
            clearSelections();
        } else {
            openErrorDialog("Please select both a deposit AND an upgrade");
        }
    }

    /**
     * Method called in order to display a new dialog pane containing information about the provided error
     * @param message the error that should be displayed to the user
     */
    private void openErrorDialog(String message) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Error");
        VBox dialogContent = new VBox(10);
        dialogContent.getChildren().add(new Label(message));
        dialog.getDialogPane().setContent(dialogContent);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.show();
    }
}