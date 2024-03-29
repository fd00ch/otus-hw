package org.akimov.bot.model.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Summary {
    @JsonProperty("system")
    private System system;

    @JsonProperty("miner")
    private Miner miner;
}
