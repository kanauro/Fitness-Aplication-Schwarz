package sk.uniba.fmfi.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import sk.uniba.fmfi.model.UserInfo;

@AllArgsConstructor
public class ShowGraphEventHandler implements EventHandler {

    private final String type;
    private final String chartTitle;
    private final UserInfo userInfo;
    LineChart<Number, Number> lineChart;
    private final XYChart.Series series;
    TableView userInfoTableView;

    private final StatisticsDataController statisticsDataController = new StatisticsDataController();

    @SneakyThrows
    @Override
    public void handle(Event event) {
        lineChart.setTitle(chartTitle);
        series.setName(chartTitle);
        series.getData().removeAll(series.getData());
        series.getData().addAll(statisticsDataController.getDataByType(this.type, this.userInfo));

        userInfoTableView.getItems().clear();
        userInfoTableView.getItems().add(statisticsDataController.getUserStatisticsByType(this.type, this.userInfo));
    }
}
