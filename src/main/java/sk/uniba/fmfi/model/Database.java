package sk.uniba.fmfi.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

@Data
public class Database {

    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);
    private static final String DATABASE_JSON_FILE = "data.json";

    private final ObservableList<BodyRecord> recordsList = FXCollections.observableArrayList();

    private Dataset dataset;

    public ObservableList<BodyRecord> getRecords() throws IOException {
        LOGGER.info("getRecords()");
        if (recordsList.isEmpty())
            initialize();
        return recordsList;
    }

    public void saveRecord(BodyRecord bodyRecord) throws IOException {
        LOGGER.info("saveRecord({})", bodyRecord);
        dataset.getBodyRecords().add(bodyRecord);
        saveDataToFile(DATABASE_JSON_FILE, dataset);
        recordsList.add(bodyRecord);
    }

    private void initialize() throws IOException {
        LOGGER.info("initialize()");
        this.dataset = loadDatasetFromFile(DATABASE_JSON_FILE);
        recordsList.removeAll();
        recordsList.addAll(this.dataset.getBodyRecords());
    }

    private Dataset loadDatasetFromFile(String fileName) throws IOException {
        LOGGER.info("loadDatasetFromFile({})", fileName);
        String jsonString = FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, Dataset.class);
    }

    private void saveDataToFile(String fileName, Dataset dataset) throws IOException {
        LOGGER.info("setDataset({}, {})", fileName, dataset);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(fileName).toFile(), dataset);
    }
}
