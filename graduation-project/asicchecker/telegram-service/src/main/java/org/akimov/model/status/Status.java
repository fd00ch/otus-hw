package org.akimov.model.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status {
    @JsonProperty("miner_state")
    private String miner_state;

    @JsonProperty("miner_state_time")
    private float miner_state_time;

    @JsonProperty("find_miner")
    private boolean find_miner;

    @JsonProperty("restart_required")
    private boolean restart_required;

    @JsonProperty("reboot_required")
    private boolean reboot_required;

    @JsonProperty("unlocked")
    private boolean unlocked;
}
