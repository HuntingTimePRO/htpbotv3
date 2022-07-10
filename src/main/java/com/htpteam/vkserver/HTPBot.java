package com.htpteam.vkserver;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class HTPBot extends TelegramLongPollingBot {
    private  static String auth_token = "1760557470:AAGCb730koE7i06ZAyuPRPacWy1rfuhycgo";
    private static  String name = "htp_team_bot";


    @Override
    public String getBotUsername() {return name;}

    @Override
    public String getBotToken() {return auth_token;}

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getChatId());
    }
}
