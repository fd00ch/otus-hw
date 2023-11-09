package org.akimov.asic.model.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NetworkStatus {
    @JsonProperty("mac")
    private String mac;

    @JsonProperty("dhcp")
    private boolean dhcp;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("netmask")
    private String netmask;

    @JsonProperty("gateway")
    private String gateway;

    @JsonProperty("dns")
    private List<String> dns;

    @JsonProperty("hostname")
    private String hostname;
}
