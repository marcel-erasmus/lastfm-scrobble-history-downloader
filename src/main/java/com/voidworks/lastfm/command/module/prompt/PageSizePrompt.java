package com.voidworks.lastfm.command.module.prompt;

import java.util.Scanner;

public class PageSizePrompt extends AbstractPrompt<String> {
    @Override
    public void execute() {
        printMessage("\nPlease specify the page size that you want for your downloads (min 50 max 1000):");
        setData(new Scanner(System.in).next());
    }

    @Override
    public boolean isValid() {
        int pageSize;
        try {
            pageSize = Integer.parseInt(getData());
        } catch (Exception e) {
            printError(e.getMessage());
            return false;
        }

        if (pageSize < 50 || pageSize > 1000) {
            printError("Page size must be in the range of 50 to 1000.");
            return false;
        }

        return true;
    }
}
