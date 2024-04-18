package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
import seng201.team0.services.*;

import java.util.Random;

/**
 * Controller for the shop.fxml window
 * @author Caleb Cooper
 */
public class ShopController {

    @FXML
    public Label shopLabel;
    public Button backButton;

    public Label currentMoney;
    public Label currentRound;
    public Label roundsRemaining;
    public Label currentMoneyLabel;
    public Label currentRoundLabel;
    public Label roundsRemainingLabel;

    public Label towersLabel;
    public Button towerButton1;
    public Button towerButton2;
    public Button towerButton3;
    public Button towerButton4;
    public Button towerButton5;

    public Label upgradesLabel;
    public Button upgradeButton1;
    public Button upgradeButton2;
    public Button upgradeButton3;
    public Button upgradeButton4;
    public Button upgradeButton5;
    public Button upgradeButton6;

    public Label towerStatsLabel;
    public Label resourceAmount;
    public Label resourceAmountLabel;
    public Label reloadSpeed;
    public Label reloadSpeedLabel;
    public Label resourceType;
    public Label resourceTypeLabel;
    public Label towerLevel;
    public Label towerLevelLabel;
    public Label towerCost;
    public Label towerCostLabel;

    public Label upgradeStats;
    public Label upgradeCost;
    public Label upgradeCostLabel;

    public Button purchaseButton;

    private GameManager gameManager;
    private DifficultySelectionService difficultyService;
    private NameInputService nameService;
    private RoundsSelectionService roundsService;
    private TowerSelectionService towerService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private ShopAvailabilityService shopAvailabilityService;
    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public ShopController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.difficultyService = gameManager.getDifficultyService();
        this.nameService = gameManager.getNameService();
        this.roundsService = gameManager.getRoundsService();
        this.towerService = gameManager.getTowerService();
        this.moneyService = gameManager.getMoneyService();
        this.currentRoundService = gameManager.getCurrentRoundService();
        this.shopAvailabilityService = gameManager.getShopAvailabilityService();
    }
    /**
     * Initialize the window
     */
    public void initialize() {
        int towersAvailable = shopAvailabilityService.getTowersAvailable();
        if (towersAvailable == 4) {
            towerButton4.setDisable(false);
        } else if (towersAvailable == 5) {
            towerButton4.setDisable(false);
            towerButton5.setDisable(false);
        }

        int upgradesAvailable = shopAvailabilityService.getUpgradesAvailable();
        if (upgradesAvailable == 4) {
            upgradeButton4.setDisable(false);
        } else if (upgradesAvailable == 5) {
            upgradeButton4.setDisable(false);
            upgradeButton5.setDisable(false);
        } else if (upgradesAvailable == 6) {
            upgradeButton4.setDisable(false);
            upgradeButton5.setDisable(false);
            upgradeButton6.setDisable(false);
        }

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

    }
    /**
     * Method to call when the back button is clicked
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndLaunchMainScreen();
    }
}