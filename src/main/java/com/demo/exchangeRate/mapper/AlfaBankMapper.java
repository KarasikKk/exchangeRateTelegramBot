package com.demo.exchangeRate.mapper;

import com.demo.exchangeRate.DTO.AlfaBankDTO;
import com.demo.exchangeRate.DTO.FullBankExchangeRateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AlfaBankMapper {

    public FullBankExchangeRateDTO convertToFullExchangeRateDTO(List<AlfaBankDTO> alfaBankDTOBank) {
        FullBankExchangeRateDTO fullExchangeRate = new FullBankExchangeRateDTO();
        for (AlfaBankDTO alfaBank : alfaBankDTOBank) {
            if (alfaBank.getName() != null) {
                fillTheFields(fullExchangeRate, alfaBank);
            }
        }
        return fullExchangeRate;
    }

    private void fillTheFields(FullBankExchangeRateDTO fullExchangeRate, AlfaBankDTO alfaBank) {
        if (alfaBank.getSellIso().equals("EUR")) {
            fullExchangeRate.setEurBuyRate(alfaBank.getSellRate());
            fullExchangeRate.setEurSellRate(alfaBank.getBuyRate());
        } else if (alfaBank.getSellIso().equals("USD")) {
            fullExchangeRate.setUsdBuyRate(alfaBank.getSellRate());
            fullExchangeRate.setUsdSellRate(alfaBank.getBuyRate());
        }
    }
}
