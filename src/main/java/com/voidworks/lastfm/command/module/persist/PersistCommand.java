package com.voidworks.lastfm.command.module.persist;

import com.voidworks.lastfm.command.Command;
import com.voidworks.lastfm.command.module.persist.bean.PersistCommandBean;
import com.voidworks.lastfm.communication.Communicator;
import com.voidworks.lastfm.service.LastfmService;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;

import java.nio.file.Files;
import java.nio.file.Paths;

public interface PersistCommand extends Command, Communicator {

    default LastfmServiceResponse getServiceResponse(PersistCommandBean data) {
        LastfmService lastfmService = new LastfmService();
        LastfmServiceResponse response = lastfmService.getScrobblesByUserAndPage(data.getUser(), data.getPage(), data.getPageSize());

        if (response.getCode() != 200) {
            printError(String.format("An anomaly has occurred (STATUS CODE %s).", response.getCode()));
        }

        return response;
    }

    String generateContent(String json);

    default void persistContent(PersistCommandBean data, String fileContent, String fileExtension) {
        String path = String.format("%s\\lastfm_scrobbles_page_%s%s", data.getDirectory(), data.getPage(), fileExtension);
        try {
            Files.write(Paths.get(path), fileContent.getBytes());
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    default void printProgressMessage(int page, int totalPages) {
        printMessage(String.format("Processed page %s of %s (%.2f%%)", page, totalPages, (double) page / totalPages * 100));
    }

}
