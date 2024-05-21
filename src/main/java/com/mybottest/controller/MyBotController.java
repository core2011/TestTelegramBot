package com.mybottest.controller;

import com.mybottest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Locale;

@Controller
public class MyBotController extends TelegramLongPollingBot {

    private final String botUserName;
    private final String botToken;

    @Autowired
    private final UserController userController;

    public MyBotController() {
        userController = new UserController();
        botUserName = "my_currency_test_bot";
        botToken = "6931639419:AAEQw-ILYaIrQJrWU5wnUnI_JB8NSB9BSXk";
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var firstName = update.getMessage().getChat().getFirstName();
        var userName = update.getMessage().getChat().getUserName();
        var messageText = update.getMessage().getText().toUpperCase(Locale.ROOT);
        var hasText = update.hasMessage() && update.getMessage().hasText();
        var newTextMessage = "test";
        User user;

        if (hasText) {
            switch (messageText) {
                case "/START" -> newTextMessage = "Hi " + userName;
                case "/ADDME" -> {
                    user = userController.saveUserIfNoExist(chatId, userName, firstName);
                    newTextMessage = user.toString();
                }

            }


        }

        sendMessage(chatId, newTextMessage);
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

    private String askPhoneNumber(Update update, Long chatId){
        sendMessage(chatId, "Send your phone number begin from 0");
        return update.getMessage().getText();
    }
}
