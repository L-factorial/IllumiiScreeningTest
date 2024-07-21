package com.illumio.screening.dictionary;

import java.util.HashSet;
import java.util.Set;

public class InMemoryDictionary implements Dictionary{
    Set<String> words;
    public InMemoryDictionary(){
        words = new HashSet<String>();
    }
    @Override
    public void addWord(String word) {
        this.words.add(word);
    }

    @Override
    public void removeWord(String word) {
        this.words.remove(word);
    }

    @Override
    public boolean contains(String word) {
        return this.words.contains(word);
    }
}
