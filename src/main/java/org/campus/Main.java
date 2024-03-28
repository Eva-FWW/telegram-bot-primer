package org.campus;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
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

        //Вопросы
        TGUser tgUser = users.get(userId);
        if (questionMessage(userId, tgUser, update) == true) return;

        resultMessage(userId, tgUser);
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

    public static Boolean questionMessage (Long userId, TGUser user, Update update) {
        for(Question question : user.getQuestions()){
            if(question.getSendQuestionToUser() == true){
                if (question.getHasAnswer() == false){
                    question.setHasAnswer(true);

                    for(Answer answer: question.getAnswers()){
                        if(answer.getText().equals(update.message().text())){
                            if (answer.getLanguageType().equals(LanguageType.QA)){
                                user.setBackendPoint(user.getBackendPoint() + 1);
                            }

                            if (answer.getLanguageType().equals(LanguageType.FRONTEND)){
                                user.setFrontendPoint(user.getFrontendPoint() + 1);
                            }

                            if (answer.getLanguageType().equals(LanguageType.BACKEND)){
                                user.setQaPoint(user.getQaPoint() + 1);
                            }
                        }
                    }
                }
            }

            if(question.getSendQuestionToUser() == false){
                question.setSendQuestionToUser(true);
                SendMessage sendMessage = new SendMessage(userId, question.getText());
                sendMessage.parseMode(ParseMode.Markdown);

                String[][] keyButtons = new String[4][1];
                for(int i=0; i< question.getAnswers().size(); i++){
                    keyButtons[i][0] = question.getAnswers().get(i).getText();
                }

                Keyboard keyboard = new ReplyKeyboardMarkup(keyButtons)
                        .oneTimeKeyboard(true)
                        .resizeKeyboard(true)
                        .selective(true);
                sendMessage.replyMarkup(keyboard);
                telegramBot.execute(sendMessage);
                return true;
            }
        }

        return false;
    }

    public static void resultMessage(Long userId,TGUser user){
        telegramBot.execute(new SendMessage(userId, Texts.RESULT_MESSAGE));
        String resultMessage = "";

        if(user.getBackendPoint() >= user.getFrontendPoint() && user.getBackendPoint() >= user.getQaPoint()){
            resultMessage = Texts.RESULT_MESSAGE_JAVA;
        }

        if(user.getQaPoint() >= user.getFrontendPoint() && user.getQaPoint() >= user.getBackendPoint()){
            resultMessage = Texts.RESULT_MESSAGE_FRONTEND;
        }

        if(user.getFrontendPoint() >= user.getBackendPoint() && user.getFrontendPoint() >= user.getQaPoint()){
            resultMessage = Texts.RESULT_MESSAGE_QA;
        }

        SendMessage sendMessage = new SendMessage(userId, resultMessage);
        sendMessage.parseMode(ParseMode.Markdown);

        Keyboard replyKeyboard = new ReplyKeyboardMarkup(Texts.RESULT_MESSAGE_RESTART)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);

        sendMessage.replyMarkup(replyKeyboard);
        telegramBot.execute(sendMessage);

        users.put(userId, new TGUser(userId));
    }
}