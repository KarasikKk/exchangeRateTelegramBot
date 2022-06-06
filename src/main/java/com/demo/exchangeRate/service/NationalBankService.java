package com.demo.exchangeRate.service;

import com.demo.exchangeRate.DTO.NationalBankDTO;
import com.demo.exchangeRate.DTO.SellExchangeRateDTO;
import com.demo.exchangeRate.mapper.NationalBankMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class NationalBankService implements Bank{
    private final NationalBankMapper nbMapper;
    private final MessageCreatorService messageService;

    @Override
    public String getExchangeRate(String bankUrl) {
        NationalBankDTO[] nationalBankDTOS = null;
        try {
            nationalBankDTOS = new ObjectMapper().readValue(new URL(bankUrl), NationalBankDTO[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertDtoToString(convertToViewDTO(nationalBankDTOS));
    }

    private SellExchangeRateDTO convertToViewDTO(NationalBankDTO[] nationalBankDTOS) {
        return nbMapper.convertToSellExchangeRateDTO(nationalBankDTOS);
    }

    private String convertDtoToString(SellExchangeRateDTO sellExchangeRateDTO){
        return messageService.createAnswerWithSellExchangeRate(sellExchangeRateDTO);
    }
}
