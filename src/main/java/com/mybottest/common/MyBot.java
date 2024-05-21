package com.mybottest.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyBot extends TelegramLongPollingBot {

//    @Value("${bot.user.name}")
    private final String botUserName
//            = "my_currency_test_bot"
        ;

//    @Value("${bot.token}")
    private final String botToken
//            = "6931639419:AAEQw-ILYaIrQJrWU5wnUnI_JB8NSB9BSXk"
        ;

    public MyBot(){
        botUserName = "my_currency_test_bot";
        botToken = "6931639419:AAEQw-ILYaIrQJrWU5wnUnI_JB8NSB9BSXk";
        System.out.println("userName - " + botUserName);
        System.out.println("botToken - " + botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var name = update.getMessage().getChat().getFirstName();


        SendMessage sendMessage = new SendMessage(
                String.valueOf(chatId),
                "Hi " + name
        );
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
