package com.voidworks.lastfm.command.module.prompt;

import com.voidworks.lastfm.communication.Communicator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DirectoryPrompt extends AbstractPrompt<String> {

    @Override
    public void execute() {
        Communicator.printMessage("\nPlease enter the name of the destination directory:");
        setData(new Scanner(System.in).next());
    }

    @Override
    public boolean isValid() {
        Path directory;
        try {
            directory = Paths.get(getData());
        } catch (Exception e) {
            Communicator.printError(e.getMessage());
            return false;
        }

        if (!Files.isDirectory(directory)) {
            Communicator.printError("Specified directory does not exist.");
            return false;
        }

        return true;
    }

}
