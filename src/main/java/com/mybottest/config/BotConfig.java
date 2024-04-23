package com.mybottest.config;

import lombok.Data;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

@Data
public class BotConfig {
    String botName;
    String token;

    public BotConfig() {
        botName = getConfig("bot.name");
        token = getConfig("bot.token");
    }

    public String getConfig(String configName){
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            config.load("application.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return config.getString(configName);
    }

}
