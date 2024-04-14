package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
/**
 * Controller for the inventory.fxml window
 * @author Caleb Cooper
 */
public class InventoryController {

    private GameManager gameManager;

    @FXML
    public Label inventoryLabel;
    public Button backButton;
    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public InventoryController(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    /**
     * Initialize the window
     */
    public void initialize() {
        System.out.println("Inventory Page");
    }
    /**
     * Method to call when the back button is clicked
     */
    @FXML
    private void onBackButtonClicked() {
        gameManager.resetAndLaunchMainScreen();
    }
}