package com.demo.exchangeRate.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "telegrambot")
public class TelegramBotConfig {
    String webHookPath;
    String userName;
    String botToken;
    String belarusBankPath;
    String alfaBankPath;
    String helpAnswer;
    String defaultAnswer;
    String NationalBankPath;
}