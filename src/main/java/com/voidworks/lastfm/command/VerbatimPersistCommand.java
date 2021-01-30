package com.voidworks.lastfm.command;

import com.voidworks.lastfm.service.response.LastfmServiceResponse;

public class VerbatimPersistCommand extends AbstractPersistCommand {

    public VerbatimPersistCommand(String directory, String user, int page, int pageSize) {
        super(directory, user, page, pageSize);
    }

    @Override
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
        return json;
    }

    @Override
    void queueChainedCommand(int page, int totalPages) {
        if (page < totalPages) {
            setNext(new VerbatimPersistCommand(directory, user, page + 1, pageSize));
        }
    }
}
