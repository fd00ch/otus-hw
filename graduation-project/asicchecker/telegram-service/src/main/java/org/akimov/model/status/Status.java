package org.akimov.model.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status {
    @JsonProperty("miner_state")
    private String minerState;

    @JsonProperty("miner_state_time")
    private float minerStateTime;

    @JsonProperty("find_miner")
    private boolean findMiner;

    @JsonProperty("restart_required")
    private boolean restartRequired;

    @JsonProperty("reboot_required")
    private boolean rebootRequired;

    @JsonProperty("unlocked")
    private boolean unlocked;
}
