package org.akimov.asic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MinerStatus {
    @JsonProperty("miner_state")
    private String minerState;

    @JsonProperty("miner_state_time")
    private float minerStateTime;
}
