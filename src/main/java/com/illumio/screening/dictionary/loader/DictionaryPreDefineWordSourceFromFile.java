package com.illumio.screening.dictionary.loader;

import com.illumio.screening.tokenreader.TokenReader;
import com.illumio.screening.tokenreader.TokenReaderImpl;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryPreDefineWordSourceFromFile implements DictionaryPreDefinedWordSource{
    TokenReader tokenReader;
    public DictionaryPreDefineWordSourceFromFile(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        tokenReader = new TokenReaderImpl(scanner);
    }

    @Override
    public TokenReader getTokenReader() {
        return this.tokenReader;
    }
}
