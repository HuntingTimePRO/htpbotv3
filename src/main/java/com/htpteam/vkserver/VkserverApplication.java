package com.htpteam.vkserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.net.ServerSocket;

@SpringBootApplication
public class VkserverApplication {

	public static void main(String[] args){
		SpringApplication.run(VkserverApplication.class, args);
		try {
			HTPBot bot = new HTPBot();
			TelegramBotsApi bots = new TelegramBotsApi(DefaultBotSession.class);

			bots.registerBot(bot);
			System.out.println("BotStarted");
		}catch (TelegramApiException e)
		{
			System.out.println(e);
		}

	}



}
