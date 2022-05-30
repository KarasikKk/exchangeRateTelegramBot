package com.demo.exchangeRate.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class BelarusBankFinanceRate implements BankRate {
    private double USD_in;
    private double USD_out;
    private double EUR_in;
    private double EUR_out;

    @Override
    public double getUSDPurchaseRate() {
        return USD_in;
    }

    @Override
    public double getEURPurchaseRate() {
        return EUR_in;
    }

    @Override
    public double getUSDSellingRate() {
        return USD_out;
    }

    @Override
    public double getEURSellingRate() {
        return EUR_out;
    }
}
