package com.demo.exchangeRate.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class BelarusBankDTO{
    @JsonProperty(value = "USD_in")
    private double USDPurchaseRate;
    @JsonProperty(value = "USD_out")
    private double USDSellRate;
    @JsonProperty(value = "EUR_in")
    private double EURPurchaseRate;
    @JsonProperty(value = "EUR_out")
    private double EURSellRate;
}
