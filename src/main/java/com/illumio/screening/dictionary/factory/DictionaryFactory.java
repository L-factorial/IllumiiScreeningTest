package com.illumio.screening.dictionary.factory;

import com.illumio.screening.dictionary.Dictionary;
import com.illumio.screening.dictionary.loader.DictionaryLoader;
import com.illumio.screening.dictionary.loader.DictionaryPreDefineWordSourceFromFile;
import com.illumio.screening.dictionary.loader.DictionaryPreDefinedWordSource;

import java.io.File;
import java.io.IOException;

public class DictionaryFactory {
    public static Dictionary getDictionaryFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        DictionaryPreDefinedWordSource dictionaryPreDefinedWordSource = new DictionaryPreDefineWordSourceFromFile(file);
        Dictionary dictionary = DictionaryLoader.loadDictionary(dictionaryPreDefinedWordSource.getTokenReader());
        return dictionary;
    }
}
