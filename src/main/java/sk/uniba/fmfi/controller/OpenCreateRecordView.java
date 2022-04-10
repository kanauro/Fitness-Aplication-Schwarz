package sk.uniba.fmfi.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import lombok.AllArgsConstructor;
import sk.uniba.fmfi.model.Database;
import sk.uniba.fmfi.view.CreateRecordView;

@AllArgsConstructor
public class OpenCreateRecordView implements EventHandler {

    private final Database database;

    @Override
    public void handle(Event event) {
        new CreateRecordView(this.database);
    }
}
