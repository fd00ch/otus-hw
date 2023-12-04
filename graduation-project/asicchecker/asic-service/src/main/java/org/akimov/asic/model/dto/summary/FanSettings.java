package org.akimov.asic.model.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FanSettings {
    @JsonProperty("mode")
    private FanMode fanMode;
}
