package seng201.team15.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import seng201.team15.GameManager;
import seng201.team15.gui.cellfactories.CartCellFactory;
import seng201.team15.models.Cart;
import seng201.team15.models.Tower;
import seng201.team15.services.*;

import java.util.*;

/**
 * Controller for the main.fxml window
 * @author Caleb Cooper
 */
public class MainController {

    private final GameManager gameManager; // final because services aren't themselves changed, just provide info through getters and setters
    private final RoundsSelectionService roundsService;
    private final InventoryService inventoryService;
    private final MoneyBalanceService moneyService;
    private final CurrentRoundService currentRoundService;
    private final ShopAvailabilityService shopAvailabilityService;
    private final PlayerScoreService playerScoreService;

    @FXML
    private Label currentMoneyLabel;
    @FXML
    private Label currentRoundLabel;
    @FXML
    private Label roundsRemainingLabel;
    @FXML
    private Label distance;
    @FXML
    private Label numberCarts;
    @FXML
    private ChoiceBox<String> roundDifficultyDropdown;
    @FXML
    private ListView<Cart> cartList;
    @FXML
    private Button playRound;
    @FXML
    private Button shopButton;
    @FXML
    private Button inventoryButton;

    private List<String> randomEventList = new ArrayList<>();
    private int remainingRounds;

    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game
     */
    public MainController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.roundsService = gameManager.getRoundsService();
        this.inventoryService = gameManager.getInventoryService();
        this.moneyService = gameManager.getMoneyService();
        this.currentRoundService = gameManager.getCurrentRoundService();
        this.shopAvailabilityService = gameManager.getShopAvailabilityService();
        this.playerScoreService = gameManager.getPlayerScoreService();
    }

    /**
     * Initialize the window, sets up the buttons to change colour when hovered over, displays the users current money, round and rounds remaining.
     * Sets up the carts list view
     */
    public void initialize() {
        remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();

        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

        playRound.setOnMouseEntered(event -> playRound.setStyle("-fx-background-color: #A08B27"));
        playRound.setOnMouseExited(event -> playRound.setStyle("-fx-background-color: #D4AF37"));

        shopButton.setOnMouseEntered(event -> shopButton.setStyle("-fx-background-color: #999999"));
        shopButton.setOnMouseExited(event -> shopButton.setStyle(""));

        inventoryButton.setOnMouseEntered(event -> inventoryButton.setStyle("-fx-background-color: #999999"));
        inventoryButton.setOnMouseExited(event -> inventoryButton.setStyle(""));

        //generate carts
        roundDifficultyDropdown.getItems().addAll("Easy", "Medium", "Hard");

        if (currentRoundService.getDifficulty() != null) { //If difficulty has previously been set
            roundDifficultyDropdown.setValue(currentRoundService.getDifficulty());
            distance.setText(currentRoundService.getDistance() + " Metres");
            numberCarts.setText(currentRoundService.getNumCarts().toString());
            cartList.setCellFactory(new CartCellFactory());
            cartList.setItems(FXCollections.observableArrayList(currentRoundService.getPotentialCarts()));
        }

        roundDifficultyDropdown.setOnAction(event -> {
            currentRoundService.setDifficulty(roundDifficultyDropdown.getValue());
            distance.setText(currentRoundService.getDistance() + " Metres");
            numberCarts.setText(currentRoundService.getNumCarts().toString());
            cartList.setCellFactory(new CartCellFactory());
            cartList.setItems(FXCollections.observableArrayList(currentRoundService.getPotentialCarts()));
        });
    }

    /**
     * Method called when the shop button is clicked, resets the pane and displays the shop screen
     */
    @FXML
    private void onShopButtonClicked() {
        gameManager.resetAndOpenShopScreen();
    }

    /**
     * Method called when the inventory button is clicked, resets the pane and displays the inventory screen
     */
    @FXML
    private void onInventoryButtonClicked() {
        gameManager.resetAndOpenInventoryScreen();
    }

    /**
     * Method called when all rounds are completed or when game is failed, resets the plane and displays the end screen
     */
    private void gameOver() { gameManager.resetAndOpenEndScreen(); }

    /**
     * Method called when the play round button is clicked, checks to see if difficulty is selected and starts the round,
     * otherwise displays error message
     */
    @FXML
    private void onPlayRoundButtonClicked() {
        if (roundDifficultyDropdown.getValue() != null && roundDifficultyDropdown.getValue() != "") {
            currentRoundService.storeCarts(currentRoundService.getPotentialCarts());
            startRound();
        } else {
            openErrorDialog("Error - No difficulty selected");
        }
    }

    /**
     * Contains the maths to check if all the carts can be filled in time before they reach the end of the track after
     * checking if there is a corresponding tower for each cart. Calls finishRound() if yes, otherwise displays error message
     * and ends game.
     */
    private void startRound() {
        if (checkResources()) {
            //Round not failed yet - check distance
            for (Cart cart: currentRoundService.getCarts()) {
                if (cart.getFilledSize() != cart.getSize()) {
                    boolean cartFilled = false;
                    for (Tower tower : inventoryService.getMainTowerSelection()) {
                        if (tower.getResourceType() == cart.getResourceType()) {
                            double timeToFill = (double) (cart.getSize() - cart.getFilledSize()) / tower.getFillRate(); // = time in mins
                            double distanceRemaining = (cart.getSpeed()*((double) 1000/60)) * timeToFill; // Turn to m/min to be able to compare
                            if (distanceRemaining <= currentRoundService.getDistance()) {
                                cart.fill((int) (tower.getFillRate() * timeToFill));
                                cartFilled = true;  // Getting a Caused by: java.util.ConcurrentModificationException error here somewhere?
                                break;
                            }
                        }
                    }

                    if (!cartFilled) {
                        openErrorDialog("Error - Not filled in time");
                        gameOver();
                        break;
                    }
                }
            }
            //Round completed
            finishRound();

        } else {
            gameOver();
            openErrorDialog("Error - There was one or more carts that did not have a corresponding tower");

        }
    }

    /**
     * Checks to see if game is completed, otherwise has the possibility of a random event occurring and resets the
     * screen, ready for a new round
     */
    private void finishRound() {
        if (remainingRounds == 0) {
            currentRoundService.setGameSuccess();
            gameOver();
        } else {
            if (remainingRounds > 0) {
                randomTowerEvent();
            }
            resetAndUpdateLabelsAndStats();
        }
    }

    /**
     * Goes through the list of carts to see if there is a corresponding tower in the users current main tower
     * selection with a matching resource type which will allow it to be filled
     * @return returns true if all carts have a corresponding tower, otherwise returns false
     */
    private Boolean checkResources() {
        ArrayList<Boolean> cartResourceTypeSupported = new ArrayList<>(Arrays.asList(new Boolean[currentRoundService.getCarts().size()]));
        Collections.fill(cartResourceTypeSupported, false); //https://stackoverflow.com/questions/20615448/set-all-values-of-arraylistboolean-to-false-on-instantiation
        int i = 0;
        for (Cart cart: currentRoundService.getCarts()) {
            for (Tower tower: inventoryService.getMainTowerSelection()) {
                if (tower.getResourceType() == cart.getResourceType()) {
                    cartResourceTypeSupported.set(i, true);
                }
            }
            i++;
        }
        return !cartResourceTypeSupported.contains(false); //Round failed
    }

    /**
     * Method called to check whether a random event has occurred this round and adds that information to a list in order
     * to all be displayed in a dialog box before the next round begins. Multiple positive random events can occur each round, only
     * a maximum of one tower can break per round
     */
    private void randomTowerEvent() {
        Random r = new Random();
        int randomEvents = 0;
        for (Tower tower : inventoryService.getMainTowerSelection()) {
            int chanceOfTowerStatIncrease = r.nextInt(0, 3); // 1/3 chance
            if (chanceOfTowerStatIncrease == 0) {
                int whichStatIncrease = r.nextInt(1, 5); // 4 stats to choose from
                String stat = "";
                switch (whichStatIncrease) {
                    case 1: // Tower Points
                        tower.gainTowerPoints(100);
                        stat = "Tower Points";
                        break;
                    case 2: // Resource Amount
                        tower.setResourceAmount(tower.getResourceAmount() + 5);
                        stat = "Resource Amount";
                        break;
                    case 3: // Reload Speed
                        tower.setReloadSpeed(tower.getReloadSpeed() - 0.5);
                        stat = "Reload Speed";
                        break;
                    case 4: // Price
                        tower.setCost(tower.getCost() + 10);
                        stat = "Cost";
                        break;
                }
                randomEventList.add("The " + stat + " of a " + tower.getResourceType() + " tower has changed!");
                randomEvents += 1;
            }

            int chanceOfTowerBreaking = r.nextInt(0, 21-tower.getRoundsUsed()); // 1/20 chance for tower to break, chance increases the more the tower is used
            if (chanceOfTowerBreaking == 0) {
                randomEventList.add("A " + tower.getResourceType() + " tower has broken! It has been removed from your inventory");
                inventoryService.removeMainTower(tower);
                randomEvents += 1;
                // "Breaks" tower by removing from inventory - Might do the "Be placed in a broken state and require a specific item to be repaired to working order" bit later on
            }

            tower.addRoundUsed(); // Tracks how many times a tower has been used
        }
        if (randomEvents > 0) {
            openRandomEventDialog();
        }
    }

    /**
     * Resets the labels for current money, current round and rounds remaining to reflect updated values and also
     * the labels associated with carts. Calls services to reset the available carts and reset shop
     */
    private void resetAndUpdateLabelsAndStats() {
        Integer currentMoney = moneyService.getCurrentBalance();
        String currentDifficulty = currentRoundService.getDifficulty();
        Integer currentRound = currentRoundService.getCurrentRound();

        if (Objects.equals(currentDifficulty, "Easy")) {
            moneyService.setNewBalance(currentMoney + 100);
            playerScoreService.addPlayerScore(50);
        } else if (Objects.equals(currentDifficulty, "Medium")) {
            moneyService.setNewBalance(currentMoney + 250);
            playerScoreService.addPlayerScore(100);
        } else if (Objects.equals(currentDifficulty, "Hard")) {
            moneyService.setNewBalance(currentMoney + 500);
            playerScoreService.addPlayerScore(150);
        }

        shopAvailabilityService.resetStore(currentRound); // reset shop
        currentRoundService.setCarts(); // reset carts
        currentRoundService.setCurrentRound(currentRound + 1); // update rounds
        remainingRounds -= 1;

        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString()); //update labels
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

        resetCartInfo(); // reset labels
        currentRoundService.setDifficulty("reset"); // reset difficulty so that previous rounds difficulty isn't stored
        cartList.setItems(null); // reset list view to remove carts
    }

    /**
     * Sets all the cart information labels to be blank
     */
    private void resetCartInfo() {
        roundDifficultyDropdown.setValue("");
        distance.setText("");
        numberCarts.setText("");
    }

    /**
     * Opens a dialog box to display each random event that has occurred within randomEventList
     */
    private void openRandomEventDialog() {
        //Tutorial 2 Extension
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("A random event has occurred");
        VBox dialogContent = new VBox(50);
        for (String event: randomEventList) {
            dialogContent.getChildren().add(new Label(event));
        }
        dialog.getDialogPane().setContent(dialogContent);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.show();
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
