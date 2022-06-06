package com.demo.exchangeRate.controller;

import com.demo.exchangeRate.config.ButtonsConfig;
import com.demo.exchangeRate.service.AlfaBankService;
import com.demo.exchangeRate.service.BelarusBankService;
import com.demo.exchangeRate.service.NationalBankService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
public class MessageController {
    private final BelarusBankService belarusBankService;
    private final AlfaBankService alfaBankService;
    private final NationalBankService nationalBankService;
    private final ButtonsConfig buttonsConfig;

    public MessageController(BelarusBankService belarusBankService, AlfaBankService alfaBankService,
                             NationalBankService nationalBankService, ButtonsConfig buttonsConfig) {
        this.belarusBankService = belarusBankService;
        this.alfaBankService = alfaBankService;
        this.nationalBankService = nationalBankService;
        this.buttonsConfig = buttonsConfig;
    }

    public BotApiMethod handleUpdate(Update update, Map<String, String> exchangeRateReceivingAddresses) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (message != null && message.hasText()) {
            return getSendMessage(exchangeRateReceivingAddresses, message, sendMessage);
        } else {
            return sendMessage;
        }
    }

    private SendMessage getSendMessage(Map<String, String> exchangeRateReceivingAddresses, Message message,
                                       SendMessage sendMessage) {
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.enableHtml(true);
        switch (message.getText()) {

            case "help":
                sendMessage.setText(exchangeRateReceivingAddresses.get(message.getText()));
                return sendMessage;
            case "BelarusBank":
                return createAnswer(message,belarusBankService.
                        getExchangeRate(exchangeRateReceivingAddresses.get(message.getText())),sendMessage);
            case "AlfaBank":
                return createAnswer(message,alfaBankService.
                        getExchangeRate(exchangeRateReceivingAddresses.get(message.getText())),sendMessage);
            case "NationalBank":
                return createAnswer(message, nationalBankService.
                       getExchangeRate(exchangeRateReceivingAddresses.get(message.getText())), sendMessage);
            default:
                sendMessage.setText(exchangeRateReceivingAddresses.get("defaultAnswer"));
                return sendMessage;
        }
    }

    public SendMessage createAnswer(Message message, String exchangeRate, SendMessage sendMessage) {
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(exchangeRate);
        setButton(sendMessage);
        return sendMessage;
    }

    public void setButton(SendMessage sendMessage) {
        buttonsConfig.setButton(sendMessage);
    }
}
