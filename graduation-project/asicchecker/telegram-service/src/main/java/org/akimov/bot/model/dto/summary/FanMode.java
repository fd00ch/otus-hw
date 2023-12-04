package org.akimov.bot.model.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FanMode {
    @JsonProperty("name")
    private String name;

    @JsonProperty("param")
    private int param;
}
