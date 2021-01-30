package com.voidworks.lastfm.prompt;

import com.voidworks.lastfm.communication.Communicator;
import com.voidworks.lastfm.prompt.enumerator.DownloadOptionType;

import java.util.Scanner;

public class DownloadOptionPrompt extends AbstractPrompt<String> implements Communicator {
    @Override
    public void execute() {
        printMessage("\nYou can choose to download your scrobble data as follows:");
        printMessage("1) Download a \"sanitised\" large dump file version in CSV format with quotes as text qualifiers.");
        printMessage("2) Download a \"sanitised\" paginated version in CSV format with quotes as text qualifiers.");
        printMessage("3) Download a \"sanitised\" paginated version in JSON format.");
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
