package com.illumio.screening.tokenreader;

import java.util.Scanner;

public class TokenReaderImpl implements TokenReader {
    private Scanner scanner;
    public TokenReaderImpl(Scanner sc) {
        this.scanner = sc;
    }

    @Override
    public String next() {
        if(scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }


}
