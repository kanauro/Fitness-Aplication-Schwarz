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

    private static final String SAVE_LOAD_SUCCESS_MESSAGE = "Success";
    private static final String SAVE_LOAD_FAILURE_MESSAGE = "Failed";
    private static final String SAVE_ERROR_NOT_FOUND_MESSAGE = "Súbor '{}' nebol nájdený";
    private static final String LOAD_ERROR_MESSAGE = "Načínie datasetu zo súboru '{}' bolo neúspešné.";

    private final ObservableList<BodyRecord> recordsList = FXCollections.observableArrayList();

    private String databaseFileName;
    private UserInfo userInfo;

    public Database(String fileName) {
        LOGGER.info("initialize()");

        this.databaseFileName = fileName;
        if (fileName == null || fileName.isEmpty()) {
            this.databaseFileName = "data.json";
        }

    }

    public String saveRecord(BodyRecord bodyRecord) {
        LOGGER.info("saveRecord({})", bodyRecord);

        try {
            userInfo.getBodyRecords().add(bodyRecord);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(databaseFileName).toFile(), userInfo);
            recordsList.add(bodyRecord);
            return SAVE_LOAD_SUCCESS_MESSAGE;
        } catch (IOException | NullPointerException e) {
            LOGGER.error(SAVE_ERROR_NOT_FOUND_MESSAGE, databaseFileName);
            return SAVE_LOAD_FAILURE_MESSAGE;
        }
    }

    public String loadUserInfo() {
        LOGGER.info("loadUserInfoFromFile()");
        try {
            String jsonString = FileUtils.readFileToString(new File(this.databaseFileName), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            userInfo = mapper.readValue(jsonString, UserInfo.class);
            recordsList.removeAll();
            recordsList.addAll(this.userInfo.getBodyRecords());
            return SAVE_LOAD_SUCCESS_MESSAGE;
        } catch (IOException e) {
            LOGGER.error(LOAD_ERROR_MESSAGE, this.databaseFileName);
            return SAVE_LOAD_FAILURE_MESSAGE;
        }
    }

}
