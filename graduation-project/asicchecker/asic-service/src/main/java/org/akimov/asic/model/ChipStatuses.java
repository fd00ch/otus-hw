package org.akimov.asic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChipStatuses {
    @JsonProperty("red")
    private int red;

    @JsonProperty("orange")
    private int orange;

    @JsonProperty("grey")
    private int grey;
}
