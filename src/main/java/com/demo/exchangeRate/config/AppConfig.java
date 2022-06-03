package com.demo.exchangeRate.config;

import com.demo.exchangeRate.bot.TelegramBot;
import com.demo.exchangeRate.controller.MessageController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import java.util.HashMap;

@Configuration
public class AppConfig {
    private final TelegramBotConfig botConfig;

    public AppConfig(TelegramBotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
    }

    @Bean
    public TelegramBot springWebhookBot(SetWebhook setWebhook, MessageController messageController) {
        TelegramBot bot = new TelegramBot(messageController, setWebhook);
        botPropertyInjection(bot);
        return bot;
    }

    private void botPropertyInjection(TelegramBot bot) {
        bot.setBotToken(botConfig.getBotToken());
        bot.setBotPath(botConfig.getWebHookPath());
        bot.setExchangeRateReceivingAddresses(new HashMap<>());
        bot.getExchangeRateReceivingAddresses().put("BelarusBank", botConfig.getBelarusBankPath());
        bot.getExchangeRateReceivingAddresses().put("AlfaBank", botConfig.getAlfaBankPath());
        bot.getExchangeRateReceivingAddresses().put("NationalBank", botConfig.getNationalBankPath());
        bot.getExchangeRateReceivingAddresses().put("defaultAnswer", botConfig.getDefaultAnswer());
        bot.getExchangeRateReceivingAddresses().put("help", botConfig.getHelpAnswer());
    }
}
