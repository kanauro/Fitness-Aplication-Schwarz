package sk.uniba.fmfi.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sk.uniba.fmfi.controller.OpenCreateRecordView;
import sk.uniba.fmfi.controller.OpenStatisticsView;
import sk.uniba.fmfi.model.Database;
import sk.uniba.fmfi.model.BodyRecord;

public class FitnessApplicationView extends Application {

    private static final String IMAGES_PATH = "file:src/main/resources/images/";
    private static final String TEXT_FONT = "Tahoma";

	public static void main(String[] args) {
		launch(args);
	}
	
    public void start(Stage primaryStage) {
        Database database = new Database(null);
        database.loadUserInfo();

		primaryStage.setTitle("Fitness aplikácia");
		primaryStage.getIcons().add(new Image(IMAGES_PATH + "application-logo.png"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Fitness aplikácia");
        scenetitle.setFont(Font.font(TEXT_FONT, FontWeight.NORMAL, 25));
        grid.add(scenetitle, 0, 0, 2, 1);

        final Separator firstSeparator = new Separator();
        grid.add(firstSeparator, 0, 1, 2, 1);

        Image createRecordImage = new Image(IMAGES_PATH + "create-record.png");
        Button createRecordButton = new Button("Vytvor záznam");
        createRecordButton.setGraphic(new ImageView(createRecordImage));
        createRecordButton.setMinWidth(250);
        createRecordButton.setFont(Font.font(TEXT_FONT, FontWeight.BOLD, 15));
        createRecordButton.setOnAction(new OpenCreateRecordView(database));

        Image showStatisticsImage = new Image(IMAGES_PATH + "show-statistics.png");
        Button showStatisticsButton = new Button("Štatistický prehľad");
        showStatisticsButton.setGraphic(new ImageView(showStatisticsImage));
        showStatisticsButton.setMinWidth(250);
        showStatisticsButton.setFont(Font.font(TEXT_FONT, FontWeight.BOLD, 15));
        showStatisticsButton.setOnAction(new OpenStatisticsView(database));

        HBox boxFirst = new HBox();
        boxFirst.setSpacing(25);
        boxFirst.setAlignment(Pos.CENTER);
        boxFirst.getChildren().addAll(createRecordButton, showStatisticsButton);
        grid.add(boxFirst, 0, 2, 2, 1);

        final Separator secondSeparator = new Separator();
        grid.add(secondSeparator, 0, 3, 2, 1);

        Text recordTableTitle = new Text("Uložené záznamy");
        recordTableTitle.setFont(Font.font(TEXT_FONT, FontWeight.NORMAL, 18));
        grid.add(recordTableTitle, 1, 5);

        TableView<BodyRecord> recordTableView = new TableView<>();

        TableColumn<BodyRecord,Float> dateColumn = new TableColumn<>("Dátum");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.prefWidthProperty().bind(recordTableView.widthProperty().multiply(0.17));
        dateColumn.setResizable(false);

        TableColumn<BodyRecord,Float> weightColumn = new TableColumn<>("Váha");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightColumn.prefWidthProperty().bind(recordTableView.widthProperty().multiply(0.15));
        weightColumn.setResizable(false);

        TableColumn<BodyRecord,Float> armColumn = new TableColumn<>("Obvod bicepsu");
        armColumn.setCellValueFactory(new PropertyValueFactory<>("arm"));
        armColumn.prefWidthProperty().bind(recordTableView.widthProperty().multiply(0.17));
        armColumn.setResizable(false);

        TableColumn<BodyRecord,Float> neckColumn = new TableColumn<>("Obvod krku");
        neckColumn.setCellValueFactory(new PropertyValueFactory<>("neck"));
        neckColumn.prefWidthProperty().bind(recordTableView.widthProperty().multiply(0.17));
        neckColumn.setResizable(false);

        TableColumn<BodyRecord,Float> waistColumn = new TableColumn<>("Pás");
        waistColumn.setCellValueFactory(new PropertyValueFactory<>("waist"));
        waistColumn.prefWidthProperty().bind(recordTableView.widthProperty().multiply(0.17));
        waistColumn.setResizable(false);

        TableColumn<BodyRecord,Float> hipColumn = new TableColumn<>("Boky");
        hipColumn.setCellValueFactory(new PropertyValueFactory<>("hip"));
        hipColumn.prefWidthProperty().bind(recordTableView.widthProperty().multiply(0.17));
        hipColumn.setResizable(false);

        recordTableView.getColumns().addAll(dateColumn, weightColumn, armColumn, neckColumn, waistColumn, hipColumn);
        recordTableView.setItems(database.getRecordsList());
        recordTableView.setMinWidth(600);
        recordTableView.setMaxHeight(180);
        grid.add(recordTableView, 1, 6);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
	}
}