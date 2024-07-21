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
        while(inputHandler.hasNext()) {
            String word = inputHandler.nextWord();
            if(dictionary.contains(word)) {
                matchedWordFrequencyMap.put(word, matchedWordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        return matchedWordFrequencyMap;
    }

    public Map<String, Integer> getResult() {
        return this.matchedWordFrequencyMap;
    }

    public void showResult() {
        Comparator<String> comparator = (s1, s2) -> this.matchedWordFrequencyMap.getOrDefault(s2, 0) - matchedWordFrequencyMap.getOrDefault(s1, 0);
        Map<String, Integer> treeMap = new TreeMap<>(comparator);
        treeMap.putAll(this.matchedWordFrequencyMap);
        int columnWidth = 30;
        for(Map.Entry<String, Integer> entry : treeMap.entrySet()) {
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
            System.out.println("Missing predefined words and input file");
            return;
        }
        String inputFile = args[0];
        String predefinedWordsFile = args[1];
        Dictionary dictionary = DictionaryFactory.getDictionaryFromFile(predefinedWordsFile);
        InputHandler inputHandler = InputHandlerFactory.getFileBasedInputHandler(new File(inputFile));
//        Map<String, Integer> matchedWordFrequencyMap = new HashMap<>();
//        while(inputHandler.hasNext()) {
//            String word = inputHandler.nextWord();
//            if(dictionary.contains(word)) {
//                matchedWordFrequencyMap.put(word, matchedWordFrequencyMap.getOrDefault(word, 0) + 1);
//            }
//        }

        Application app = new Application(dictionary, inputHandler);
        app.showResult();



    }
}