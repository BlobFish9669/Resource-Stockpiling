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
    }

    /**
     * Initialize the window
     */
    public void initialize() {
        // Binds the width and height of the grid to the size of the window.
        mainBorderPane.prefWidthProperty().bind(MenuWindow.getWidth());
        mainBorderPane.prefHeightProperty().bind(MenuWindow.getHeight());

        currentRoundService.setCurrentRound(1);
        int remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();

        currentMoneyLabel.setText("$" + moneyService.getCurrentBalance().toString());
        currentRoundLabel.setText(currentRoundService.getCurrentRound().toString());
        roundsRemainingLabel.setText(Integer.toString(remainingRounds));

        //generate carts
        roundDifficultyDropdown.getItems().addAll("Easy", "Medium", "Hard");
        roundDifficultyDropdown.setOnAction(event -> {
            currentRoundService.setDifficulty(roundDifficultyDropdown.getValue());
            distance.setText(currentRoundService.getDistance() + " Metres");
            numberCarts.setText(currentRoundService.getNumCarts().toString());
            cartList.setCellFactory(new CartCellFactory());
            cartList.setItems(FXCollections.observableArrayList(currentRoundService.getCarts()));
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
    private void onPlayRoundButtonClicked() { System.out.println("Play Round"); }
}
