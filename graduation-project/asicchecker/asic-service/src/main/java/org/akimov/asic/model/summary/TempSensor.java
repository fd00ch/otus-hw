package org.akimov.asic.model.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TempSensor {
    @JsonProperty("status")
    private String status;

    @JsonProperty("temp")
    private int temp;
}
