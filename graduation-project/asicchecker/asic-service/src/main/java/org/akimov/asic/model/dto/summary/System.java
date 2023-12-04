package org.akimov.asic.model.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class System {
    @JsonProperty("os")
    private String os;

    @JsonProperty("miner_name")
    private String minerName;

    @JsonProperty("file_system_version")
    private String fileSystemVersion;

    @JsonProperty("mem_total")
    private int memTotal;

    @JsonProperty("mem_free")
    private int memFree;

    @JsonProperty("mem_free_percent")
    private int memFreePercent;

    @JsonProperty("mem_buf")
    private int memBuf;

    @JsonProperty("mem_buf_percent")
    private int memBufPercent;

    @JsonProperty("network_status")
    private NetworkStatus networkStatus;

    @JsonProperty("uptime")
    private String uptime;
}
