package com.demo.exchangeRate.mapper;

import com.demo.exchangeRate.DTO.BelarusBankDTO;
import com.demo.exchangeRate.DTO.FullBankExchangeRateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BelarusBankMapper {

    public FullBankExchangeRateDTO convertToFullExchangeRateDTO(List<BelarusBankDTO> belarusBankDTO) {
        FullBankExchangeRateDTO fullExchangeRate = new FullBankExchangeRateDTO();
        fullExchangeRate.setUsdSellRate(belarusBankDTO.get(0).getUSDSellRate());
        fullExchangeRate.setUsdBuyRate(belarusBankDTO.get(0).getUSDPurchaseRate());
        fullExchangeRate.setEurSellRate(belarusBankDTO.get(0).getEURSellRate());
        fullExchangeRate.setEurBuyRate(belarusBankDTO.get(0).getEURPurchaseRate());
        return fullExchangeRate;
    }
}
