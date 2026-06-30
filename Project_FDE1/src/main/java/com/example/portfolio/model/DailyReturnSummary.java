package com.example.portfolio.model;

import java.util.Date;

public class DailyReturnSummary {
    private String portfolioId;
    private Date valuationDate;    
    private double portfolioreturnPct;
    private double benchmarkReturnPct;
    private double excessReturnPct;
    private StatusDecision status;
    private String[] reasons;
    private Date processedAt;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String[] getReasons() {
        return reasons;
    }

    public void setReasons(String[] reasons) {
        this.reasons = reasons;
    }

    public Date getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Date processedAt) {
        this.processedAt = processedAt;
    }


    public enum StatusDecision {
        VALID,
        REVIEW_REQUIRED,
        INVALID_INPUT
    }

    public DailyReturnSummary(String portfolioId, Date valuationDate, double dailyReturnPct, double benchmarkReturnPct, double excessReturnPct, StatusDecision status, String[] reasons, Date processedAt) {
        this.portfolioId = portfolioId;
        this.valuationDate = valuationDate;
        this.portfolioreturnPct = dailyReturnPct;
        this.benchmarkReturnPct = benchmarkReturnPct;
        this.excessReturnPct = excessReturnPct;
        this.status = status;
        this.reasons = reasons;
        this.processedAt = processedAt;
    }

    public double getportfolioreturnPct() {
        return portfolioreturnPct;
    }

    public void setportfolioreturnPct(double portfolioreturnPct) {
        this.portfolioreturnPct = portfolioreturnPct;
    }

    public double getBenchmarkReturnPct() {
        return benchmarkReturnPct;
    }

    public void setBenchmarkReturnPct(double benchmarkReturnPct) {
        this.benchmarkReturnPct = benchmarkReturnPct;
    }

    public double getexcessReturnPct() {
        return excessReturnPct;
    }

    public void setExcessReturnPct(double excessReturnPct) {
        this.excessReturnPct = excessReturnPct;
    }

    public StatusDecision getStatus() {
        return status;
    }

    public void setStatus(StatusDecision status) {
        this.status = status;
    }
}
