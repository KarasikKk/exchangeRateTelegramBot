package com.demo.exchangeRate.service;

import com.demo.exchangeRate.DTO.AlfaBankDTO;
import com.demo.exchangeRate.DTO.FullBankExchangeRateDTO;
import com.demo.exchangeRate.mapper.AlfaBankMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlfaBankService implements Bank{
    private final AlfaBankMapper alfaBankMapper;
    private final MessageCreatorService messageService;

    @Override
    public String getExchangeRate(String bankUrl) {
        ObjectMapper mapper = new ObjectMapper();
        List<AlfaBankDTO> alfaBankDTO = new ArrayList<>();
        try {
            JsonNode response = mapper.readTree(new URL(bankUrl)).path("rates");
            CollectionType collectionType =
                    TypeFactory
                            .defaultInstance()
                            .constructCollectionType(List.class, AlfaBankDTO.class);

            alfaBankDTO = mapper.reader(collectionType).readValue(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertDtoToString(convertToDTO(alfaBankDTO));
    }

    private FullBankExchangeRateDTO convertToDTO(List<AlfaBankDTO> alfaBankDTO) {
        return alfaBankMapper.convertToFullExchangeRateDTO(alfaBankDTO);
    }

    private String convertDtoToString(FullBankExchangeRateDTO fullExchangeRate){
       return messageService.createAnswerWithFullExchangeRate(fullExchangeRate);
    }
}
