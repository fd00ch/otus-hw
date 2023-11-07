package org.akimov.model.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fan {
    @JsonProperty("id")
    private int id;

    @JsonProperty("rpm")
    private int rpm;
}
