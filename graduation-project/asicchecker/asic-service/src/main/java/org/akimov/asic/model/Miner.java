package org.akimov.asic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Miner {
    @JsonProperty("miner_status")
    private MinerStatus minerStatus;

    @JsonProperty("miner_type")
    private String minerType;

    @JsonProperty("hardware_version")
    private String hardwareVersion;

    @JsonProperty("cgminer_version")
    private String cgminerVersion;

    @JsonProperty("compile_time")
    private String compileTime;

    @JsonProperty("average_hashrate")
    private float averageHashrate;

    @JsonProperty("instant_hashrate")
    private float instantHashrate;

    @JsonProperty("pcb_temp")
    private Temp pcbTemp;

    @JsonProperty("chip_temp")
    private Temp chipTemp;

    @JsonProperty("power_usage")
    private float powerUsage;

    @JsonProperty("power_efficiency")
    private float powerEfficiency;

    @JsonProperty("hw_errors_percent")
    private float hwErrorsPercent;

    @JsonProperty("hw_errors")
    private float hwErrors;

    @JsonProperty("devfee_percent")
    private float devfeePercent;

    @JsonProperty("devfee")
    private float devfee;

    @JsonProperty("pools")
    private List<Pool> pools;

    @JsonProperty("cooling")
    private Cooling cooling;

    @JsonProperty("chains")
    private List<Chain> chains;

    @JsonProperty("found_blocks")
    private int foundBlocks;

    @JsonProperty("best_share")
    private long bestShare;
}
