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

        persistFileContent(generateFileContent(response.getJson()), ".json");
        queueChainedCommand(response.getPage(), response.getTotalPages());
        printProgressMessage(response.getTotalPages());
    }

    @Override
    String generateFileContent(String json) {
        return new SanitisedContentGenerator().generateContent("{\"scrobbles\":[", "]}", ",", new ScrobbleJsonFormatter(), json);
    }

    @Override
    void queueChainedCommand(int page, int totalPages) {
        if (page < totalPages) {
            setNext(new SanitisedJsonPersistCommand(directory, user, page + 1, pageSize));
        }
    }
}
