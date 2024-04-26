package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import seng201.team0.GameManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.services.CurrentRoundService;
import seng201.team0.services.InventoryService;
import seng201.team0.services.MoneyBalanceService;
import seng201.team0.services.RoundsSelectionService;

/**
 * Controller for the inventory.fxml window
 * @author Caleb Cooper
 */
public class InventorySellController {

    @FXML
    public BorderPane inventorySellBorderPane;

    public Label title;

    public Label currentMoney;
    public Label currentMoneyLabel;
    public Label currentRound;
    public Label currentRoundLabel;
    public Label roundsRemaining;
    public Label roundsRemainingLabel;

    public Label mainTowers;
    public ListView<Tower> mainTowerList;

    public Label reserveTowers;
    public ListView<Tower> reserveTowerList;

    public Label upgrades;
    public ListView<Upgrade> upgradeList;

    public Button sellTower;
    public Button sellUpgrade;
    public Button backButton;

    private GameManager gameManager;
    private RoundsSelectionService roundsService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private InventoryService inventoryService;

    private Tower selectedMainTower;
    private Tower selectedReserveTower;
    private Upgrade selectedUpgrade;
    private Boolean isTowerSelectedMain;

    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public InventorySellController(GameManager gameManager) {
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
        inventorySellBorderPane.prefWidthProperty().bind(MenuWindow.getWidth());
        inventorySellBorderPane.prefHeightProperty().bind(MenuWindow.getHeight());

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

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
        gameManager.resetAndOpenShopScreen();
    }

    @FXML
    private void onSellTowerButtonClicked() {
        if (isTowerSelectedMain != null) {
            if (isTowerSelectedMain) {
                inventoryService.removeMainTower(selectedMainTower);
                moneyService.setNewBalance(moneyService.getCurrentBalance() + selectedMainTower.getCost());
                currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());

                mainTowerList.getItems().remove(selectedMainTower);
                clearSelections();
            } else {
                inventoryService.removeReserveTower(selectedReserveTower);
                moneyService.setNewBalance(moneyService.getCurrentBalance() + selectedReserveTower.getCost());
                currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());

                reserveTowerList.getItems().remove(selectedReserveTower);
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
    private void onSellUpgradeButtonClicked() {
        if (selectedUpgrade != null) {
            inventoryService.removeUserUpgrade(selectedUpgrade);
            moneyService.setNewBalance(moneyService.getCurrentBalance() + selectedUpgrade.getCost());
            currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());

            upgradeList.getItems().remove(selectedUpgrade);
            upgradeList.getSelectionModel().clearSelection();
            selectedUpgrade = null;
        }
    }
}
