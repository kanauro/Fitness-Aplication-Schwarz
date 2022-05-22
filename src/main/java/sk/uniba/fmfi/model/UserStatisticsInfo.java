package sk.uniba.fmfi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UserStatisticsInfo {
    float minimum;
    float maximum;
    float average;
}
