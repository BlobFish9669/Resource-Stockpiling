package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
/**
 * Controller for the main.fxml window
 * @author Caleb Cooper
 */
public class MainController {

    private GameManager gameManager;

    @FXML
    public Button shopButton;
    public Button inventoryButton;
    public Label mainLabel;
    /**
     * Constructor
     * @param gameManager an instance of GameManger that is linked through the entirety of the game in order to keep it
     *                    all linked.
     */
    public MainController(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    /**
     * Initialize the window
     */
    public void initialize() {
        System.out.println("Main Page");
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
}
