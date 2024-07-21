package com.illumio.screening.input.handler.factory;

import com.illumio.screening.input.handler.InputHandler;
import com.illumio.screening.tokenreader.TokenReader;
import com.illumio.screening.tokenreader.TokenReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputHandlerFactory {
    public static InputHandler getFileBasedInputHandler(File file) throws FileNotFoundException {
        TokenReader tokenReader = new TokenReaderImpl(new Scanner(file));
        return new InputHandler(tokenReader);
    }
}
