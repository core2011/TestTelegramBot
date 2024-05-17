package com.mybottest;

import com.mybottest.config.BotConfig;
import com.mybottest.logic.BusinessLogic;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

public class MyBot extends TelegramLongPollingBot {

    private static final BotConfig botConfig = new BotConfig();
    private URL url;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        try {
            url = new URL(botConfig.getConfig("url"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            messageText = messageText.toUpperCase(Locale.ROOT);
            long chatId = update.getMessage().getChatId();

            Scanner scanner = new Scanner((InputStream) url.getContent());
            StringBuilder result = new StringBuilder();
            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }
            BusinessLogic businessLogic = new BusinessLogic(result.toString());


            switch (messageText) {
                case "/START" -> sendMessage(chatId, "HI " + update.getMessage().getChat().getFirstName());
                case "ALL" -> sendMessage(chatId, businessLogic.getAllCurrencyModelList().toString());
                default -> sendMessage(chatId, businessLogic.getCurrencyModelByShortName(messageText).toString());
            }

        }

    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage(
                String.valueOf(chatId),
                textToSend
        );
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
