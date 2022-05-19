import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import sk.uniba.fmfi.controller.BodyValuesCalculator;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.model.UserInfo;

import java.util.Collections;

import static org.junit.Assert.*;

class BodyValuesCalculatorTests {
    BodyValuesCalculator calculator;
    BodyRecord bodyRecord;

    @BeforeEach
    void setUp() {
        UserInfo userInfo = new UserInfo("Adam", 23, "male", 80f, 185f, Collections.emptyList());
        bodyRecord = new BodyRecord(1,"2022-04-13", 100f, 190f, 30f, 41f, 90f,
                90f,10f,10f, 10f);
        calculator = new BodyValuesCalculator(userInfo);
    }

    @Test
    void nullRecordAsArg() {
        //why getBodyFatPercentage is not annotated with @NotNull?
        assertThrows(NullPointerException.class, () -> calculator.getBodyFatPercentage(null));
        assertThrows(NullPointerException.class, () -> calculator.getBMI(null));
        assertThrows(NullPointerException.class, () -> calculator.getLeanMassPercentage(null));
    }
    @Test
    void BMIFromValidRecord() {
        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100f, 190f, 30f, 41f, 90f,
                90f,10f,10f, 10f);
        float bmi = calculator.getBMI(bodyRecord);
        Assertions.assertEquals(100f / (1.9 * 1.9), bmi, 0.001);
    }
    @Test
    void BMIWithIncorrectHeight() {
        //min
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 100f,0f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
        //max
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 100f, 300f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
    }

    @Test
    void BMIWithIncorrectWeight() {
      //min
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 0f, 170f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
        //max
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 300f, 170f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
    }

    @Test
    void unknownGenderTest() {
        UserInfo userInfo = new UserInfo("Adam", 23, "mail", 80f,
                185f, Collections.emptyList());
        calculator = new BodyValuesCalculator(userInfo);
        assertThrows(IllegalArgumentException.class, () -> calculator.getBodyFatPercentage(bodyRecord));
    }
    @Test
    void userInfoWithNoArgsTest() {
        UserInfo userInfo = new UserInfo();
        calculator = new BodyValuesCalculator(userInfo);
        assertThrows(NullPointerException.class, () -> calculator.getBodyFatPercentage(bodyRecord));
    }
    @Test
    void setValuesTest() {
        UserInfo userInfo = new UserInfo("Adam", 23, "male", 80f,
                185f, Collections.emptyList());
        calculator = new BodyValuesCalculator(userInfo);
        calculator.setBodyValues(bodyRecord);
        float bmi = calculator.getBMI(bodyRecord);
        float fat = calculator.getBodyFatPercentage(bodyRecord);
        float lean = calculator.getLeanMassPercentage(bodyRecord);
        Assertions.assertEquals(calculator.getBMI(bodyRecord), bmi, 0.001);
        Assertions.assertEquals(calculator.getBodyFatPercentage(bodyRecord), fat, 0.001);
        Assertions.assertEquals(calculator.getLeanMassPercentage(bodyRecord), lean, 0.001);
    }

}
