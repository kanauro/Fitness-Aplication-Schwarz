import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import sk.uniba.fmfi.controller.BodyValuesCalculator;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.model.Database;
import sk.uniba.fmfi.model.UserInfo;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BodyValuesCalculatorTests {
    BodyValuesCalculator calculator = new BodyValuesCalculator();
    BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100f, 190f, 30f, 41f, 90f,
            90f,10f,10f, 10f);


    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(final Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.out.printf("\nStarting test: %s in %s\n", methodName, className);
        }
    };
    @BeforeEach
    void setUp() {
        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100f, 190f, 30f, 41f, 90f,
                90f,10f,10f, 10f);
        UserInfo userInfo = new UserInfo("Adam", 23, "male", 80f,
                185f, Collections.EMPTY_LIST);
        calculator = new BodyValuesCalculator(userInfo);
    }

    @Test
    public void nullRecordAsArg() {
        //why getBodyFatPercentage is not annotated with @NotNull?
        assertThrows(NullPointerException.class, () -> calculator.getBodyFatPercentage(null));
        assertThrows(NullPointerException.class, () -> calculator.getBMI(null));
        assertThrows(NullPointerException.class, () -> calculator.getLeanMassPercentage(null));
    }
    @Test
    public void BMIFromValidRecord() {
        BodyRecord bodyRecord = new BodyRecord(1,"2022-04-13", 100f, 190f, 30f, 41f, 90f,
                90f,10f,10f, 10f);
        float bmi = calculator.getBMI(bodyRecord);
        assertEquals(100f / (1.9 * 1.9),
                bmi, 0.001);
    }
    @Test
    public void BMIWithIncorrectHeight() {
        //min
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 100f,0f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
        //max
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 100f, 300f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
    }
    @Test
    public void BMIWithIncorrectWeight() {
      //min
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 0f, 170f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
        //max
        assertThrows(IllegalArgumentException.class, () -> calculator.getBMI(new BodyRecord(1,"2022-04-13", 300f, 170f, 30f, 41f, 90f,
                90f,10f,10f, 10f)));
    }

    @Test
    public void unknownGenderTest() {
        UserInfo userInfo = new UserInfo("Adam", 23, "mail", 80f,
                185f, Collections.EMPTY_LIST);
        calculator = new BodyValuesCalculator(userInfo);
        assertThrows(IllegalArgumentException.class, () -> calculator.getBodyFatPercentage(bodyRecord));
    }
    @Test
    public void userInfoWithNoArgsTest() {
        UserInfo userInfo = new UserInfo();
        calculator = new BodyValuesCalculator(userInfo);
        assertThrows(NullPointerException.class, () -> calculator.getBodyFatPercentage(bodyRecord));
    }
    @Test
    public void setValuesTest() {
        UserInfo userInfo = new UserInfo("Adam", 23, "male", 80f,
                185f, Collections.EMPTY_LIST);
        calculator = new BodyValuesCalculator(userInfo);
        calculator.setBodyValues(bodyRecord);
        float bmi = calculator.getBMI(bodyRecord);
        float fat = calculator.getBodyFatPercentage(bodyRecord);
        float lean = calculator.getLeanMassPercentage(bodyRecord);
        assertEquals(calculator.getBMI(bodyRecord), bmi, 0.001);
        assertEquals(calculator.getBodyFatPercentage(bodyRecord), fat, 0.001);
        assertEquals(calculator.getLeanMassPercentage(bodyRecord), lean, 0.001);
    }


}
