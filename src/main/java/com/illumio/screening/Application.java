package com.illumio.screening;

import com.illumio.screening.dictionary.Dictionary;
import com.illumio.screening.dictionary.factory.DictionaryFactory;
import com.illumio.screening.input.handler.InputHandler;
import com.illumio.screening.input.handler.factory.InputHandlerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Application {
    Dictionary dictionary;
    InputHandler inputHandler;
    Map<String, Integer> matchedWordFrequencyMap;
    public Application(Dictionary dictionary, InputHandler inputHandler) {
        this.dictionary = dictionary;
        this.inputHandler  = inputHandler;
        this.matchedWordFrequencyMap = new HashMap<>();
        this.computeResult();
    }

    private Map<String, Integer> computeResult() {
        Map<String, Integer> result = new HashMap<>();
        while(inputHandler.hasNext()) {
            String word = inputHandler.nextWord();
            if(dictionary.contains(word)) {
                result.put(word, result.getOrDefault(word, 0) + 1);
            }
        }
        Comparator<String> comparator = (s1, s2) -> {
            if( result.getOrDefault(s2, 0) - result.getOrDefault(s1, 0) == 0) {
                return s1.compareTo(s2);
            }
            return result.getOrDefault(s2, 0) - result.getOrDefault(s1, 0);
        };
        this.matchedWordFrequencyMap = new TreeMap<>(comparator);
        this.matchedWordFrequencyMap.putAll(result);
        return matchedWordFrequencyMap;
    }

    public Map<String, Integer> getResult() {
        return this.matchedWordFrequencyMap;
    }

    public void showResult() {

        int columnWidth = 30;
        for(Map.Entry<String, Integer> entry : this.matchedWordFrequencyMap.entrySet()) {
            String key = entry.getKey();
            if(key.length() < columnWidth) {
                while(key.length() < columnWidth) {
                    key = key +" ";
                }
            }
            System.out.println(key + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.out.println("Missing predefined words and/or input file. The first argument is the exact file path for input. The second argument is the exact file path for predefined words.");
            return;
        }
        String inputFile = args[0];
        String predefinedWordsFile = args[1];
        Dictionary dictionary = DictionaryFactory.getDictionaryFromFile(predefinedWordsFile);
        InputHandler inputHandler = InputHandlerFactory.getFileBasedInputHandler(new File(inputFile));

        Application app = new Application(dictionary, inputHandler);
        app.showResult();



    }
}