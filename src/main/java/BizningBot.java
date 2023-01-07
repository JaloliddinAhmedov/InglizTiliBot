import db.ConnectingDB;
import model.QuizModel;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonPollType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BizningBot extends TelegramLongPollingBot {
    int sanoq = 0;
    String savol = "";
    List<String> javob = new ArrayList<>();
    Map<Long, Integer> map = new HashMap<>();
    List<QuizModel> quizes = new ArrayList<>();
    int turn  = 0;
    int togriSoni = 0;
    String username = "";
    long chatId = 0;

    final static int TIME = 15;

    @Override
    public String getBotUsername() {
        return "jaloliddin_darslari_bot";
    }

    @Override
    public String getBotToken() {
        return "5805017399:AAGmHsFdlzEaPOs5Cgb2ganiTPNlMLx2YMk";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()){
            String msg = update.getMessage().getText();
            chatId = update.getMessage().getChatId();
            if (msg.equals("boshlash")){
                sendQuiz(chatId);
            }
        }

        SendAudio sendAudio = new SendAudio();
        InputFile inputFile = new InputFile();
        inputFile.setMedia("https:\\music.yandex.ru\\artist\\291283");
        sendAudio.setAudio(inputFile);
        sendAudio.setChatId(chatId);
        sendAudio.setCaption("Listen and hava a good time");

        try {
            execute(sendAudio);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        /*if (update.hasPollAnswer()){
            PollAnswer pollAnswer = update.getPollAnswer();
            Poll poll = update.getPoll();
            System.out.println(pollAnswer);
            int answerId = pollAnswer.getOptionIds().get(0);

            if (poll.getCorrectOptionId() == answerId){
                togriSoni ++;
            }
        }*/
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
        turn = 0;
        for (int i=0;i<updates.size() && updates.size() >= 3;i++){
            Update update = updates.get(i);
            if (update.hasPollAnswer()){
                PollAnswer pollAnswer = update.getPollAnswer();

                int answerId = pollAnswer.getOptionIds().get(0);

                username = pollAnswer.getUser().getFirstName();
                if (quizes.get(turn).getTogri_javob_id() == ( answerId + 1 )){
                    togriSoni ++;
                }else{

                }
                turn ++;
            }
        }
        turn = 0;
        if(updates.size() >= 3){
            String message = "Dear " + username + "\nYou have found\n" + "✅ "+ togriSoni + " correct \n" + "❌ " + (20 - togriSoni) + " incorrect answers!\nI will send you a song for your attempt!";
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(message);
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            sendMessage.setChatId(156146644L);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

            SendAudio sendAudio = new SendAudio();
            InputFile inputFile = new InputFile();
            inputFile.setMedia(new File("C:\\Users\\JaloliddinAhmad\\Downloads\\yusuf-sahin-ft-ahsen-almaz-yandirdin-kalbimi.mp3"));
            sendAudio.setAudio(inputFile);
            sendAudio.setChatId(chatId);
            sendAudio.setCaption("Listen and hava a good time");

            try {
                execute(sendAudio);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void sendQuiz(long h_chatID){
        quizes = ConnectingDB.connecting();

        for (int i=0;i<quizes.size();i++) {
            SendPoll poll = new SendPoll();
            poll.setQuestion((i + 1) + ". " + quizes.get(i).getSavol());
            List<String> pollOptionList = new ArrayList<>();

            pollOptionList.add(quizes.get(i).getJavob1());
            pollOptionList.add(quizes.get(i).getJavob2());
            pollOptionList.add(quizes.get(i).getJavob3());
            pollOptionList.add(quizes.get(i).getJavob4());

            poll.setOptions(pollOptionList);
            poll.setType("quiz");
            poll.setCorrectOptionId(quizes.get(i).getTogri_javob_id() - 1);
            poll.setAllowMultipleAnswers(false);
            poll.setIsAnonymous(false);
            poll.setOpenPeriod(TIME);
            poll.setChatId(h_chatID);
            

            try {
                execute(poll);
                Thread.sleep(TIME * 1000);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void send1(){
        List<KeyboardRow> keyboard = new ArrayList<>();

        // First keyboard row
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Add buttons to the first keyboard row
        keyboardFirstRow.add(new KeyboardButton("Hi"));
        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton1.setText("Salom1");
        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText("Salom2");
        keyboardFirstRow.add(keyboardButton1);
        keyboardButton2.setRequestPoll(new KeyboardButtonPollType("keyboardButton1"));
        keyboardFirstRow.add(keyboardButton2);


        // Second keyboard row
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardSecondRow.add(new KeyboardButton("Help"));

        // Add all of the keyboard rows to the list
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // and assign this list to our keyboard
//        replyKeyboardMarkup.setKeyboard(keyboard);
//        try {
////            polling(chatId, update);
////            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
    }
}
