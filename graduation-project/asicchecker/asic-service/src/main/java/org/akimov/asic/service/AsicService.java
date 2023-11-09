package org.akimov.asic.service;

import org.akimov.asic.model.dto.summary.Summary;
import org.akimov.asic.model.dto.status.Status;

public interface AsicService {
    Status getStatus();
    Summary getSummary();
    void reboot();
}
