package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import seng201.team0.GameManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.services.*;

/**
 * Controller for the end_screen.fxml window
 * @author Caleb Cooper
 */
public class EndScreenController {


    private GameManager gameManager;
    private NameInputService nameService;
    private RoundsSelectionService roundsService;
    private MoneyBalanceService moneyService;
    private CurrentRoundService currentRoundService;
    private InventoryService inventoryService;
    private DifficultySelectionService difficultyService;

    @FXML
    public BorderPane endScreenBorderPane;
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
    }
    /**
     * Initialize the window
     */
    public void initialize() {
        // Binds the width and height of the grid to the size of the window.
        endScreenBorderPane.prefWidthProperty().bind(MenuWindow.getWidth());
        endScreenBorderPane.prefHeightProperty().bind(MenuWindow.getHeight());

        if (currentRoundService.getGameSuccess()) {
            gameMessage.setText("Congratulations "+ nameService.getCurrentName() + ", you Won!");
        } else {
            gameMessage.setText(nameService.getCurrentName() + ", you Lost!");
        }

        String difficulty = difficultyService.getDifficultySelection();
        Integer startBalance;

        if (difficulty == "Easy") {
            startBalance = 100;
        } else if (difficulty == "Medium") {
            startBalance = 75;
        } else if (difficulty == "Hard") {
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

    }

    @FXML
    public void onExitButtonClicked() {
        System.exit(0);
    }
}