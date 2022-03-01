package sk.uniba.fmfi.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonDataService {

    private final ObservableList<Record> recordsList = FXCollections.observableArrayList();

    public ObservableList<Record> getRecords() {
        return recordsList;
    }

}
