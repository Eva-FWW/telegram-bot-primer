package org.campus;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {
    public static final String BOT_TOKEN = "7150481816:AAHOpQWo2VApjNW3JmTLBxnm_eCevzJCgQk";
    public static LogService logService = new LogService();
    public static Map<Long, TGUser> users = new HashMap<>();
    public static TelegramBot telegramBot = new TelegramBot(BOT_TOKEN);

    public static void main(String[] args) {
        System.out.println("Hello world!");

        telegramBot.setUpdatesListener(list -> {
            list.forEach(update -> {
                logService.lod(update);
                SendMessage sendMessage = new SendMessage(update.message().from().id(), "Напиши какой твой любимый цвет");
                telegramBot.execute((sendMessage));
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }



}