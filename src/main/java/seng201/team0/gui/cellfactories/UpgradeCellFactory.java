package seng201.team0.gui.cellfactories;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import seng201.team0.models.Upgrade;

/**
 * Custom cell factory for Upgrades to use in ListView
 * @author seng201 teaching team, Caleb Cooper
 */
public class UpgradeCellFactory implements Callback<ListView<Upgrade>, ListCell<Upgrade>> {
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
                    // https://creazilla.com/nodes/3151369-cog-clipart
                    ImageView imageView = new ImageView("/images/cog.png");
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    VBox vBox = new VBox(5);
                    Label nameLabel = new Label(upgrade.getUpgradeTitle());
                    nameLabel.setFont(new Font(20));
                    vBox.getChildren().addAll(
                            nameLabel,
                            new Label(String.format("Sell-Back Price: %s",upgrade.getSellPrice()))
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
