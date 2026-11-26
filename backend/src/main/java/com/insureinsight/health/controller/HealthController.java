package com.insureinsight.health.controller;

import com.insureinsight.common.api.ApiResponse;
import com.insureinsight.common.dto.HealthDto;
import com.insureinsight.health.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping
    public ApiResponse<HealthDto> health() {
        return ApiResponse.success("API 연결이 정상입니다.", healthService.getHealth());
    }
}
