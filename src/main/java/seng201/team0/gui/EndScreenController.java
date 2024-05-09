package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
import seng201.team0.services.*;

import java.util.Objects;

/**
 * Controller for the end_screen.fxml window
 * @author Caleb Cooper
 */
public class EndScreenController {


    private final GameManager gameManager;
    private final NameInputService nameService;
    private final RoundsSelectionService roundsService;
    private final MoneyBalanceService moneyService;
    private final CurrentRoundService currentRoundService;
    private final InventoryService inventoryService;
    private final DifficultySelectionService difficultyService;
    private final ShopAvailabilityService shopAvailabilityService;
    private final PlayerScoreService playerScoreService;

    @FXML
    public Label title;
    public Label gameMessage;
    public Label roundsChosen;
    public Label roundsChosenLabel;
    public Label roundsCompleted;
    public Label roundsCompletedLabel;
    public Label moneyGained;
    public Label moneyGainedLabel;
    public Label score;
    public Label scoreLabel;
    public Button exitButton;
    public Button restartGameButton;


    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public EndScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
        this.roundsService = gameManager.getRoundsService();
        this.moneyService = gameManager.getMoneyService();
        this.currentRoundService = gameManager.getCurrentRoundService();
        this.inventoryService = gameManager.getInventoryService();
        this.nameService = gameManager.getNameService();
        this.difficultyService = gameManager.getDifficultyService();
        this.shopAvailabilityService = gameManager.getShopAvailabilityService();
        this.playerScoreService = gameManager.getPlayerScoreService();
    }

    /**
     * Initialize the window, sets up the buttons to change colour when hovered over, checks whether the game was won or lost and
     * displays a message accordingly, displays the users game stats
     */
    public void initialize() {
        restartGameButton.setOnMouseEntered(event -> restartGameButton.setStyle("-fx-background-color: #999999"));
        restartGameButton.setOnMouseExited(event -> restartGameButton.setStyle(""));

        exitButton.setOnMouseEntered(event -> exitButton.setStyle("-fx-background-color: #999999"));
        exitButton.setOnMouseExited(event -> exitButton.setStyle(""));

        if (currentRoundService.getGameSuccess()) {
            gameMessage.setText("Congratulations "+ nameService.getCurrentName() + ", you Won!");
        } else {
            gameMessage.setText(nameService.getCurrentName() + ", you Lost!");
        }

        String difficulty = difficultyService.getDifficultySelection();
        Integer startBalance;

        if (Objects.equals(difficulty, "Easy")) {
            startBalance = 100;
        } else if (Objects.equals(difficulty, "Medium")) {
            startBalance = 75;
        } else if (Objects.equals(difficulty, "Hard")) {
            startBalance = 50;
        } else {
            startBalance = 25;
        }


        Integer roundsChosen = roundsService.getRoundsSelection();
        Integer remainingRounds = roundsService.getRoundsSelection() - currentRoundService.getCurrentRound();
        Integer currentBalance = moneyService.getCurrentBalance();

        roundsChosenLabel.setText(roundsService.getRoundsSelection().toString());
        roundsCompletedLabel.setText(String.valueOf(roundsChosen - remainingRounds));
        moneyGainedLabel.setText(String.valueOf((currentBalance - startBalance)));
        scoreLabel.setText(String.valueOf(playerScoreService.getPlayerScore()));

    }

    /**
     * Method called if the restart game button is clicked, resets all the service classes so that a fresh game can be played.
     */
    @FXML
    public void onRestartGameButtonClicked() {
        currentRoundService.setDifficulty("reset");
        currentRoundService.setCurrentRound(1);
        inventoryService.resetInventory();
        shopAvailabilityService.resetStore(1);
        currentRoundService.setCarts();
        gameManager.resetAndLaunchMenuScreen();
    }

    /**
     * Method called when the exit button is clicked, closes the game window and exits
     */
    @FXML
    public void onExitButtonClicked() {
        System.exit(0);
    }
}