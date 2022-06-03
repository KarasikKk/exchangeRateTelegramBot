package com.demo.exchangeRate.controller;

import com.demo.exchangeRate.DTO.AlfaBankDTO;
import com.demo.exchangeRate.DTO.AlfaBankViewDTO;
import com.demo.exchangeRate.mapper.AlfaBankMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AlfaBankController {
    private final AlfaBankMapper alfaBankMapper;

    public AlfaBankViewDTO getExchangeRate(Map<String, String> exchangeRateReceivingAddresses, String bank) {
        String url = exchangeRateReceivingAddresses.get(bank);
        ObjectMapper mapper = new ObjectMapper();
        List<AlfaBankDTO> alfaBankDTO = new ArrayList<>();
        try {
            JsonNode response = mapper.readTree(new URL(url)).path("rates");
            CollectionType collectionType =
                    TypeFactory
                            .defaultInstance()
                            .constructCollectionType(List.class, AlfaBankDTO.class);

            alfaBankDTO = mapper.reader(collectionType).readValue(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertToDTO(alfaBankDTO);
    }

    private AlfaBankViewDTO convertToDTO(List<AlfaBankDTO> alfaBankDTO) {
        return alfaBankMapper.convertToAlfaBankViwDTO(alfaBankDTO);
    }
}
