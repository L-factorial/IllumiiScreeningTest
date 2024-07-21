package com.illumio.screening.dictionary;

public interface Dictionary {
    void addWord(String word);
    void removeWord(String word);
    boolean contains(String word);
}
