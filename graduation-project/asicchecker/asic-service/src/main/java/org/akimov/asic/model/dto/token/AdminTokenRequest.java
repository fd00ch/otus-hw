package org.akimov.asic.model.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminTokenRequest {
    @JsonProperty("pw")
    private String pw;
}
