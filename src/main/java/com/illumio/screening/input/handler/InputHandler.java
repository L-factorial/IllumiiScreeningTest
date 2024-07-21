package com.illumio.screening.input.handler;

import com.illumio.screening.tokenreader.TokenReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class InputHandler {
    TokenReader tokenReader;
    private static Set<Character> punctuations = new HashSet<Character>(List.of(',', '?', '.'));

    private static Function<String, String> punctuationCleanUp = s -> {
        if(punctuations.contains(s.charAt(s.length() - 1)) ) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    };
    private static Function<String, String> toLowerCase = s -> s.toLowerCase();

    private static List<Function<String, String>> wordTransformationRules = List.of(punctuationCleanUp, toLowerCase);

    public InputHandler(TokenReader tokenReader) {
        this.tokenReader = tokenReader;
    }
    public String nextWord() {
        if(!tokenReader.hasNext()) {
            return null;
        }
        String word = tokenReader.next();
        return wordTransform(word);
    }
    public boolean hasNext() {
        return tokenReader.hasNext();
    }


    private String wordTransform(String word) {
        if(word == null || word.length() == 0) {
            return word;
        }
        for(Function<String, String> rule : wordTransformationRules) {
            word = rule.apply(word);
        }
        return word;
    }
}
