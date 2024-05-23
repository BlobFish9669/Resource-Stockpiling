package seng201.team15.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import seng201.team15.GameManager;
import seng201.team15.gui.cellfactories.ShopTowerCellFactory;
import seng201.team15.gui.cellfactories.ShopUpgradeCellFactory;
import seng201.team15.models.Tower;
import seng201.team15.models.Upgrade;
import seng201.team15.services.*;

import java.util.*;

/**
 * Controller for the shop.fxml window
 * @author Caleb Cooper
 */
public class ShopController {

    private final GameManager gameManager;
    private final RoundsSelectionService roundsService;
    private final InventoryService inventoryService;
    private final MoneyBalanceService moneyService;
    private final CurrentRoundService currentRoundService;
    private final ShopAvailabilityService shopAvailabilityService;

    @FXML
    private Label currentMoneyLabel;
    @FXML
    private Label currentRoundLabel;
    @FXML
    private Label roundsRemainingLabel;
    @FXML
    private Button towerButton1;
    @FXML
    private Button towerButton2;
    @FXML
    private Button towerButton3;
    @FXML
    private Button towerButton4;
    @FXML
    private Button towerButton5;
    @FXML
    private Button upgradeButton1;
    @FXML
    private Button upgradeButton2;
    @FXML
    private Button upgradeButton3;
    @FXML
    private Button upgradeButton4;
    @FXML
    private Button upgradeButton5;
    @FXML
    private Button upgradeButton6;
    @FXML
    private Button purchaseTowerButton;
    @FXML
    private Button purchaseUpgradeButton;
    @FXML
    private Button sellItemsButton;
    @FXML
    private Button backButton;
    @FXML
    private ListView<Tower> towersListView;
    @FXML
    private ListView<Upgrade> upgradesListView;

    private List<Tower> shopTowers;
    private List<Upgrade> shopUpgrades;
    private List<Boolean> purchasedTowers;
    private List<Boolean> purchasedUpgrades;

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
        this.roundsService = gameManager.getRoundsService();
        this.inventoryService = gameManager.getInventoryService();
        this.moneyService = gameManager.getMoneyService();
        this.currentRoundService = gameManager.getCurrentRoundService();
        this.shopAvailabilityService = gameManager.getShopAvailabilityService();
    }

    /**
     * Initialize the window, sets up the buttons to change colour when hovered over, displays the users current money, round and rounds remaining.
     * Sets up the buttons according to what has or hasn't previously been purchased that round and sets up the list views to
     * display the tower and upgrade stats
     */
    public void initialize() {

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
     * Method called when the back button is clicked, resets the pane and displays the main screen
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndLaunchMainScreen();
    }

    /**
     * Method called when the sell button is clicked, resets the pane and displays the inventory sell screen
     */
    @FXML
    private void onSellButtonClicked() { gameManager.resetAndOpenInventorySellScreen(); }

    /**
     * Method to display stats of the selected tower
     * @param towerIndex the index of the intended tower to view
     */
    private void showTowerStats(int towerIndex) {
        Tower selectedTower = shopTowers.get(towerIndex);
        ArrayList<Tower> tempList = new ArrayList<>();
        tempList.add(selectedTower);
        towersListView.setItems(FXCollections.observableArrayList(tempList));
    }

    /**
     * Clears the tower list view of any selected tower
     */
    private void clearTowerStats() {
        towersListView.setItems(null);
    }

    /**
     * Method to display stats of the selected upgrade
     * @param upgradeIndex the index of the intended upgrade to view
     */
    private void showUpgradeStats(int upgradeIndex) {
        Upgrade selectedUpgrade = shopUpgrades.get(upgradeIndex);
        ArrayList<Upgrade> tempList = new ArrayList<>();
        tempList.add(selectedUpgrade);
        upgradesListView.setItems(FXCollections.observableArrayList(tempList));
    }

    /**
     * Clears the upgrade list view of any selected upgrades
     */
    private void clearUpgradeStats() {
        upgradesListView.setItems(null);
    }

    /**
     * Makes sure a tower is selected, balance is enough to cover the tower and the inventory isn't full. Adds the tower
     * to the users inventory into either main or reserve depending on if main is already full, reduces money balance and
     * sets the tower to purchased in order to not be purchased again
     */
    @FXML
    private void onTowerPurchaseClicked() {
        if (towerToPurchase == null) {
            openErrorDialog("Please select a deposit");
        } else if (moneyService.getCurrentBalance() - towerToPurchase.getCost() < 0) {
            openErrorDialog("Not enough money");
        } else if (inventoryService.getMainTowerSelection().size() == 5 && inventoryService.getReserveTowerSelection().size() == 5) {
            openErrorDialog("Too many deposits, sell one and try again");
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
            moneyService.subtractBalance(towerToPurchase.getCost());
            if (inventoryService.getMainTowerSelection().size() == 5) {
                inventoryService.addToReserveTowerSelection(towerToPurchase);
            } else {
                inventoryService.addToMainTowerSelection(towerToPurchase);
            }
            shopAvailabilityService.setTowerPurchased(towerButton-1);
            towerToPurchase = null;
            clearTowerStats();
        }
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
    }

    /**
     * Makes sure an upgrade is selected and balance is enough to cover the upgrade. Adds the upgrade to the users
     * inventory, reduces money balance and sets the upgrade to purchased in order to not be purchased again
     */
    @FXML
    private void onUpgradePurchaseClicked() {
        if (upgradeToPurchase == null) {
            openErrorDialog("Please select an upgrade");
        } else if (moneyService.getCurrentBalance() - upgradeToPurchase.getCost() < 0) {
            openErrorDialog("Not enough money");
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
            moneyService.subtractBalance(upgradeToPurchase.getCost());
            inventoryService.addUserUpgrade(upgradeToPurchase);
            shopAvailabilityService.setUpgradePurchased(upgradeButton-1);
            upgradeToPurchase = null;
            clearUpgradeStats();
        }
        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
    }

    /**
     * Displays information about the provided error in a dialog box
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