package com.illumio.screening;

import com.illumio.screening.dictionary.Dictionary;
import com.illumio.screening.dictionary.loader.DictionaryLoader;
import com.illumio.screening.input.handler.InputHandler;
import com.illumio.screening.tokenreader.TokenReader;
import com.illumio.screening.tokenreader.TokenReaderImpl;
import org.junit.Test;

import java.util.Map;
import java.util.Scanner;

public class ApplicationTest {
    String wordList1 = "Name Detect AI";
    String inputString1 = "Detecting first names is tricky to do even with AI. how do you say a street name is not a first name?";
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
        System.out.println(result);

    }

}
