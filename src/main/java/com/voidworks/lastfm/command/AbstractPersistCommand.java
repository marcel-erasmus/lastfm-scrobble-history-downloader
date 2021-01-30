package com.voidworks.lastfm.command;

import com.voidworks.lastfm.communication.Communicator;
import com.voidworks.lastfm.service.LastfmService;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Files;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.NONE)
@Getter
@Setter
public abstract class AbstractPersistCommand extends AbstractChainedCommand<AbstractPersistCommand> implements Communicator {
    protected String user;
    protected String directory;
    protected int page;
    protected int pageSize;

    AbstractPersistCommand(String directory, String user, int page, int pageSize) {
        this.directory = directory;
        this.user = user;
        this.page = page;
        this.pageSize = pageSize;
    }

    LastfmServiceResponse getServiceResponse(String user, int page, int pageSize) {
        LastfmService lastfmService = new LastfmService();
        LastfmServiceResponse response = lastfmService.getScrobblesByUserAndPage(user, page, pageSize);

        if (response.getCode() != 200) {
            printError(String.format("An anomaly has occurred (STATUS CODE %s).", response.getCode()));
        }

        return response;
    }

    void persistFileContent(String fileContent, String fileExtension) {
        String path = String.format("%s\\lastfm_scrobbles_page_%s%s", directory, page, fileExtension);
        try {
            Files.write(Paths.get(path), fileContent.getBytes());
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    void printProgressMessage(int totalPages) {
        printMessage(String.format("Processed page %s of %s (%.2f%%)", page, totalPages, (double) page / totalPages * 100));
    }
}
