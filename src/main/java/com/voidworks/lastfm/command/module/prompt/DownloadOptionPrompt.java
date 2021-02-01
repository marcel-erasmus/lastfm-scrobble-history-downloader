package com.voidworks.lastfm.command.module.prompt;

import com.voidworks.lastfm.command.module.prompt.enumerator.DownloadOptionType;

import java.util.Scanner;

public class DownloadOptionPrompt extends AbstractPrompt<String> {
    @Override
    public void execute() {
        printMessage("\nYou can choose to download your scrobble data as follows:");
        printMessage("1) Slimmed-down scrobble data stored in a single large file (CSV format, quotes as text qualifiers).");
        printMessage("2) Slimmed-down scrobble data stored in various paginated files (CSV format, quotes as text qualifiers).");
        printMessage("3) Slimmed-down scrobble data stored in various paginated files (JSON format).");
        printMessage("4) Download verbatim as the Last.fm API returns it in JSON format (paginated).");
        printMessage("\nThe download might take a while depending on how much scrobble history needs to be downloaded.");
        printMessage("\nPlease enter the number (1, 2, 3 or 4) representing how you want your scrobble data downloaded:");

        setData(new Scanner(System.in).next());
    }

    @Override
    public boolean isValid() {
        int downloadMode;
        try {
            downloadMode = Integer.parseInt(getData());
        } catch (Exception e) {
            printError(e.getMessage());
            return false;
        }

        if (DownloadOptionType.of(downloadMode) == null) {
            printError("Only 1, 2, 3 or 4 is accepted for this prompt.");
            return false;
        }

        return true;
    }
}
