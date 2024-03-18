package org.bot.coincontext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;

public abstract class CoinParser {

    private final String URL = "https://www.coingecko.com/";

    public String startParse() throws IOException {
        Document doc = Jsoup.connect(URL).get();
        doc.select("p").forEach(System.out::println);

        Element priceLink = doc;
        return priceLink.toString();
    }
}
