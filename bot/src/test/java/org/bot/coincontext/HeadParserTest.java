package org.bot.coincontext;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadParserTest {
    @ParameterizedTest
    @CsvSource({ "bitcoin, 1", "ethereum, 2", "dogecoin, 3"})
    void testStartParse(String coinName, String chatId) {
        HeadParser headParser = new HeadParser();
        String result = headParser.startParse(coinName, chatId);
        assertEquals("Data not found!", result, "Expected data not found!");
    }

}