package com.demo.exchangeRate.mapper;

import com.demo.exchangeRate.DTO.NationalBank;
import com.demo.exchangeRate.DTO.NationalBankViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NationalBankMapper {

    public NationalBankViewDTO convertToNationalBankViewDTO(NationalBank[] nationalBanks) {
        NationalBankViewDTO nationalBankViewDTO = new NationalBankViewDTO();
        for (NationalBank nb : nationalBanks) {
            if (nb.getCurAbbreviation().equals("USD")) {
                nationalBankViewDTO.setUsd(nb.getCurOfficialRate());
            } else if (nb.getCurAbbreviation().equals("EUR")) {
                nationalBankViewDTO.setEur(nb.getCurOfficialRate());
            }
        }
        return nationalBankViewDTO;
    }
}
