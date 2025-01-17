package seng201.team15.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class starts the javaFX application window
 * @author seng201 teaching team
 */
public class MenuWindow extends Application {
    /**
     * Opens the gui with the fxml content specified in resources/fxml/fx_wrapper.fxml
     * @param primaryStage The current fxml stage, handled by javaFX Application class
     * @throws IOException if there is an issue loading fxml file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/fx_wrapper.fxml"));
        Parent root = baseLoader.load();
        GameWrapper gameWrapper = baseLoader.getController();
        primaryStage.setTitle("Group 15 Game");
        Scene scene = new Scene(root, 1200, 675);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Locks screen size https://stackoverflow.com/questions/34809447/disable-maximize-button-and-resizing-window-in-javafx#:~:text=You%20can%20do%20it%20with,remove%20window%20buttons%20with%20stage.
        primaryStage.show();
        gameWrapper.init(primaryStage);
    }

    /**
     * Launches the FXML application, this must be called from another class (in this cass App.java) otherwise JavaFX
     * errors out and does not run
     * @param args command line arguments
     */
    public static void launchWrapper(String[] args) {
        launch(args);
    }

}
