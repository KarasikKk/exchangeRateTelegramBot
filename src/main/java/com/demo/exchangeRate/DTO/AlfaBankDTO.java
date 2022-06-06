package com.demo.exchangeRate.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlfaBankDTO {
    private double sellRate;
    private String sellIso;
    private double buyRate;
    private String name;
}
