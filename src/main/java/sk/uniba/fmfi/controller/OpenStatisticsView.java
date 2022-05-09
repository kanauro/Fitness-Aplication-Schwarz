package sk.uniba.fmfi.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import lombok.AllArgsConstructor;
import sk.uniba.fmfi.model.Database;
import sk.uniba.fmfi.view.StatisticsView;

@AllArgsConstructor
public class OpenStatisticsView implements EventHandler {

    private final Database database;

    @Override
    public void handle(Event event) {
        new StatisticsView(this.database);
    }
}
