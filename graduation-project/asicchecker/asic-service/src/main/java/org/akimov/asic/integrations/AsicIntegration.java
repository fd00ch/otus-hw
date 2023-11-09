package org.akimov.asic.integrations;

import org.akimov.asic.model.status.Status;
import org.akimov.asic.model.summary.Summary;

public interface AsicIntegration {
    Status getStatus();
    Summary getSummary();
    void reboot();
}
