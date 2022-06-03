package com.demo.exchangeRate.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BelarusBankViewDTO implements BankRate {
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
    public double getUSDSellRate() {
        return USD_out;
    }

    @Override
    public double getEURSellRate() {
        return EUR_out;
    }
}
