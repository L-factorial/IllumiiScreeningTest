package com.illumio.screening;

import com.illumio.screening.dictionary.Dictionary;
import com.illumio.screening.dictionary.loader.DictionaryLoader;
import com.illumio.screening.input.handler.InputHandler;
import com.illumio.screening.tokenreader.TokenReader;
import com.illumio.screening.tokenreader.TokenReaderImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ApplicationTest {
    String wordList1 = "Name Detect AI yak";
    String inputString1 = "Detecting first ai ai ai AI names is tricky to do even with AI. how do yak yak yak you say a street name is not a first name?";
    @Test
    public void applicationTest1(){
        Scanner wordList1Scanner = new Scanner(wordList1);
        Scanner inputString1Scanner = new Scanner(inputString1);

        TokenReader wordList1TokenReader = new TokenReaderImpl(wordList1Scanner);
        TokenReader inputString1TokenReader = new TokenReaderImpl(inputString1Scanner);

        Dictionary dictionaryLoader = DictionaryLoader.loadDictionary(wordList1TokenReader);
        InputHandler inputHandler = new InputHandler(inputString1TokenReader);

        Application application = new Application(dictionaryLoader, inputHandler);
        Map<String, Integer> result = application.getResult();

        Map<String, Integer> expectedResult = Map.of("ai", 5, "name", 2, "yak", 3);
        Comparator<String> comparator = (s1, s2) -> {
            if( expectedResult.getOrDefault(s2, 0) - expectedResult.getOrDefault(s1, 0) == 0) {
                return s1.compareTo(s2);
            }
            return expectedResult.getOrDefault(s2, 0) - expectedResult.getOrDefault(s1, 0);
        };
        Map<String, Integer> sortedExpectedResult = new TreeMap<>(comparator);
        sortedExpectedResult.putAll(expectedResult);
        Iterator<Map.Entry<String, Integer>> iterator1 = sortedExpectedResult.entrySet().iterator();
        Iterator<Map.Entry<String, Integer>> iterator2 = result.entrySet().iterator();
        while(iterator2.hasNext() && iterator2.hasNext()) {
            Map.Entry<String, Integer> entry2 = iterator2.next();
            Map.Entry<String, Integer> entry1 = iterator1.next();
            Assert.assertEquals(entry1.getKey(), entry2.getKey());
            Assert.assertEquals(entry1.getValue(), entry2.getValue());
        }
        Assert.assertFalse(iterator2.hasNext());
        Assert.assertFalse(iterator1.hasNext());


    }

}
