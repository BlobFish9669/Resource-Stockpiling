package seng201.team0.gui;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import seng201.team0.GameManager;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;
import seng201.team0.services.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private InventoryService inventoryService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private ShopAvailabilityService shopAvailabilityService;


    @FXML
    public BorderPane mainBorderPane;
    public Label mainLabel;

    public Label currentMoney;
    public Label currentMoneyLabel;
    public Label currentRound;
    public Label currentRoundLabel;
    public Label roundsRemaining;
    public Label roundsRemainingLabel;

    public Label roundDifficultyLabel;
    public Label distanceLabel;
    public Label distance;
    public Label numberCartsLabel;
    public Label numberCarts;
    public ChoiceBox<String> roundDifficultyDropdown;
    public ListView<Cart> cartList;

    public Button playRound;
    public Button shopButton;
    public Button inventoryButton;


    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game
     */
    public MainController(GameManager gameManager) {
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
        mainBorderPane.prefWidthProperty().bind(MenuWindow.getWidth());
        mainBorderPane.prefHeightProperty().bind(MenuWindow.getHeight());

        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();

        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

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
    @FXML
    private void onPlayRoundButtonClicked() {
        if (roundDifficultyDropdown.getValue() != null && roundDifficultyDropdown.getValue() != "") {
            currentRoundService.storeCarts(currentRoundService.getPotentialCarts());
            startRound();
        } else {
            System.out.println("Error - No difficulty selected");
        }
    }

    private void startRound() {
        if (checkResources()) {
            //Round not failed yet - check distance
            for (Cart cart: currentRoundService.getCarts()) {
                if (cart.getFilledSize() != cart.getSize()) {
                    boolean cartFilled = false;
                    for (Tower tower : inventoryService.getMainTowerSelection()) {
                        if (tower.getResourceType() == cart.getResourceType()) {
                            double timeToFill = (double) (cart.getSize() - cart.getFilledSize()) / tower.getFillRate(); // = time in mins
                            //System.out.println(timeToFill);
                            double distanceRemaining = (cart.getSpeed()*((double) 1000/60)) * timeToFill; // Turn to m/min to be able to compare
                            //System.out.println(distanceRemaining);
                            if (distanceRemaining <= currentRoundService.getDistance()) {
                                cart.fill((int) (tower.getFillRate() * timeToFill));
                                cartFilled = true;
                                break;
                            }
                        }
                    }

                    if (!cartFilled) {
                        gameOver();
                        System.out.print("Error - not filled in time");
                        break;
                    }
                }
            }
            //Round completed
            finishRound();

        } else {
            gameOver();
            System.out.println("Error - resource");
        }
    }

    private void gameOver() { gameManager.resetAndOpenEndScreen(); }

    private void finishRound() {
        Integer currentMoney = moneyService.getCurrentBalance();
        String currentDifficulty = currentRoundService.getDifficulty();
        Integer currentRound = currentRoundService.getCurrentRound();
        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();

        if (remainingRounds == 0) {
            currentRoundService.setGameSuccess(true);
            gameOver();
        } else {
            if (currentDifficulty == "Easy") {
                moneyService.setNewBalance(currentMoney + 100);
            } else if (currentDifficulty == "Medium") {
                moneyService.setNewBalance(currentMoney + 250);
            } else if (currentDifficulty == "Hard") {
                moneyService.setNewBalance(currentMoney + 500);
            }

            shopAvailabilityService.resetStore(); // reset shop
            currentRoundService.setCarts(); // reset carts
            currentRoundService.setCurrentRound(currentRound + 1); // update rounds
            remainingRounds -= 1;

            currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString()); //update labels
            currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
            roundsRemainingLabel.setText(Integer.toString(remainingRounds));

            resetCartInfo(); // reset labels
            cartList.setItems(null); // reset list view to remove carts

        }
    }

    private void resetCartInfo() {
        roundDifficultyDropdown.setValue("");
        distance.setText("");
        numberCarts.setText("");
    }

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
        System.out.println(cartResourceTypeSupported);
        if (cartResourceTypeSupported.contains(false)) {
            return false; //Round failed
        }
        return true;
    }
}
