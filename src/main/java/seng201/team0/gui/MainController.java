package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;

public class MainController {

    private GameManager gameManager;

    @FXML
    public Button shopButton;
    public Button inventoryButton;
    public Label mainLabel;

    public MainController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        System.out.println("Main Page");
    }

    @FXML
    private void onShopButtonClicked() {
        gameManager.resetAndOpenShopScreen();
    }

    @FXML
    private void onInventoryButtonClicked() {
        gameManager.resetAndOpenInventoryScreen();
    }
}
