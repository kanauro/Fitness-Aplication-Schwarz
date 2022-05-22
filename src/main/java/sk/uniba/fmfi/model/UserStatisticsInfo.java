package sk.uniba.fmfi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UserStatisticsInfo {
    @JsonProperty("minimum") float minimum;
    @JsonProperty("maximum") float maximum;
    @JsonProperty("average") float average;
}
