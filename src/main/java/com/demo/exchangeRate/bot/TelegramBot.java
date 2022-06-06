package com.demo.exchangeRate.bot;

import com.demo.exchangeRate.controller.MessageController;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.util.Map;

@Getter
@Setter
public class TelegramBot extends SpringWebhookBot {
    private String botPath;
    private String botUsername;
    private String botToken;
    private Map<String, String> exchangeRateReceivingAddresses;
    private MessageController messageController;

    public TelegramBot(MessageController messageController, DefaultBotOptions options, SetWebhook setWebhook) {
        super(options, setWebhook);
        this.messageController = messageController;
    }

    public TelegramBot(MessageController messageController, SetWebhook setWebhook) {
        super(setWebhook);
        this.messageController = messageController;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return messageController.handleUpdate(update, exchangeRateReceivingAddresses);
    }
}
