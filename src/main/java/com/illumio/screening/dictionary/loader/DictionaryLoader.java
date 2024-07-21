package com.illumio.screening.dictionary.loader;

import com.illumio.screening.dictionary.Dictionary;
import com.illumio.screening.dictionary.InMemoryDictionary;
import com.illumio.screening.tokenreader.TokenReader;


public class DictionaryLoader {
    public static Dictionary loadDictionary(TokenReader tokenReader) {
        Dictionary inMemoryDictionary = new InMemoryDictionary();
        while (tokenReader.hasNext()) {
            inMemoryDictionary.addWord(tokenReader.next().toLowerCase());
        }
        return inMemoryDictionary;
    }
}
