package com.demo.exchangeRate.controller;

import com.demo.exchangeRate.DTO.BankRate;
import com.demo.exchangeRate.DTO.NationalBankViewDTO;
import com.demo.exchangeRate.config.ButtonsConfig;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageController {
    final BelarusBankController belarusBankController;
    final AlfaBankController alfaBankController;
    final NationalBankController nationalBankController;
    final ButtonsConfig buttonsConfig;

    public MessageController(BelarusBankController belarusBankController, AlfaBankController alfaBankController, NationalBankController nationalBankController, ButtonsConfig buttonsConfig) {
        this.belarusBankController = belarusBankController;
        this.alfaBankController = alfaBankController;
        this.nationalBankController = nationalBankController;
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
                return createAnswerWithExchangeRate(message, belarusBankController.
                        getExchangeRate(exchangeRateReceivingAddresses, message.getText()), sendMessage);
            case "AlfaBank":
                return createAnswerWithExchangeRate(message, alfaBankController.
                        getExchangeRate(exchangeRateReceivingAddresses, message.getText()), sendMessage);
            case "NationalBank":
                return createShortAnswer(message, nationalBankController.
                        getExchangeRate(exchangeRateReceivingAddresses, message.getText()), sendMessage);
            default:
                sendMessage.setText(exchangeRateReceivingAddresses.get("defaultAnswer"));
                return sendMessage;
        }
    }

    public SendMessage createAnswerWithExchangeRate(Message message, BankRate bankRate, SendMessage sendMessage) {
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText("<pre>     Покупка   Продажа</pre>\n" +
                "USD<pre> " + bankRate.getUSDPurchaseRate() + "</pre><pre>      " + bankRate.getUSDSellRate() + "</pre>"
                + "\nEUR<pre> " + bankRate.getEURPurchaseRate() + "</pre><pre>      " + bankRate.getEURSellRate() + "</pre>");
        setButton(sendMessage);
        return sendMessage;
    }

    public SendMessage createShortAnswer(Message message, NationalBankViewDTO bankRate, SendMessage sendMessage) {
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText("<pre>      Курс</pre>\n" +
                "USD<pre>  " + bankRate.getUsd()
                + "\nEUR  </pre> " + bankRate.getEur());
        setButton(sendMessage);
        return sendMessage;
    }

    public void setButton(SendMessage sendMessage) {
        buttonsConfig.setButton(sendMessage);
    }
}