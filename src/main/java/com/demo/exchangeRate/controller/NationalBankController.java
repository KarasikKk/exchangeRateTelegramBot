package com.demo.exchangeRate.controller;

import com.demo.exchangeRate.DTO.NationalBank;
import com.demo.exchangeRate.DTO.NationalBankViewDTO;
import com.demo.exchangeRate.mapper.NationalBankMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NationalBankController {
    private final NationalBankMapper nbMapper;

    public NationalBankViewDTO getExchangeRate(Map<String, String> exchangeRateReceivingAddresses, String bank) {
        String url = exchangeRateReceivingAddresses.get(bank);
        NationalBank[] nationalBanks = null;
        try {
            nationalBanks = new ObjectMapper().readValue(new URL(url), NationalBank[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertToViewDTO(nationalBanks);
    }

    private NationalBankViewDTO convertToViewDTO(NationalBank[] nationalBanks) {
        return nbMapper.convertToNationalBankViewDTO(nationalBanks);
    }
}
