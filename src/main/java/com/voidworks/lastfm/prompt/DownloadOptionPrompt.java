package com.voidworks.lastfm.prompt;

import com.voidworks.lastfm.communication.Communicator;
import com.voidworks.lastfm.prompt.enumerator.DownloadOptionType;

import java.util.Arrays;
import java.util.Scanner;

public class DownloadOptionPrompt extends AbstractPrompt<String> implements Communicator {
    @Override
    public void execute() {
        printMessage("\nYou can choose to download your scrobble data as follows:");
        printMessage("1) Download verbatim as the Last.fm API returns it in JSON format.");
        printMessage("2) Download a \"sanitised\" version in JSON format.");
        printMessage("3) Download a \"sanitised\" version in CSV format with quotes as text qualifiers.");
        printMessage("\nNote that all downloads are paginated so as to keep the download file sizes manageable.");
        printMessage("If you want a large dump file you can always run the download and aggregate the files with another tool.");
        printMessage("\nPlease enter the number (1, 2 or 3) representing how you want your scrobble data downloaded:");

        setData(new Scanner(System.in).next());
    }

    @Override
    public boolean isValid() {
        Integer downloadMode;
        try {
            downloadMode = Integer.parseInt(getData());
        } catch (Exception e) {
            printError(e.getMessage());
            return false;
        }

        if (Arrays.asList(DownloadOptionType.values()).contains(downloadMode)) {
            printError("Only 1, 2 or 3 is accepted for this prompt.");
            return false;
        }

        return true;
    }
}
