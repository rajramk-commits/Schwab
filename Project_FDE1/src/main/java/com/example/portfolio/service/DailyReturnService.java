package com.example.portfolio.service;

import com.example.portfolio.model.DailyReturnSummary;
import com.example.portfolio.model.DailyReturnSummary.StatusDecision;
import com.example.portfolio.model.ValuationRequest;

public class DailyReturnService {
    public DailyReturnSummary calculateSummary(ValuationRequest request) {
        if (!isValidInput(request)) {
            return new DailyReturnSummary(null, null, 0.0, 0.0, 0.0, StatusDecision.INVALID_INPUT, null, null);
        }

        String portfolioId = request.getPortfolioId();
        java.util.Date valuationDate = request.getValuationDate();
        double startValue = request.getBeginMarketValue();
        double endValue = request.getEndMarketValue();
        double netCashFlow = request.getNetCashFlow();
        double benchmarkReturnPercent = request.getBenchmarkReturnPct();
        String[] reasons = new String[0]; // Placeholder for reasons, can be populated based on business logic
        java.util.Date processedAt = new java.util.Date(); // Current timestamp

        double dailyReturnPercent = ((endValue - startValue - netCashFlow) / startValue) * 100.0;
        double excessReturnPercent = dailyReturnPercent - benchmarkReturnPercent;

        StatusDecision status = evaluateStatus(startValue, dailyReturnPercent, benchmarkReturnPercent, netCashFlow);
        return new DailyReturnSummary(portfolioId, valuationDate, dailyReturnPercent, benchmarkReturnPercent, excessReturnPercent, status, reasons, processedAt);
    }

    private boolean isValidInput(ValuationRequest request) {
        return request != null
                && request.getBeginMarketValue() != null
                && request.getEndMarketValue() != null
                && request.getNetCashFlow() != null
                && request.getBenchmarkReturnPct() != null
                && request.getBeginMarketValue() > 0
                && request.getEndMarketValue() >= 0;
    }

    private StatusDecision evaluateStatus(double startValue, double dailyReturnPercent, double benchmarkReturnPercent, double netCashFlow) {
        boolean suspiciousCashFlow = Math.abs(netCashFlow) > 0.2 * startValue;
        boolean suspiciousExcess = Math.abs(dailyReturnPercent - benchmarkReturnPercent) > 5;

        if (suspiciousCashFlow || suspiciousExcess) {
            return StatusDecision.REVIEW_REQUIRED;
        }
        return StatusDecision.VALID;
    }
}
