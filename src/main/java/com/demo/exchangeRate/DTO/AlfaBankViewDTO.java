package com.demo.exchangeRate.DTO;

import lombok.Setter;

@Setter
public class AlfaBankViewDTO implements BankRate {
    private double UsdBuyRate;
    private double UsdSellRate;
    private double EurBuyRate;
    private double EurSellRate;

    @Override
    public double getUSDPurchaseRate() {
        return UsdBuyRate;
    }

    @Override
    public double getEURPurchaseRate() {
        return EurBuyRate;
    }

    @Override
    public double getUSDSellRate() {
        return UsdSellRate;
    }

    @Override
    public double getEURSellRate() {
        return EurSellRate;
    }
}
