package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import seng201.team0.GameManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.services.CurrentRoundService;
import seng201.team0.services.MoneyBalanceService;
import seng201.team0.services.RoundsSelectionService;
import seng201.team0.services.InventoryService;

/**
 * Controller for the inventory.fxml window
 * @author Caleb Cooper
 */
public class InventoryController {

    private GameManager gameManager;
    private RoundsSelectionService roundsService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private InventoryService inventoryService;
    private Tower selectedMainTower;
    private Tower selectedReserveTower;
    private Upgrade selectedUpgrade;

    @FXML
    public BorderPane inventoryBorderPane;
    public Label inventoryLabel;
    public Label currentMoneyLabel;
    public Label currentRoundLabel;
    public Label roundsRemainingLabel;
    public ListView<Tower> mainTowerList;
    public ListView<Tower> reserveTowerList;
    public ListView<Upgrade> upgradeList;
    public Button moveTowerButton;
    public Button useUpgradeButton;
    public Button backButton;

    private Boolean isTowerSelectedMain;


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
     * Initialize the window
     */
    public void initialize() {
        // Binds the width and height of the grid to the size of the window.
        inventoryBorderPane.prefWidthProperty().bind(MenuWindow.getWidth());
        inventoryBorderPane.prefHeightProperty().bind(MenuWindow.getHeight());

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));
        System.out.println("Inventory Page");


        mainTowerList.setCellFactory(new TowerCellFactory());
        mainTowerList.setItems(FXCollections.observableArrayList(inventoryService.getMainTowerSelection()));
        // Used Tut 3 code here as a template
        mainTowerList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) r -> {
            selectedMainTower = mainTowerList.getSelectionModel().getSelectedItem();
            isTowerSelectedMain = true;
        });

        reserveTowerList.setCellFactory(new TowerCellFactory());
        reserveTowerList.setItems(FXCollections.observableArrayList(inventoryService.getReserveTowerSelection()));
        reserveTowerList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) r -> {
            selectedReserveTower = reserveTowerList.getSelectionModel().getSelectedItem();
            isTowerSelectedMain = false;
        });

        upgradeList.setCellFactory(new UpgradeCellFactory());
        upgradeList.setItems(FXCollections.observableArrayList(inventoryService.getUserUpgrades()));
        upgradeList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Upgrade>) r -> {
            selectedUpgrade = upgradeList.getSelectionModel().getSelectedItem();
        });
    }
    /**
     * Method to call when the back button is clicked
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndLaunchMainScreen();
    }
    @FXML
    private void onMoveTowerButtonClicked() {
        if (isTowerSelectedMain != null) {
            if (isTowerSelectedMain) {
                if (inventoryService.getMainTowerSelection().size() == 1) {
                    System.out.println("Error - There must always be at least one main tower");
                } else if(inventoryService.getReserveTowerSelection().size() < 5) {
                    inventoryService.addToReserveTowerSelection(selectedMainTower);
                    reserveTowerList.getItems().add(selectedMainTower);

                    inventoryService.removeMainTower(selectedMainTower);
                    mainTowerList.getItems().remove(selectedMainTower);
                } else {
                    System.out.println("Error - Too many towers in reserve towers");
                }
                    clearSelections();
            } else {
                if (inventoryService.getMainTowerSelection().size() < 5) {
                    inventoryService.addToMainTowerSelection(selectedReserveTower);
                    mainTowerList.getItems().add(selectedReserveTower);

                    inventoryService.removeReserveTower(selectedReserveTower);
                    reserveTowerList.getItems().remove(selectedReserveTower);
                } else {
                    System.out.println("Error - Too many towers in main towers");
                }
                clearSelections();
            }
        }
    }
    private void clearSelections() {
        mainTowerList.getSelectionModel().clearSelection();
        reserveTowerList.getSelectionModel().clearSelection();
        selectedMainTower = null;
        selectedReserveTower = null;
        isTowerSelectedMain = null;
    }
    @FXML
    private void onUseUpgradeButtonClicked() {

    }
}