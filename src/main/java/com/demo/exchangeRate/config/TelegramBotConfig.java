package com.demo.exchangeRate.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "telegrambot")
public class TelegramBotConfig {
    private  String webHookPath;
    private  String userName;
    private  String botToken;
    private  String belarusBankPath;
    private  String alfaBankPath;
    private  String helpAnswer;
    private  String defaultAnswer;
    private  String NationalBankPath;
}
