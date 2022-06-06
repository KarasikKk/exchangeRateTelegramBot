package com.demo.exchangeRate.service;

import com.demo.exchangeRate.DTO.FullBankExchangeRateDTO;
import com.demo.exchangeRate.DTO.SellExchangeRateDTO;
import org.springframework.stereotype.Service;

@Service
public class MessageCreatorService {

    public String createAnswerWithFullExchangeRate(FullBankExchangeRateDTO bankRate) {
        return  "<pre>     Покупка   Продажа</pre>\n" +
                "USD<pre> " + bankRate.getUsdBuyRate() + "</pre><pre>      " + bankRate.getUsdSellRate() + "</pre>"
                + "\nEUR<pre> " + bankRate.getEurBuyRate() + "</pre><pre>      " + bankRate.getEurSellRate() + "</pre>";
    }

    public String createAnswerWithSellExchangeRate(SellExchangeRateDTO bankRate) {
       return "<pre>      Курс</pre>\n" +
               "USD<pre>  " + bankRate.getUsdSellRate()
               + "\nEUR  </pre> " + bankRate.getEurSellRate();
    }
}
