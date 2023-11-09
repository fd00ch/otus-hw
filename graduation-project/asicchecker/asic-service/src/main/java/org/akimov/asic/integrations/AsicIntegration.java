package org.akimov.asic.integrations;

import org.akimov.asic.model.dto.status.Status;
import org.akimov.asic.model.dto.summary.Summary;

public interface AsicIntegration {
    Status getStatus();
    Summary getSummary();
    void reboot();
}
