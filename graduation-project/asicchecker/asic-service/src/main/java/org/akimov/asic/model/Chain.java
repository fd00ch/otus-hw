package org.akimov.asic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Chain {
    @JsonProperty("id")
    private int id;

    @JsonProperty("frequency")
    private float frequency;

    @JsonProperty("voltage")
    private int voltage;

    @JsonProperty("power_usage")
    private int powerUsage;

    @JsonProperty("hashrate_ideal")
    private float hashrateIdeal;

    @JsonProperty("hashrate_rt")
    private float hashrateRt;

    @JsonProperty("hw_errors")
    private int hwErrors;

    @JsonProperty("pcb_temp_sens")
    private List<TempSensor> pcbTempSens;

    @JsonProperty("chip_temp_sens")
    private List<TempSensor> chipTempSens;

    @JsonProperty("chip_temp")
    private Temp chipTemp;

    @JsonProperty("chip_statuses")
    private ChipStatuses chipStatuses;

    @JsonProperty("status")
    private ChainStatus status;
}
