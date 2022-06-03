package com.demo.exchangeRate.controller;

import com.demo.exchangeRate.DTO.BelarusBankViewDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class BelarusBankController {

    public BelarusBankViewDTO getExchangeRate(Map<String, String> exchangeRateReceivingAddresses, String bank) {
        String url = exchangeRateReceivingAddresses.get(bank);
        Optional<BelarusBankViewDTO> belarusBankFinance = Optional.empty();
        try {
            belarusBankFinance = Arrays.stream(new ObjectMapper().
                    readValue(new URL(url), BelarusBankViewDTO[].class)).findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return belarusBankFinance.orElseGet(BelarusBankViewDTO::new);
    }
}
