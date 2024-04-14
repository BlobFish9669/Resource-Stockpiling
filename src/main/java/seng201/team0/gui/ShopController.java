package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
/**
 * Controller for the shop.fxml window
 * @author Caleb Cooper
 */
public class ShopController {

    private GameManager gameManager;

    @FXML
    public Label shopLabel;
    public Button backButton;
    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public ShopController(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    /**
     * Initialize the window
     */
    public void initialize() {
        System.out.println("Shop Page");
    }
    /**
     * Method to call when the back button is clicked
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndLaunchMainScreen();
    }
}