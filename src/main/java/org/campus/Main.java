package org.campus;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
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
                newMessageFromUser(update);
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public static void newMessageFromUser (Update update) {
        logService.lod(update);

        Long userId = update.message().from().id();
        if (startMessage(userId)) return;

        SendMessage sendMessage = new SendMessage(update.message().from().id(), "Напиши какой твой любимый цвет");
        telegramBot.execute((sendMessage));
    }

    public static Boolean startMessage(Long userId){
        if(users.containsKey(userId) == false) {
            users.put(userId, new TGUser(userId));

            SendMessage sendMessage = new SendMessage(userId, Texts.HELLO_MESSAGE);
            sendMessage.parseMode(ParseMode.Markdown); //чтобы тескт был жирным

            Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(Texts.HELLO_MESSAGE_BUTTON)
                    .oneTimeKeyboard(true)
                    .resizeKeyboard(true)
                    .selective(true);

            sendMessage.replyMarkup(replyKeyboardMarkup);
            telegramBot.execute(sendMessage);
            return true;
        }

        return false;
    }



}