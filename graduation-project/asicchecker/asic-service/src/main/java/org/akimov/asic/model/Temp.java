package org.akimov.asic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Temp {
    @JsonProperty("min")
    private int min;

    @JsonProperty("max")
    private int max;
}
