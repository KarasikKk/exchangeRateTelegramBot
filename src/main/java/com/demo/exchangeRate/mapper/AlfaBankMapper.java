package com.demo.exchangeRate.mapper;

import com.demo.exchangeRate.DTO.AlfaBankDTO;
import com.demo.exchangeRate.DTO.AlfaBankViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AlfaBankMapper {

    public AlfaBankViewDTO convertToAlfaBankViwDTO(List<AlfaBankDTO> alfaBankDTOBank) {
        AlfaBankViewDTO alfaBankView = new AlfaBankViewDTO();
        for (AlfaBankDTO alfaBank : alfaBankDTOBank) {
            if (alfaBank.getName() != null) {
                fillTheFields(alfaBankView, alfaBank);
            }
        }
        return alfaBankView;
    }

    private void fillTheFields(AlfaBankViewDTO alfaBankView, AlfaBankDTO alfaBank) {
        if (alfaBank.getSellIso().equals("EUR")) {
            alfaBankView.setEurBuyRate(alfaBank.getSellRate());
            alfaBankView.setEurSellRate(alfaBank.getBuyRate());
        } else if (alfaBank.getSellIso().equals("USD")) {
            alfaBankView.setUsdBuyRate(alfaBank.getSellRate());
            alfaBankView.setUsdSellRate(alfaBank.getBuyRate());
        }
    }
}
