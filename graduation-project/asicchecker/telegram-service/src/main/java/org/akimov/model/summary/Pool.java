package org.akimov.model.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pool {
    @JsonProperty("id")
    private int id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("pool_type")
    private String poolType;

    @JsonProperty("user")
    private String user;

    @JsonProperty("status")
    private String status;

    @JsonProperty("asic_boost")
    private boolean asicBoost;

    @JsonProperty("diff")
    private String diff;

    @JsonProperty("accepted")
    private int accepted;

    @JsonProperty("rejected")
    private int rejected;

    @JsonProperty("stale")
    private int stale;

    @JsonProperty("ls_diff")
    private double lsDiff;

    @JsonProperty("ls_time")
    private String lsTime;

    @JsonProperty("diffa")
    private double diffa;
}
