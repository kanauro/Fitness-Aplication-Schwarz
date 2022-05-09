import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.model.Database;
import sk.uniba.fmfi.model.UserInfo;

import java.io.IOException;
import java.nio.file.Paths;


class DatabaseLayerTests {

    private static final String DATABASE_JSON_FILE_OK = "data_test.json";
    private static final String DATABASE_JSON_FILE_WRONG = "123456";

    private UserInfo userInfo;

    @BeforeEach
    void setUp() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        database.loadUserInfo();
        this.userInfo = database.getUserInfo();
    }

    @AfterEach
    void cleanUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(DATABASE_JSON_FILE_OK).toFile(), userInfo);
    }

    @SneakyThrows
    @Test
    void useDefaultDatabaseFileTest() {
        Database database = new Database(null);
        Assertions.assertEquals("data.json", database.getDatabaseFileName());
    }

    @SneakyThrows
    @Test
    void overrideDefaultDatabaseFileTest() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        Assertions.assertEquals(DATABASE_JSON_FILE_OK, database.getDatabaseFileName());
    }

    @SneakyThrows
    @Test
    void loadUserInfoOkTest() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        Assertions.assertEquals("Success", database.loadUserInfo());
    }

    @SneakyThrows
    @Test
    void loadUserInfoCountTest() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        database.loadUserInfo();

        Assertions.assertEquals(7, database.getRecordsList().size());
    }

    @SneakyThrows
    @Test
    void loadUserInfoFailedTest() {
        Database database = new Database(DATABASE_JSON_FILE_WRONG);
        Assertions.assertEquals("Failed", database.loadUserInfo());
    }

    @SneakyThrows
    @Test
    void saveUserInfoOkTest() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        database.loadUserInfo();

        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100, 190, 30, 41, 90,
                90,10,10,10,10);

        Assertions.assertEquals("Success", database.saveRecord(bodyRecord));
    }

    @SneakyThrows
    @Test
    void saveUserInfoCountOkTest() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        database.loadUserInfo();

        int beforeSize = database.getRecordsList().size();

        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100, 190, 30, 41, 90,
                90,10,10,10,10);

        database.saveRecord(bodyRecord);

        Assertions.assertEquals(database.getRecordsList().size(), beforeSize + 1);
    }

    @SneakyThrows
    @Test
    void saveUserInfoRecordOkTest() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        database.loadUserInfo();

        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100, 190, 30, 41, 90,
                90,10,10,10,10);

        database.saveRecord(bodyRecord);

        Assertions.assertEquals(bodyRecord, database.getRecordsList().get(database.getRecordsList().size() - 1));
    }

    @SneakyThrows
    @Test
    void saveUserInfoFailedTest() {
        Database database = new Database(DATABASE_JSON_FILE_WRONG);
        database.loadUserInfo();

        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100, 190, 30, 41, 90,
                90,10,10,10,10);

        Assertions.assertEquals("Failed", database.saveRecord(bodyRecord));
    }

    @SneakyThrows
    @Test
    void saveUserInfoChangedFileFailedTest() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        database.loadUserInfo();

        database.setDatabaseFileName("data_test_failure.csv");

        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100, 190, 30, 41, 90,
                90,10,10,10,10);

        Assertions.assertEquals("Failed", database.saveRecord(bodyRecord));
    }
}