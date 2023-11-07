package org.akimov.intergation;

import org.akimov.model.status.Status;
import org.akimov.model.summary.Summary;

public interface AsicServiceClient {
    Status getAsicStatus();
    Summary getAsicSummary();
}
