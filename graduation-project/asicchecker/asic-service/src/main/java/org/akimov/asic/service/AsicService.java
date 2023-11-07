package org.akimov.asic.service;

import org.akimov.asic.model.summary.Summary;
import org.akimov.asic.model.status.Status;

public interface AsicService {
    Status getStatus();
    Summary getSummary();
    void reboot();
}
