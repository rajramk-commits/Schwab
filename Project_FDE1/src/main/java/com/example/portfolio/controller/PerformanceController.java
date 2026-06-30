package com.example.portfolio.controller;

import com.example.portfolio.model.DailyReturnSummary;
import com.example.portfolio.model.ValuationRequest;
import com.example.portfolio.service.DailyReturnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {
    private final DailyReturnService dailyReturnService = new DailyReturnService();

    @PostMapping("/daily-summary")
    public ResponseEntity<DailyReturnSummary> calculateDailySummary(@Valid @RequestBody ValuationRequest request) {
        DailyReturnSummary result = dailyReturnService.calculateSummary(request);
        if (result.getStatus() == DailyReturnSummary.StatusDecision.INVALID_INPUT) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
