package com.demo.exchangeRate.controller;

import com.demo.exchangeRate.controller.FinanceController;
import com.demo.exchangeRate.model.BankRate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageController {
    FinanceController<BankRate> financeController;

    public MessageController(FinanceController<BankRate> financeController) {
        this.financeController = financeController;
    }

    public BotApiMethod handleUpdate(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        if (message != null && message.hasText()) {
            switch (message.getText()) {

                case "/help":
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setChatId(String.valueOf(message.getChatId()));
                    sendMessage1.setText("dont worry");
                    return sendMessage1;
                case "BelarusBank":
                    return sendMsg(message, financeController.getFinance(), sendMessage);
                default:
                    SendMessage sendMessage2 = new SendMessage();
                    sendMessage2.setChatId(String.valueOf(message.getChatId()));
                    sendMessage2.setText("dont worry");
                    return sendMessage2;
            }
        }
        return null;
    }

    public SendMessage sendMsg(Message message, BankRate bankRate, SendMessage sendMessage) {
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText("<pre>     Покупка   Продажа</pre>\n" +
                "USD<pre> " + bankRate.getUSDPurchaseRate() + "</pre><pre>      " + bankRate.getEURSellingRate() + "</pre>"
                + "\nEUR<pre> " + bankRate.getEURPurchaseRate() + "</pre><pre>      " + bankRate.getEURSellingRate() + "</pre>");

        setButton(sendMessage);
        return sendMessage;
    }

    public void setButton(SendMessage sendMessage) {
        //инициализация клавиатуры
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //разметка для клавиатуры
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        //определение кому будет выводится клавиатура
        replyKeyboardMarkup.setSelective(true);
        //Подгон клавиатуры
        replyKeyboardMarkup.setResizeKeyboard(true);
        //выбор скрывать ли клавиатуру
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("BelarusBank"));
        keyboardFirstRow.add(new KeyboardButton("BNB"));
        keyboardFirstRow.add(new KeyboardButton("help"));

        keyboardRowsList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
    }

}