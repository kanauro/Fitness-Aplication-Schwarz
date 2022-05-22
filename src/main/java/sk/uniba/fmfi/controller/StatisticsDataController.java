package sk.uniba.fmfi.controller;

import javafx.scene.chart.XYChart;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.model.UserInfo;
import sk.uniba.fmfi.model.UserStatisticsInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StatisticsDataController {

    public List<XYChart.Data<Number, Float>> getDataByType(String type, UserInfo userInfo) {
        switch (type) {
            case "fat":
                List<Float> fatValues = userInfo.getBodyRecords().stream().map(BodyRecord::getFat).collect(Collectors.toList());
                return prepareChartData(fatValues);
            case "muscle":
                List<Float> muscleValues = userInfo.getBodyRecords().stream().map(BodyRecord::getMuscle).collect(Collectors.toList());
                return prepareChartData(muscleValues);
            case "weight":
                List<Float> weightValues = userInfo.getBodyRecords().stream().map(BodyRecord::getWeight).collect(Collectors.toList());
                return prepareChartData(weightValues);
            case "hip":
                List<Float> hipValues = userInfo.getBodyRecords().stream().map(BodyRecord::getHip).collect(Collectors.toList());
                return prepareChartData(hipValues);
            default:
                return Collections.emptyList();
        }
    }

    private List<XYChart.Data<Number, Float>> prepareChartData(List<Float> values) {
        List<XYChart.Data<Number, Float>> data = new ArrayList<>();
        IntStream.range(0, values.size()).forEach(i -> data.add(new XYChart.Data<>(i + 1, values.get(i))));
        return data;
    }

    public UserStatisticsInfo getUserStatisticsByType(String type, UserInfo userInfo){
        List<XYChart.Data<Number, Float>> data = new ArrayList<>();
        data = getDataByType(type, userInfo);
        Float min = null;
        Float max = null;
        Float avg = 0f;

        for (XYChart.Data<Number,Float> item:data) {
            avg+= item.getYValue();

            if (min == null || item.getYValue()<min ){
                min = item.getYValue();
            }
            if (max == null || item.getYValue()>max ){
                max = item.getYValue();
            }
        }
        avg /= data.size();

        min = Math.round(min * 100.0) / 100.0f;
        max = Math.round(max * 100.0) / 100.0f;
        avg = Math.round(avg * 100.0) / 100.0f;

        return new UserStatisticsInfo(min,max,avg);
    }

}
