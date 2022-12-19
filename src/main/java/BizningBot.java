import db.ConnectingDB;
import model.QuizModel;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BizningBot extends TelegramLongPollingBot {
    int sanoq = 0;
    String savol = "";
    List<String> javob = new ArrayList<>();

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
        Message message = update.getMessage();
        int msgId = update.getMessage().getMessageId();
        long chatId = update.getMessage().getChatId();
        String msg = update.getMessage().getText();
        List<QuizModel> quizes = ConnectingDB.connecting();
        for (int i=0;i<quizes.size();i++) {
            SendPoll poll = new SendPoll();
            poll.setQuestion(quizes.get(i).getSavol());
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
            poll.setOpenPeriod(15);
            poll.setChatId(update.getMessage().getChatId());

            try {
                execute(poll);
                if (true) {
                    return;
                }
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (msg == "boshlash"){
            if (sanoq == 0){
                savol = msg;
                javob.clear();
                sanoq ++;
            }else
            if (sanoq > 0 && sanoq <= 4 ) {
                javob.add(msg);
                sanoq ++;
            }else {
                sanoq = 0;
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
