package seng201.team15.gui.cellfactories;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import seng201.team15.models.Upgrade;

/**
 * Custom cell factory for Upgrades available in the shop to use in ListView
 * @author seng201 teaching team, Caleb Cooper
 */
public class ShopUpgradeCellFactory implements Callback<ListView<Upgrade>, ListCell<Upgrade>> {
    @Override
    public ListCell<Upgrade> call(ListView<Upgrade> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Upgrade upgrade, boolean empty) {
                super.updateItem(upgrade, empty);
                if (empty || upgrade == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(5);
                    ImageView imageView = new ImageView("/images/cog.png");
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(75);
                    imageView.setFitHeight(75);
                    VBox vBox = new VBox(10);
                    Label nameLabel = new Label(upgrade.getUpgradeTitle());
                    nameLabel.setFont(new Font(15));
                    vBox.getChildren().addAll(
                            nameLabel,
                            new Label(String.format("Cost: %s",upgrade.getCost()))
                    );
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
