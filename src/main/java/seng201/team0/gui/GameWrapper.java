package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.GameManager;

import java.io.IOException;

public class GameWrapper {
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;
        new GameManager(this::launchMenuScreen, this::launchMainScreen, this::launchShopScreen, this::launchInventoryScreen, this::clearPane);
    }

    public void launchMenuScreen(GameManager rocketManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new MenuController(rocketManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().clear();
    }

    public void launchMainScreen(GameManager rocketManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainController(rocketManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Main");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void launchShopScreen(GameManager rocketManager) {
        try {
            FXMLLoader shopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            shopScreenLoader.setControllerFactory(param -> new MainController(rocketManager));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchInventoryScreen(GameManager rocketManager) {
        try {
            FXMLLoader inventoryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory.fxml"));
            inventoryScreenLoader.setControllerFactory(param -> new MainController(rocketManager));
            Parent setupParent  = inventoryScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
