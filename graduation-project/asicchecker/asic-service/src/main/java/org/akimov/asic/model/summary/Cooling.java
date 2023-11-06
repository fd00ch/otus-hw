package org.akimov.asic.model.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Cooling {
    @JsonProperty("fan_num")
    private int fanNum;

    @JsonProperty("fans")
    private List<Fan> fans;

    @JsonProperty("settings")
    private FanSettings fanSettings;

    @JsonProperty("fan_duty")
    private int fanDuty;
}
