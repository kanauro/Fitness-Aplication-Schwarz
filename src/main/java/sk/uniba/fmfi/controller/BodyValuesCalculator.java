package sk.uniba.fmfi.controller;

import lombok.Data;
import sk.uniba.fmfi.model.BodyRecord;

@Data
public class BodyValuesCalculator {

    // TODO: Doplnit metody pre vypocet podielu kosti, svalov a vody

    public void setBodyValues(BodyRecord bodyRecord){
        bodyRecord.setFat(getBodyFatPercentage(bodyRecord));
        bodyRecord.setBones(1.5f);
        bodyRecord.setMuscle(1.5f);
        bodyRecord.setWater(1.5f);
    }

    public float getBodyFatPercentage(BodyRecord bodyRecord) {
        return (float) (163.205f * Math.log(bodyRecord.getWaist() + bodyRecord.getHip() - bodyRecord.getNeck()) - 97.684f * Math.log(bodyRecord.getHeight()) - 104.912f);
    }

}
