package sk.uniba.fmfi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    float weight;
    float height;
    float arm;
    float neck;
    float waist;
    float hip;
}
