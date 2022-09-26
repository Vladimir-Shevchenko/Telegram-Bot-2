package com.example.SpringDemo3Bot.service;

import com.example.SpringDemo3Bot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

   public TelegramBot(BotConfig config){
       this.config = config;
   }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
if (update.hasMessage()&&update.getMessage().hasText()) {
    String text = update.getMessage().getText();
    long chatId = update.getMessage().getChatId();
            

    switch (text) {
        case "/start":
            startCommandRecieve(chatId, update.getMessage().getChat().getFirstName());
            break;
        default: sendMessage(chatId, "Sorry, this command doesn't support!");
    }//switch
}//if
    }


    private void startCommandRecieve(long chatId, String name) {
       String answer = "Hi "+name+" nice to meet you!";
       log.info("Answered to "+name);
        sendMessage(chatId,answer);
    }

    private void sendMessage(long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        }catch(TelegramApiException e){
            throw new RuntimeException(e);
        }

    }
}
