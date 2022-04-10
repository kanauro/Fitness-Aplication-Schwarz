package sk.uniba.fmfi.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sk.uniba.fmfi.controller.CreateRecordEventHandler;
import sk.uniba.fmfi.model.Database;

public class CreateRecordView extends Stage {

    private static final String IMAGES_PATH = "file:src/main/resources/images/";

    public CreateRecordView(Database database) {
        this.setTitle("Pridanie nového záznamu");
        this.getIcons().add(new Image(IMAGES_PATH + "create-record.png"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        Label weightLabel = new Label("Váha:");
        grid.add(weightLabel, 0, 1);
        Label heightLabel = new Label("Výška:");
        grid.add(heightLabel, 0, 2);
        Label armLabel = new Label("Obvod bicepsu:");
        grid.add(armLabel, 0, 3);
        Label neckLabel = new Label("Obvod krku:");
        grid.add(neckLabel, 0, 4);
        Label waistLabel = new Label("Obvod pásu:");
        grid.add(waistLabel, 0, 5);
        Label hipLabel = new Label("Boky:");
        grid.add(hipLabel, 0, 6);

        TextField weightField = new TextField();
        weightField.setPromptText("Zadajte vašu aktuálnu váhu");
        grid.add(weightField, 1, 1);
        TextField heightField = new TextField();
        heightField.setPromptText("Zadajte vašu aktuálnu výšku");
        grid.add(heightField, 1, 2);
        TextField armField = new TextField();
        armField.setPromptText("Zadajte obvod bicepsu");
        grid.add(armField, 1, 3);
        TextField neckField = new TextField();
        neckField.setPromptText("Zadajte obvod krku");
        grid.add(neckField, 1, 4);
        TextField waistField = new TextField();
        waistField.setPromptText("Zadajte obvod pásu");
        grid.add(waistField, 1, 5);
        TextField hipField = new TextField();
        hipField.setPromptText("Zadajte obvod bokov");
        grid.add(hipField, 1, 6);

        Button createRecordButton = new Button("Vytvoriť záznam");
        grid.add(createRecordButton, 1, 7);

        Scene scene = new Scene(grid);
        this.setScene(scene);
        this.sizeToScene();
        this.setResizable(false);
        this.show();

        createRecordButton.setOnAction(new CreateRecordEventHandler(this, database, weightField, heightField, armField, neckField, waistField, hipField));
    }

}
