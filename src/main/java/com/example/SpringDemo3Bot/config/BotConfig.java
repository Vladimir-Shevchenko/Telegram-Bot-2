package com.example.SpringDemo3Bot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("application.properties")
@Data
public class BotConfig {

    @Value("${bot.name}")
    String botName;
    @Value("${bot.key}")
    String token;
}
