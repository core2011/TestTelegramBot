package com.mybottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class ApplicationTelegramBot {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTelegramBot.class);
    }
}
