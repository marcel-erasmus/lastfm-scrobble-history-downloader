package com.voidworks.lastfm.command;

import com.voidworks.lastfm.command.generator.SanitisedContentGenerator;
import com.voidworks.lastfm.formatter.ScrobbleCsvFormatter;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;

public class SanitisedCsvPersistCommand extends AbstractPersistCommand {

    public SanitisedCsvPersistCommand(String directory, String user, int page, int pageSize) {
        super(directory, user, page, pageSize);
    }

    public void execute() {
        LastfmServiceResponse response = getServiceResponse(user, page, pageSize);

        if (response.getCode() != 200) {
            return;
        }

        String fileData = new SanitisedContentGenerator().generateContent(
                "",
                "",
                System.lineSeparator(),
                new ScrobbleCsvFormatter(),
                response.getJson());

        persistFileContent(fileData, ".csv");

        if (response.getPage() < response.getTotalPages()) {
            setNext(new SanitisedCsvPersistCommand(directory, user, response.getPage() + 1, pageSize));
        }

        printProgressMessage(response.getTotalPages());
    }

}
