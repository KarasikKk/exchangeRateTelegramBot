package com.demo.exchangeRate.controller;

import com.demo.exchangeRate.model.BelarusBankFinanceRate;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

@Component
public class FinanceController<T> {

    public T getFinance(){
        Optional<BelarusBankFinanceRate> belarusBankFinance = Optional.empty();
        try {
            belarusBankFinance = Arrays.stream(new ObjectMapper().
                    readValue(new URL("https://belarusbank.by/api/kursExchange?city=Минск"), BelarusBankFinanceRate[].class)).findFirst();
        } catch (IOException e) {
            return (T)belarusBankFinance.get();

        }

        return (T) belarusBankFinance.get();
    }

}
