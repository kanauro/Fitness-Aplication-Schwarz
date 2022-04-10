package sk.uniba.fmfi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BodyRecord {
    @JsonProperty("id") int id;
    @JsonProperty("date") String date;
    @JsonProperty("weight") float weight;
    @JsonProperty("height") float height;
    @JsonProperty("arm") float arm;
    @JsonProperty("neck") float neck;
    @JsonProperty("waist") float waist;
    @JsonProperty("hip") float hip;
    @JsonProperty("fat") float fat;
    @JsonProperty("muscle") float muscle;
    @JsonProperty("water") float water;
    @JsonProperty("bones") float bones;
}