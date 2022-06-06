package com.demo.exchangeRate.mapper;

import com.demo.exchangeRate.DTO.NationalBankDTO;
import com.demo.exchangeRate.DTO.SellExchangeRateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NationalBankMapper {

    public SellExchangeRateDTO convertToSellExchangeRateDTO(NationalBankDTO[] nationalBankDTOS) {
        SellExchangeRateDTO sellExchangeRateDTO = new SellExchangeRateDTO();
        for (NationalBankDTO nb : nationalBankDTOS) {
            if (nb.getCurAbbreviation().equals("USD")) {
                sellExchangeRateDTO.setUsdSellRate(nb.getCurOfficialRate());
            } else if (nb.getCurAbbreviation().equals("EUR")) {
                sellExchangeRateDTO.setEurSellRate(nb.getCurOfficialRate());
            }
        }
        return sellExchangeRateDTO;
    }
}
