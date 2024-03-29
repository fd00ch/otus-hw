package org.akimov.asic.model.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChainStatus {
    @JsonProperty("state")
    private String state;

    @JsonProperty("description")
    private String description;
}
