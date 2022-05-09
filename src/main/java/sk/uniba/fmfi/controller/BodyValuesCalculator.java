package sk.uniba.fmfi.controller;

import lombok.Data;
import lombok.SneakyThrows;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.model.UserInfo;

import java.util.InputMismatchException;
import java.util.List;

@Data
public class BodyValuesCalculator {
    private static final float minWeight = 20f;
    private static final float maxWeight = 200f;
    private static final float minHeight = 120f;
    private static final float maxHeight = 220f;
    private static final List<Float> maleCoefficients = List.of(1.0324f, 0.19077f, 0.15456f);
    private static final List<Float> femaleCoefficients = List.of(1.29579f, 0.35004f, 0.22100f);
    UserInfo userInfo;
    public BodyValuesCalculator(UserInfo userInfo) { this.userInfo = userInfo; }

    public BodyValuesCalculator() {}

    // TODO: Doplnit metody pre vypocet podielu kosti, svalov a vody

    public void setBodyValues(BodyRecord bodyRecord) {
        bodyRecord.setBmi(getBMI(bodyRecord));
        bodyRecord.setFat(getBodyFatPercentage(bodyRecord));
        bodyRecord.setLeanMass(getLeanMassPercentage(bodyRecord));
    }
    @SneakyThrows
    public float getBMI(BodyRecord bodyRecord) {
            if (bodyRecord.getWeight() < minWeight || bodyRecord.getWeight() > maxWeight)
                throw new IllegalArgumentException("Please enter another value for weight");
            if (bodyRecord.getHeight() < minHeight || bodyRecord.getHeight() > maxHeight)
                throw new IllegalArgumentException("Please enter another value for height");
            return (float) (bodyRecord.getWeight() / Math.pow(bodyRecord.getHeight() / 100, 2));
    }

    /**
     * Havriil Pietukhin: changed formula according to https://www.calculator.net/body-fat-calculator.html for both genders
     *
     * @param bodyRecord - json record with body parameters
     * @return body fat precentage
     */

    public float getBodyFatPercentage(BodyRecord bodyRecord) {
        List<Float> coeffs;
        if (userInfo.getGender().equals(UserInfo.MALE)) coeffs = maleCoefficients;
        else coeffs = femaleCoefficients;
        return (float) (495 / (coeffs.get(0) - coeffs.get(1) * Math.log10(bodyRecord.getWaist() - bodyRecord.getNeck())
                + coeffs.get(2) * Math.log10(bodyRecord.getHeight())) - 450);
    }
    public float getLeanMassPercentage(BodyRecord bodyRecord) {
        return bodyRecord.getWeight() * (1 - getBodyFatPercentage(bodyRecord));
    }

}
