package com.demo.exchangeRate.service;

import com.demo.exchangeRate.DTO.BelarusBankDTO;
import com.demo.exchangeRate.DTO.FullBankExchangeRateDTO;
import com.demo.exchangeRate.mapper.BelarusBankMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BelarusBankService implements Bank {
    private final BelarusBankMapper belarusBankMapper;
    private final MessageCreatorService messageService;

    @Override
    public String getExchangeRate(String bankUrl) {
        List<BelarusBankDTO> belarusBankFinance = new ArrayList<>();
        try {
            belarusBankFinance = new ObjectMapper().readValue(new URL(bankUrl),
                    new TypeReference<List<BelarusBankDTO>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertDtoToString(convertToFullExchangeRateDTO(belarusBankFinance));
    }

    private FullBankExchangeRateDTO convertToFullExchangeRateDTO(List<BelarusBankDTO> belarusBankDTO) {
        return belarusBankMapper.convertToFullExchangeRateDTO(belarusBankDTO);
    }

    private String convertDtoToString(FullBankExchangeRateDTO fullExchangeRate) {
        return messageService.createAnswerWithFullExchangeRate(fullExchangeRate);
    }
}
