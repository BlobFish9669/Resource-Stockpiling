package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import seng201.team0.GameManager;
import seng201.team0.models.Tower;
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
    public GridPane inventorySellGrid;

    public Label title;

    public Label currentMoney;
    public Label currentMoneyLabel;
    public Label currentRound;
    public Label currentRoundLabel;
    public Label roundsRemaining;
    public Label roundsRemainingLabel;

    public Label mainTowers;
    public ListView<Tower> mainTowerList;

    public Button backButton;

    private GameManager gameManager;
    private RoundsSelectionService roundsService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private InventoryService inventoryService;

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
        inventorySellGrid.prefWidthProperty().bind(MenuWindow.getWidth());
        inventorySellGrid.prefHeightProperty().bind(MenuWindow.getHeight());

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

        mainTowerList.setCellFactory(new MainTowerCellFactory());
        //mainTowerList.setItems(FXCollections.observableArrayList(rocketService.getRandomRockets(15)));
        mainTowerList.setItems(FXCollections.observableArrayList(inventoryService.getMainTowerSelection()));
    }
    /**
     * Method to call when the back button is clicked
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndOpenShopScreen();
    }
}
