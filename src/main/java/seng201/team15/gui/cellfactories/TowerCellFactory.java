package seng201.team15.gui.cellfactories;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team15.models.Tower;

/**
 * Custom cell factory for Towers to use in ListView
 * @author seng201 teaching team, Caleb Cooper
 */
public class TowerCellFactory implements Callback<ListView<Tower>, ListCell<Tower>> {
    @Override
    public ListCell<Tower> call(ListView<Tower> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Tower tower, boolean empty) {
                super.updateItem(tower, empty);
                if (empty || tower == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(5);
                    VBox vBox = new VBox(5);

                    Label nameLabel = new Label(tower.getResourceType());
                    nameLabel.setFont(new Font(20));
                    ImageView imageView;

                    if (tower.getBrokenStatus()) { // Tower is broken!
                        imageView = new ImageView("/images/broken-deposit-" + tower.getResourceType().toLowerCase() + ".png");
                        Label statusLabel = new Label("BROKEN!");

                        statusLabel.setFont(new Font(20));
                        vBox.getChildren().addAll(
                                nameLabel,
                                statusLabel
                        );
                    } else { // Not Broken
                        imageView = new ImageView("/images/deposit-" + tower.getResourceType().toLowerCase() + ".png");
                        vBox.getChildren().add(
                                nameLabel
                        );
                    }

                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);

                    vBox.getChildren().addAll(
                            new Label(String.format("Resource Amount: %s",tower.getResourceAmount())),
                            new Label(String.format("Reload Speed: %s", tower.getReloadSpeed())),
                            new Label(String.format("Level: %s", tower.getLevel())),
                            new Label(String.format("Sell-Back Price: $%s", tower.getSellPrice()))
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

