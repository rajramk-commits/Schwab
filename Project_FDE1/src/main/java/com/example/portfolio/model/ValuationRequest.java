package com.example.portfolio.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ValuationRequest {

    @NotNull
    private String portfolioId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date valuationDate;

    @NotNull
    @Positive
    private Double beginMarketValue;

    @NotNull
    private Double endMarketValue;

    @NotNull
    private Double netCashFlow;

    @NotNull
    private Double benchmarkReturnPct;

    @NotNull
    private String currency;

    @NotNull
    private String requestedBy;

    public Double getBeginMarketValue() {
        return beginMarketValue;
    }

    public void setBeginMarketValue(Double beginMarketValue) {
        this.beginMarketValue = beginMarketValue;
    }

    public Double getEndMarketValue() {
        return endMarketValue;
    }

    public void setEndMarketValue(Double endMarketValue) {
        this.endMarketValue = endMarketValue;
    }

    public Double getNetCashFlow() {
        return netCashFlow;
    }

    public void setNetCashFlow(Double netCashFlow) {
        this.netCashFlow = netCashFlow;
    }

    public Double getBenchmarkReturnPct() {
        return benchmarkReturnPct;
    }

    public void setBenchmarkReturnPct(Double benchmarkReturnPct) {
        this.benchmarkReturnPct = benchmarkReturnPct;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}
