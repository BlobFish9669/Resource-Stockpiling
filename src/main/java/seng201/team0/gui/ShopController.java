package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameManager;

public class ShopController {

    private GameManager gameManager;

    @FXML
    public Label shopLabel;
    public Button backButton;

    public ShopController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        System.out.println("Shop Page");
    }

    @FXML
    private void onBackButtonClicked() {
        System.out.println("Back button clicked");
    }
}