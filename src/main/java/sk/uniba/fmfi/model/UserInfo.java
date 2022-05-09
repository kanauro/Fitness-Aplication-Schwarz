package sk.uniba.fmfi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @JsonProperty("name") String name;
    @JsonProperty("latestWeight") float latestWeight;
    @JsonProperty("latestHeight") float latestHeight;
    @JsonProperty("records") List<BodyRecord> bodyRecords;
}
