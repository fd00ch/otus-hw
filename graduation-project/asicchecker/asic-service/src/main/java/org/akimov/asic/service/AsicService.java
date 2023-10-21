package org.akimov.asic.service;

import org.akimov.asic.model.Summary;
import org.akimov.asic.model.Status;

public interface AsicService {
    Status getStatus();
    Summary getSummary();
}
