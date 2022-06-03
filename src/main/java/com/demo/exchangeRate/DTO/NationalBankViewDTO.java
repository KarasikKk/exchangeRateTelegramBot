package com.demo.exchangeRate.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class NationalBankViewDTO {
    private double usd;
    private double eur;
}
