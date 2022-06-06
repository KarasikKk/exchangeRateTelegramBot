package com.demo.exchangeRate.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FullBankExchangeRateDTO {
    private double UsdSellRate;
    private double UsdBuyRate;
    private double EurSellRate;
    private double EurBuyRate;
}
