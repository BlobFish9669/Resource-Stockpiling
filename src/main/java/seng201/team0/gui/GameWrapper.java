package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.GameManager;

import java.io.IOException;

/**
 * Contains methods to call in order to manage the graphical interface elements and transitions between the different screens. - Used Tut2 for reference
 * @author Caleb Cooper
 */
public class GameWrapper {
    @FXML
    private Pane pane;
    private Stage stage;

    /**
     * Initializes the interface and sets up the game environment on the provided stage
     * @param stage The primary stage on which the application's elements are displayed
     */
    public void init(Stage stage) {
        this.stage = stage;
        new GameManager(this::launchMenuScreen, this::launchMainScreen, this::launchShopScreen, this::launchInventoryScreen, this::launchSellScreen, this::launchEndScreen, this::clearPane);
    }

    /**
     * Method to open the gui with the fxml content specified in menu.fxml, uses try block to catch IOException errors
     * @param gameManager The current fxml stage, handled by the GameManager class
     */
    public void launchMenuScreen(GameManager gameManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new MenuController(gameManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method called in order to clear all elements from the pane
     */
    public void clearPane() {
        pane.getChildren().clear();
    }

    /**
     * Method to open the gui with the fxml content specified in main.fxml, uses try block to catch IOException errors
     * @param gameManager The current fxml stage, handled by the GameManager class
     */
    public void launchMainScreen(GameManager gameManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainController(gameManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Main");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to open the gui with the fxml content specified in shop.fxml, uses try block to catch IOException errors
     * @param gameManager The current fxml stage, handled by the GameManager class
     */
    public void launchShopScreen(GameManager gameManager) {
        try {
            FXMLLoader shopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            shopScreenLoader.setControllerFactory(param -> new ShopController(gameManager));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to open the gui with the fxml content specified in inventory.fxml, uses try block to catch IOException errors
     * @param gameManager The current fxml stage, handled by the GameManager class
     */
    public void launchInventoryScreen(GameManager gameManager) {
        try {
            FXMLLoader inventoryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory.fxml"));
            inventoryScreenLoader.setControllerFactory(param -> new InventoryController(gameManager));
            Parent setupParent  = inventoryScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to open the gui with the fxml content specified in sell_screen.fxml, uses try block to catch IOException errors
     * @param gameManager The current fxml stage, handled by the GameManager class
     */
    public void launchSellScreen(GameManager gameManager) {
        try {
            FXMLLoader sellScreenLoader = new FXMLLoader(getClass().getResource("/fxml/sell_screen.fxml"));
            sellScreenLoader.setControllerFactory(param -> new SellScreenController(gameManager));
            Parent setupParent  = sellScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Sell");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to open the gui with the fxml content specified in end_screen.fxml, uses try block to catch IOException errors
     * @param gameManager The current fxml stage, handled by the GameManager class
     */
    public void launchEndScreen(GameManager gameManager) {
        try {
            FXMLLoader endScreenLoader = new FXMLLoader(getClass().getResource("/fxml/end_screen.fxml"));
            endScreenLoader.setControllerFactory(param -> new EndScreenController(gameManager));
            Parent setupParent  = endScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
