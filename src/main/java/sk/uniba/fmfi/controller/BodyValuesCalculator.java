package sk.uniba.fmfi.controller;

import lombok.*;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.model.UserInfo;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyValuesCalculator {
    private static final float MIN_WEIGHT = 20f;
    private static final float MAX_WEIGHT = 200f;
    private static final float MIN_HEIGHT = 120f;
    private static final float MAX_HEIGHT = 220f;
    private static final List<Float> maleCoefficients = Arrays.asList(1.0324f, 0.19077f, 0.15456f);
    private static final List<Float> femaleCoefficients = Arrays.asList(1.29579f, 0.35004f, 0.22100f);

    private UserInfo userInfo;

    public void setBodyValues(BodyRecord bodyRecord) {
        bodyRecord.setBmi(getBMI(bodyRecord));
        bodyRecord.setFat(getBodyFatPercentage(bodyRecord));
        bodyRecord.setLeanMass(getLeanMassPercentage(bodyRecord));
    }

    @SneakyThrows
    @NonNull
    public float getBMI(BodyRecord bodyRecord) {
        if (bodyRecord.getWeight() < MIN_WEIGHT || bodyRecord.getWeight() > MAX_WEIGHT)
            throw new IllegalArgumentException("Hodnota váhy musí byť v rozmedzí " + MIN_WEIGHT + " - " + MAX_WEIGHT + " kg");
        if (bodyRecord.getHeight() < MIN_HEIGHT || bodyRecord.getHeight() > MAX_HEIGHT)
            throw new IllegalArgumentException("Hodnota výšky musí byť v rozmedzí " + MIN_HEIGHT + " - " + MAX_HEIGHT+ " cm");
        return (float) (bodyRecord.getWeight() / Math.pow(bodyRecord.getHeight() / 100, 2));
    }

    /**
     * Havriil Pietukhin: changed formula according to https://www.calculator.net/body-fat-calculator.html for both genders
     *
     * @param bodyRecord - json record with body parameters
     * @return body fat precentage
     */
    @SneakyThrows
    @NonNull
    public float getBodyFatPercentage(BodyRecord bodyRecord) {
        List<Float> coeffs;
        String gender = userInfo.getGender();
        if (gender == null) throw new NullPointerException("Gender is not set");
        if (gender.equals(UserInfo.MALE)) coeffs = maleCoefficients;
        else if (gender.equals(UserInfo.FEMALE)) coeffs = femaleCoefficients;
        else throw new IllegalArgumentException("User info contains unknown gender");
        return (float) (495 / (coeffs.get(0) - coeffs.get(1) * Math.log10(bodyRecord.getWaist() - bodyRecord.getNeck())
                + coeffs.get(2) * Math.log10(bodyRecord.getHeight())) - 450);
    }

    @NonNull
    public float getLeanMassPercentage(BodyRecord bodyRecord) {
        return bodyRecord.getWeight() * (1 - getBodyFatPercentage(bodyRecord));
    }

}
