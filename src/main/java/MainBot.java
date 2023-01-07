import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MainBot {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotApi = new TelegramBotsApi(DefaultBotSession.class);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(new HttpHost("172.17.69.148" , 3128))
                    .build();

            DefaultBotOptions botOptions = new DefaultBotOptions();
            botOptions.setRequestConfig(requestConfig);

            telegramBotApi.registerBot(new BizningBot(botOptions));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
