package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.models.towertypes.*;
import seng201.team0.services.*;

import java.util.*;

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
    public Label upgradeTitle1;
    public Label upgradeInfo1;

    public Button purchaseButton;


    private GameManager gameManager;
    private DifficultySelectionService difficultyService;
    private NameInputService nameService;
    private RoundsSelectionService roundsService;
    private TowerSelectionService towerService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private ShopAvailabilityService shopAvailabilityService;

    public List<Tower> shopTowers = new ArrayList<>();
    public List<Upgrade> shopUpgrades = new ArrayList<>();
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
        int numTowersAvailable = shopAvailabilityService.getNumberTowersAvailable();
        if (numTowersAvailable == 4) {
            towerButton4.setDisable(false);
        } else if (numTowersAvailable == 5) {
            towerButton4.setDisable(false);
            towerButton5.setDisable(false);
        }

        int numUpgradesAvailable = shopAvailabilityService.getNumberUpgradesAvailable();
        if (numUpgradesAvailable == 4) {
            upgradeButton4.setDisable(false);
        } else if (numUpgradesAvailable == 5) {
            upgradeButton4.setDisable(false);
            upgradeButton5.setDisable(false);
        } else if (numUpgradesAvailable == 6) {
            upgradeButton4.setDisable(false);
            upgradeButton5.setDisable(false);
            upgradeButton6.setDisable(false);
        }

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

        List<Button> towerButtons = List.of(towerButton1, towerButton2, towerButton3, towerButton4, towerButton5);
        List<Button> upgradeButtons = List.of(upgradeButton1, upgradeButton2, upgradeButton3, upgradeButton4, upgradeButton5, upgradeButton6);

        // Have to choose between 3 and 5 unique random numbers between 1 and 10 depending on numTowersAvailable:

        for (int i = 0; i < numTowersAvailable; i++) {
            shopTowers = shopAvailabilityService.getAvailableTowers();
            final int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                for (int j = 0; j < towerButtons.size(); j++) { // Set all buttons to have no style - ensure that no buttons look selected other than the intended one
                    if (j != finalI) {
                        towerButtons.get(j).setStyle("");
                    }
                }
                showTowerStats(finalI);
                if (Objects.equals(towerButtons.get(finalI).getStyle(), "")) { // Set style of button to indicate it is selected
                    towerButtons.get(finalI).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                } else {
                    towerButtons.get(finalI).setStyle("");
                }
            });
        }

        // Have to choose between 3 and 6 not necessarily unique random numbers between 1 and ... depending on numUpgrades available:

        for (int i = 0; i < numUpgradesAvailable; i++) {
            shopUpgrades = shopAvailabilityService.getAvailableUpgrades();
            final int finalI = i;
            upgradeButtons.get(i).setOnAction(event -> {
                for (int j = 0; j < upgradeButtons.size(); j++) { // Set all buttons to have no style - ensure that no buttons look selected other than the intended one
                    if (j != finalI) {
                        upgradeButtons.get(j).setStyle("");
                    }
                }
                showUpgradeStats(finalI);
                if (Objects.equals(upgradeButtons.get(finalI).getStyle(), "")) { // Set style of button to indicate it is selected
                    upgradeButtons.get(finalI).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                } else {
                    upgradeButtons.get(finalI).setStyle("");
                }
            });
        }

    }
    /**
     * Method to call when the back button is clicked
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndLaunchMainScreen();
    }
    /**
     * Method to show stats of the selected tower
     * @param towerIndex the index of the intended tower to view
     */
    private void showTowerStats(int towerIndex) {
        Tower selectedTower = shopTowers.get(towerIndex);
        resourceAmountLabel.setText(String.valueOf((selectedTower.getResourceAmount())));
        reloadSpeedLabel.setText(String.valueOf((selectedTower.getReloadSpeed())));
        resourceTypeLabel.setText(selectedTower.getResourceType());
        towerLevelLabel.setText(String.valueOf((selectedTower.getLevel())));
        towerCostLabel.setText(String.valueOf(selectedTower.getCost()));
    }

    private void showUpgradeStats(int upgradeIndex) {
        Upgrade selectedUpgrade = shopUpgrades.get(upgradeIndex);
        upgradeInfo1.setText(selectedUpgrade.getUpgradeType());
        upgradeCostLabel.setText(String.valueOf(selectedUpgrade.getCost()));
    }
}