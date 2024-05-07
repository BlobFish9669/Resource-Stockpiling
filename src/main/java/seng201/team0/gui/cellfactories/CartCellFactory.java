package seng201.team0.gui.cellfactories;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

/**
 * Custom cell factory for Rockets to use in ListView
 * @author seng201 teaching team
 */
public class CartCellFactory implements Callback<ListView<Cart>, ListCell<Cart>> {
    @Override
    public ListCell<Cart> call(ListView<Cart> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Cart cart, boolean empty) {
                super.updateItem(cart, empty);
                if (empty || cart == null) {
                    setGraphic(null);
                } else {
                    // Create a new HBox to store the elements to be displayed
                    HBox hBox = new HBox(5);
                    // Add a 'hero' image of a tower
                    //made the tower png
                    ImageView imageView = new ImageView("/images/minecart.png");
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    // Add an inner VBox to hold rocket info
                    VBox vBox = new VBox(5);
                    // Add rocket name label (separated from the others, so we can also change the font size)
                    Label nameLabel = new Label(cart.getResourceType());
                    nameLabel.setFont(new Font(20));
                    // Add the fuel and cleanliness labels to the VBox
                    vBox.getChildren().addAll(
                            nameLabel,
                            new Label(String.format("Size: %s", cart.getSize())),
                            new Label(String.format("Speed: %s", cart.getSpeed()))
                    );
                    // Add the image and the VBox to the HBox
                    hBox.getChildren().addAll(
                            imageView,
                            vBox
                    );
                    setGraphic(hBox);
                }
            }
        };
    }
}