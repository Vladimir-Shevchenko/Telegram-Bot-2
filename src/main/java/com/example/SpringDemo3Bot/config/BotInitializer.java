package com.example.SpringDemo3Bot.config;

import com.example.SpringDemo3Bot.service.TelegramBot;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


//инициализацтя бота
//listener не разбирали
@Component
@Slf4j
public class BotInitializer {
    @Autowired
    TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try{telegramBotsApi.registerBot(bot);}catch (TelegramApiException e){}

    }

}
