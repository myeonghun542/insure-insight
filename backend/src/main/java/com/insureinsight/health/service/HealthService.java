package com.insureinsight.health.service;

import com.insureinsight.common.dto.HealthDto;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class HealthService {
    public HealthDto getHealth() {
        return new HealthDto("Insure Insight API", "UP", Instant.now());
    }
}
