package com.voidworks.lastfm.command;

import com.voidworks.lastfm.service.response.LastfmServiceResponse;

import java.nio.file.Files;
import java.nio.file.Paths;

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

        persistFileContent(response.getJson(), ".json");

        if (response.getPage() < response.getTotalPages()) {
            setNext(new VerbatimPersistCommand(directory, user, response.getPage() + 1, pageSize));
        }

        printProgressMessage(response.getTotalPages());
    }

}
