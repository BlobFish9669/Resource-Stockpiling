package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import seng201.team0.GameManager;
import seng201.team0.gui.cellfactories.ShopTowerCellFactory;
import seng201.team0.gui.cellfactories.ShopUpgradeCellFactory;
import seng201.team0.gui.cellfactories.TowerCellFactory;
import seng201.team0.gui.cellfactories.UpgradeCellFactory;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.services.*;

import java.util.*;

/**
 * Controller for the shop.fxml window
 * @author Caleb Cooper
 */
public class ShopController {

    @FXML
    public Label shopLabel;

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

    public Button purchaseTowerButton;
    public Button purchaseUpgradeButton;
    public Button sellItemsButton;
    public Button backButton;
    public ListView<Tower> towersListView;
    public ListView<Upgrade> upgradesListView;


    private GameManager gameManager;
    private DifficultySelectionService difficultyService;
    private NameInputService nameService;
    private RoundsSelectionService roundsService;
    private InventoryService inventoryService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private ShopAvailabilityService shopAvailabilityService;

    public List<Tower> shopTowers;
    public List<Upgrade> shopUpgrades;
    public List<Boolean> purchasedTowers;
    public List<Boolean> purchasedUpgrades;

    private Tower towerToPurchase;
    private Integer towerButton;
    private Upgrade upgradeToPurchase;
    private Integer upgradeButton;

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
        this.inventoryService = gameManager.getInventoryService();
        this.moneyService = gameManager.getMoneyService();
        this.currentRoundService = gameManager.getCurrentRoundService();
        this.shopAvailabilityService = gameManager.getShopAvailabilityService();
    }
    /**
     * Initialize the window
     */
    public void initialize() {
        // Binds the width and height of the grid to the size of the window.
        //shopGrid.prefWidthProperty().bind(MenuWindow.getWidth());
        //shopGrid.prefHeightProperty().bind(MenuWindow.getHeight());

        purchaseTowerButton.setOnMouseEntered(event -> purchaseTowerButton.setStyle("-fx-background-color: #999999"));
        purchaseTowerButton.setOnMouseExited(event -> purchaseTowerButton.setStyle(""));

        purchaseUpgradeButton.setOnMouseEntered(event -> purchaseUpgradeButton.setStyle("-fx-background-color: #999999"));
        purchaseUpgradeButton.setOnMouseExited(event -> purchaseUpgradeButton.setStyle(""));

        backButton.setOnMouseEntered(event -> backButton.setStyle("-fx-background-color: #999999"));
        backButton.setOnMouseExited(event -> backButton.setStyle(""));

        sellItemsButton.setOnMouseEntered(event -> sellItemsButton.setStyle("-fx-background-color: #85aeb0"));
        sellItemsButton.setOnMouseExited(event -> sellItemsButton.setStyle("-fx-background-color: #b6dcdd"));


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

        purchasedTowers = shopAvailabilityService.getPurchasedTowers();
        purchasedUpgrades = shopAvailabilityService.getPurchasedUpgrades();

        for (int i = 0; i < purchasedTowers.size(); i++) {
            if (purchasedTowers.get(i)) {
                switch (i+1) {
                    case 1:
                        towerButton1.setDisable(true);
                        break;
                    case 2:
                        towerButton2.setDisable(true);
                        break;
                    case 3:
                        towerButton3.setDisable(true);
                        break;
                    case 4:
                        towerButton4.setDisable(true);
                        break;
                    case 5:
                        towerButton5.setDisable(true);
                        break;
                }
            }
        }

        for (int i = 0; i < purchasedUpgrades.size(); i++) {
            if (purchasedUpgrades.get(i)) {
                switch (i+1) {
                    case 1:
                        upgradeButton1.setDisable(true);
                        break;
                    case 2:
                        upgradeButton2.setDisable(true);
                        break;
                    case 3:
                        upgradeButton3.setDisable(true);
                        break;
                    case 4:
                        upgradeButton4.setDisable(true);
                        break;
                    case 5:
                        upgradeButton5.setDisable(true);
                        break;
                    case 6:
                        upgradeButton6.setDisable(true);
                        break;
                }
            }

        }

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

        List<Button> towerButtons = List.of(towerButton1, towerButton2, towerButton3, towerButton4, towerButton5);
        List<Button> upgradeButtons = List.of(upgradeButton1, upgradeButton2, upgradeButton3, upgradeButton4, upgradeButton5, upgradeButton6);

        // Have to choose between 3 and 5 unique random numbers between 1 and 10 depending on numTowersAvailable:
        towersListView.setCellFactory(new ShopTowerCellFactory());
        upgradesListView.setCellFactory(new ShopUpgradeCellFactory());


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
                    towerToPurchase = shopTowers.get(finalI);
                    towerButton = finalI+1;
                } else {
                    towerButtons.get(finalI).setStyle("");
                    towerToPurchase = null;
                    towerButton = null;
                    clearTowerStats();
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
                    upgradeToPurchase = shopUpgrades.get(finalI);
                    upgradeButton = finalI+1;
                } else {
                    upgradeButtons.get(finalI).setStyle("");
                    upgradeToPurchase = null;
                    upgradeButton = null;
                    clearUpgradeStats();
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

    @FXML
    private void onSellButtonClicked() { gameManager.resetAndOpenInventorySellScreen(); }
    /**
     * Method to show stats of the selected tower
     * @param towerIndex the index of the intended tower to view
     */
    private void showTowerStats(int towerIndex) {
        Tower selectedTower = shopTowers.get(towerIndex);
        ArrayList<Tower> tempList = new ArrayList<>();
        tempList.add(selectedTower);
        towersListView.setItems(FXCollections.observableArrayList(tempList));
    }

    private void clearTowerStats() {
        towersListView.setItems(null);
    }

    private void showUpgradeStats(int upgradeIndex) {
        Upgrade selectedUpgrade = shopUpgrades.get(upgradeIndex);
        ArrayList<Upgrade> tempList = new ArrayList<>();
        tempList.add(selectedUpgrade);
        upgradesListView.setItems(FXCollections.observableArrayList(tempList));
    }

    private void clearUpgradeStats() {
        upgradesListView.setItems(null);
    }

    @FXML
    private void onTowerPurchaseClicked() {
        if (towerToPurchase == null) {
            openErrorDialog("Error - Please select a tower");
        } else if (moneyService.getCurrentBalance() - towerToPurchase.getCost() < 0) {
            openErrorDialog("Error - Not enough money");
        } else if (inventoryService.getMainTowerSelection().size() == 5 && inventoryService.getReserveTowerSelection().size() == 5) {
            openErrorDialog("Error - Too many towers, sell one and try again");
        } else {
            switch (towerButton) {
                case 1:
                    towerButton1.setDisable(true);
                    towerButton1.setStyle("");
                    break;
                case 2:
                    towerButton2.setDisable(true);
                    towerButton2.setStyle("");
                    break;
                case 3:
                    towerButton3.setDisable(true);
                    towerButton3.setStyle("");
                    break;
                case 4:
                    towerButton4.setDisable(true);
                    towerButton4.setStyle("");
                    break;
                case 5:
                    towerButton5.setDisable(true);
                    towerButton5.setStyle("");
                    break;
            }
            moneyService.setNewBalance(moneyService.getCurrentBalance() - towerToPurchase.getCost());
            if (inventoryService.getMainTowerSelection().size() == 5) {
                inventoryService.addToReserveTowerSelection(towerToPurchase);
            } else {
                inventoryService.addToMainTowerSelection(towerToPurchase);
            }
            shopAvailabilityService.setTowerPurchased(towerButton-1);
            towerToPurchase = null;
        }
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
    }

    @FXML
    private void onUpgradePurchaseClicked() {
        if (upgradeToPurchase == null) {
            openErrorDialog("Error - Please select an upgrade");
        } else if (moneyService.getCurrentBalance() - upgradeToPurchase.getCost() < 0) {
            openErrorDialog("Error - Not enough money");
        } else {
            switch (upgradeButton) {
                case 1:
                    upgradeButton1.setDisable(true);
                    upgradeButton1.setStyle("");
                    break;
                case 2:
                    upgradeButton2.setDisable(true);
                    upgradeButton2.setStyle("");
                    break;
                case 3:
                    upgradeButton3.setDisable(true);
                    upgradeButton3.setStyle("");
                    break;
                case 4:
                    upgradeButton4.setDisable(true);
                    upgradeButton4.setStyle("");
                    break;
                case 5:
                    upgradeButton5.setDisable(true);
                    upgradeButton5.setStyle("");
                    break;
                case 6:
                    upgradeButton6.setDisable(true);
                    upgradeButton6.setStyle("");
                    break;
            }
            moneyService.setNewBalance(moneyService.getCurrentBalance() - upgradeToPurchase.getCost());
            inventoryService.addUserUpgrade(upgradeToPurchase);
            shopAvailabilityService.setUpgradePurchased(upgradeButton-1);
            upgradeToPurchase = null;
        }
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
    }

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