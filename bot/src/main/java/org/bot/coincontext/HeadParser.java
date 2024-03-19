package org.bot.coincontext;

import org.bot.botsettings.BotSettings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import static org.bot.botsettings.functions.BotMessage.sendCurrencyNotFound;

public class HeadParser extends BotSettings {
    private static final String BASE_URL = "https://coinmarketcap.com/currencies/";

    public String startParse(String coinName, String chatId) {
        String url = BASE_URL + coinName;

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                    .get();

            Elements getPrice = doc.select("div.css-1bwgsh3");
            if (!getPrice.isEmpty()) {
                return getPrice.first().text();
            } else {
                sendCurrencyNotFound(chatId);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while parsing coin data", e);
        }
        return "Data not found!";
    }
}