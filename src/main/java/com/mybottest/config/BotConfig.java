package com.mybottest.config;

import lombok.Data;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

@Data
public class BotConfig {
    String botName;
    String token;



    public BotConfig() {
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            config.load("application.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        botName = config.getString("bot.name");
        token = config.getString("bot.token");
    }
}
