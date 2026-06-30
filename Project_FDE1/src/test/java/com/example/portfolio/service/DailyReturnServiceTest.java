package com.example.portfolio.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.portfolio.model.DailyReturnSummary;
import com.example.portfolio.model.ValuationRequest;

public class DailyReturnServiceTest {
    private final DailyReturnService service = new DailyReturnService();

    @Test
    public void calculateSummary_validInput_returnsValidSummary() {
        ValuationRequest request = new ValuationRequest();
        request.setPortfolioId("PF-1001");
        request.setValuationDate(new java.util.Date(126, 5, 14)); // June 14, 2026
        request.setBeginMarketValue(1000000.0);
        request.setEndMarketValue(1035000.0);
        request.setNetCashFlow(10000.0);
        request.setBenchmarkReturnPct(1.8);
        request.setCurrency("USD");
        request.setRequestedBy("advisor-01");

        DailyReturnSummary summary = service.calculateSummary(request);

        Assertions.assertEquals(2.5, summary.getportfolioreturnPct(), 1e-6);
        Assertions.assertEquals(1.8, summary.getBenchmarkReturnPct(), 1e-6);
        Assertions.assertEquals(0.7, summary.getexcessReturnPct(), 1e-6);
        Assertions.assertEquals(DailyReturnSummary.StatusDecision.VALID, summary.getStatus());
    }

    @Test
    public void calculateSummary_reviewRequired_returnsReviewStatus() {
        ValuationRequest request = new ValuationRequest();
        request.setPortfolioId("PF-1002");
        request.setValuationDate(new java.util.Date(126, 5, 15)); // June 15, 2026
        request.setBeginMarketValue(1000000.0);
        request.setEndMarketValue(1020000.0);
        request.setNetCashFlow(300000.0); // Suspicious cash flow
        request.setBenchmarkReturnPct(1.5);
        request.setCurrency("USD");
        request.setRequestedBy("advisor-02");

        DailyReturnSummary summary = service.calculateSummary(request);

        Assertions.assertEquals(DailyReturnSummary.StatusDecision.REVIEW_REQUIRED, summary.getStatus());
    }

    @Test
    public void calculateSummary_invalidInput_returnsInvalidStatus() {
        ValuationRequest request = new ValuationRequest();
        request.setBeginMarketValue(0.0);
        request.setEndMarketValue(100.0);
        request.setNetCashFlow(0.0);
        request.setBenchmarkReturnPct(1.0);

        DailyReturnSummary summary = service.calculateSummary(request);

        Assertions.assertEquals(DailyReturnSummary.StatusDecision.INVALID_INPUT, summary.getStatus());
    }
}
