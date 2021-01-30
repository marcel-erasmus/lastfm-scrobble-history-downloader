package com.voidworks.lastfm.command;

import com.voidworks.lastfm.command.generator.SanitisedContentGenerator;
import com.voidworks.lastfm.formatter.ScrobbleJsonFormatter;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;

public class SanitisedJsonPersistCommand extends AbstractPersistCommand {

    public SanitisedJsonPersistCommand(String directory, String user, int page, int pageSize) {
        super(directory, user, page, pageSize);
    }

    public void execute() {
        LastfmServiceResponse response = getServiceResponse(user, page, pageSize);

        if (response.getCode() != 200) {
            return;
        }

        String fileData = new SanitisedContentGenerator().generateContent(
                "{\"scrobbles\":[",
                "]}",
                ",",
                new ScrobbleJsonFormatter(),
                response.getJson());

        persistFileContent(fileData, ".json");

        if (response.getPage() < response.getTotalPages()) {
            setNext(new SanitisedJsonPersistCommand(directory, user, response.getPage() + 1, pageSize));
        }

        printProgressMessage(response.getTotalPages());
    }

}
