package com.insureinsight.common.dto;

import java.time.Instant;

public record HealthDto(String application, String status, Instant checkedAt) {
}
