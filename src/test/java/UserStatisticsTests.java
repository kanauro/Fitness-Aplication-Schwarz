import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import sk.uniba.fmfi.controller.StatisticsDataController;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.model.Database;
import sk.uniba.fmfi.model.UserInfo;
import sk.uniba.fmfi.model.UserStatisticsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

class UserStatisticsTests {
    UserInfo userInfo;
    private static final String DATABASE_JSON_FILE_OK = "data_test.json";


    private final StatisticsDataController statisticsDataController = new StatisticsDataController();

    @BeforeEach
    void setUp() {
        Database database = new Database(DATABASE_JSON_FILE_OK);
        database.loadUserInfo();
        this.userInfo = database.getUserInfo();
    }

    @Test
    void checkFatStatOkTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(1.5f,40.0f,22.17f);

        Assertions.assertEquals(statisticsDataController.getUserStatisticsByType("fat", this.userInfo), userStatisticsInfo);
    }

    @Test
    void checkWeightStatOkTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(1.0f,100.0f,70.17f);

        Assertions.assertEquals(statisticsDataController.getUserStatisticsByType("weight", this.userInfo), userStatisticsInfo);
    }

    @Test
    void checkLeanMassStatOkTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(60.0f,60.0f,60.0f);

        Assertions.assertEquals(statisticsDataController.getUserStatisticsByType("leanMass", this.userInfo), userStatisticsInfo);
    }

    @Test
    void checkHipStatOkTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(6.0f,105.0f,85.0f);

        Assertions.assertEquals(statisticsDataController.getUserStatisticsByType("hip", this.userInfo), userStatisticsInfo);
    }

    @Test
    void checkFatStatWrongTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(100.5f,40.0f,22.17f);

        Assertions.assertNotEquals(statisticsDataController.getUserStatisticsByType("fat", this.userInfo), userStatisticsInfo);
    }

    @Test
    void checkWeightStatWrongTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(10.0f,100.0f,700.17f);

        Assertions.assertNotEquals(statisticsDataController.getUserStatisticsByType("weight", this.userInfo), userStatisticsInfo);
    }

    @Test
    void checkLeanMassStatWrongTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(600.0f,60.0f,60.0f);

        Assertions.assertNotEquals(statisticsDataController.getUserStatisticsByType("leanMass", this.userInfo), userStatisticsInfo);
    }

    @Test
    void checkHipStatWrongTest(){
        UserStatisticsInfo userStatisticsInfo = new UserStatisticsInfo(60.0f,105.0f,85.0f);

        Assertions.assertNotEquals(statisticsDataController.getUserStatisticsByType("hip", this.userInfo), userStatisticsInfo);
    }
    @Test
    void checkStatWrongTypeTest(){
        assertThrows(NullPointerException.class, () -> statisticsDataController.getUserStatisticsByType("neexistujuciTyp", this.userInfo));
    }

    @Test
    void checkFatDataOkTest(){
        List<Float> fatValues = userInfo.getBodyRecords().stream().map(BodyRecord::getFat).collect(Collectors.toList());
        List<XYChart.Data<Number, Float>> data = new ArrayList<>();
        IntStream.range(0, fatValues.size()).forEach(i -> data.add(new XYChart.Data<>(i + 1, fatValues.get(i))));

        Assertions.assertEquals(statisticsDataController.getDataByType("fat", this.userInfo).toString(),data.toString());
    }

    @Test
    void checkWeightDataOkTest(){
        List<Float> values = userInfo.getBodyRecords().stream().map(BodyRecord::getWeight).collect(Collectors.toList());
        List<XYChart.Data<Number, Float>> data = new ArrayList<>();
        IntStream.range(0, values.size()).forEach(i -> data.add(new XYChart.Data<>(i + 1, values.get(i))));

        Assertions.assertEquals(statisticsDataController.getDataByType("weight", this.userInfo).toString(),data.toString());
    }

    @Test
    void checkLeanMassDataOkTest(){
        List<Float> values = userInfo.getBodyRecords().stream().map(BodyRecord::getLeanMass).collect(Collectors.toList());
        List<XYChart.Data<Number, Float>> data = new ArrayList<>();
        IntStream.range(0, values.size()).forEach(i -> data.add(new XYChart.Data<>(i + 1, values.get(i))));

        Assertions.assertEquals(statisticsDataController.getDataByType("leanMass", this.userInfo).toString(),data.toString());
    }

    @Test
    void checkHipDataOkTest(){
        List<Float> values = userInfo.getBodyRecords().stream().map(BodyRecord::getHip).collect(Collectors.toList());
        List<XYChart.Data<Number, Float>> data = new ArrayList<>();
        IntStream.range(0, values.size()).forEach(i -> data.add(new XYChart.Data<>(i + 1, values.get(i))));

        Assertions.assertEquals(statisticsDataController.getDataByType("hip", this.userInfo).toString(),data.toString());
    }

    @Test
    void checkFatDataWrongTest(){
        List<Float> fatValues = new ArrayList<>();
        List<XYChart.Data<Number, Float>> data = new ArrayList<>();
        IntStream.range(0, fatValues.size()).forEach(i -> data.add(new XYChart.Data<>(i + 1, fatValues.get(i))));

        Assertions.assertNotEquals(statisticsDataController.getDataByType("fat", this.userInfo).toString(),data.toString());
    }
}
