package sk.uniba.fmfi.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sk.uniba.fmfi.controller.ShowGraphEventHandler;
import sk.uniba.fmfi.model.Database;

public class StatisticsView extends Stage {

    private static final String IMAGES_PATH = "file:src/main/resources/images/";

    public StatisticsView(Database database) {
        this.setTitle("Štatistický prehľad");
        this.getIcons().add(new Image(IMAGES_PATH + "show-statistics.png"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        TableView userInfoTableView = new TableView<>();
        userInfoTableView.setEditable(true);
        TableColumn minimumColumn = new TableColumn<>("Minimum");
        minimumColumn.setCellValueFactory(new PropertyValueFactory<>("minimum"));
        TableColumn maximumColumn = new TableColumn<>("Maximum");
        maximumColumn.setCellValueFactory(new PropertyValueFactory<>("maximum"));
        TableColumn averageColumn = new TableColumn<>("Priemer");
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));

        userInfoTableView.getColumns().addAll(minimumColumn, averageColumn, maximumColumn);

        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Meranie");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(150);

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Stock Monitoring, 2010");
        grid.add(lineChart, 1,2,4,2);
        XYChart.Series series = new XYChart.Series<>();
        lineChart.getData().add(series);
        this.addEventHandler(WindowEvent.WINDOW_SHOWN, new ShowGraphEventHandler("fat", "Telesný tuk [%]", database.getUserInfo(), lineChart, series, userInfoTableView));

        Button showFatGraphButton = new Button("Tuk");
        showFatGraphButton.setOnAction(new ShowGraphEventHandler("fat", "Telesný tuk [%]", database.getUserInfo(), lineChart, series, userInfoTableView));
        grid.add(showFatGraphButton, 1, 1);

        Button showWeightGraphButton = new Button("Váha");
        showWeightGraphButton.setOnAction(new ShowGraphEventHandler("weight", "Váha [kg]", database.getUserInfo(), lineChart, series, userInfoTableView));
        grid.add(showWeightGraphButton, 2, 1);

        Button showMuscleGraphButton = new Button("Svaly");
        showMuscleGraphButton.setOnAction(new ShowGraphEventHandler("muscle", "Svaly [%]", database.getUserInfo(), lineChart, series, userInfoTableView));
        grid.add(showMuscleGraphButton, 3, 1);

        Button showHipGraphButton = new Button("Boky");
        showHipGraphButton.setOnAction(new ShowGraphEventHandler("hip", "Boky [cm]", database.getUserInfo(), lineChart, series, userInfoTableView));
        grid.add(showHipGraphButton, 4, 1);

        userInfoTableView.setMaxHeight(50);
        userInfoTableView.setMaxWidth(242);
        grid.add(userInfoTableView,5,3);

        Scene scene = new Scene(grid);
        this.setScene(scene);
        this.sizeToScene();
        this.setResizable(false);
        this.show();
    }

}
