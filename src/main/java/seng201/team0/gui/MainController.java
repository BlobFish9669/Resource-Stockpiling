package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
import seng201.team0.services.*;

import java.util.Objects;

/**
 * Controller for the main.fxml window
 * @author Caleb Cooper
 */
public class MainController {

    private GameManager gameManager;
    private DifficultySelectionService difficultyService;
    private NameInputService nameService;
    private RoundsSelectionService roundsService;
    private TowerSelectionService towerService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;


    @FXML
    public Button shopButton;
    public Button inventoryButton;
    public Label mainLabel;
    public Label currentMoney;
    public Label currentMoneyLabel;
    public Label currentRound;
    public Label currentRoundLabel;
    public Label roundsRemaining;
    public Label roundsRemainingLabel;

    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game
     */
    public MainController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.difficultyService = gameManager.getDifficultyService();
        this.nameService = gameManager.getNameService();
        this.roundsService = gameManager.getRoundsService();
        this.towerService = gameManager.getTowerService();
        this.moneyService = gameManager.getMoneyService();
        this.currentRoundService = gameManager.getCurrentRoundService();
    }

    /**
     * Initialize the window
     */
    public void initialize() {
        currentRoundService.setCurrentRound(1);
        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();

        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));
    }

    /**
     * Method to call when the shop button is clicked
     */
    @FXML
    private void onShopButtonClicked() {
        gameManager.resetAndOpenShopScreen();
    }

    /**
     * Method to call when the inventory button is clicked
     */
    @FXML
    private void onInventoryButtonClicked() {
        gameManager.resetAndOpenInventoryScreen();
    }
}
