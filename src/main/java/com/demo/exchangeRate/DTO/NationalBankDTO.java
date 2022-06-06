package com.demo.exchangeRate.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NationalBankDTO {
    @JsonProperty(value = "Cur_ID")
    private int curID;
    @JsonProperty(value = "Date")
    private String date;
    @JsonProperty(value = "Cur_Abbreviation")
    private String curAbbreviation;
    @JsonProperty(value = "Cur_OfficialRate")
    private double curOfficialRate;
}
