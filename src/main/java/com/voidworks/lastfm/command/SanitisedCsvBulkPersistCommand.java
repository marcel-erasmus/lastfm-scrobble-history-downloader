package com.voidworks.lastfm.command;

import com.voidworks.lastfm.command.generator.SanitisedContentGenerator;
import com.voidworks.lastfm.formatter.ScrobbleCsvFormatter;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SanitisedCsvBulkPersistCommand extends AbstractPersistCommand {

    public SanitisedCsvBulkPersistCommand(String directory, String user, int page, int pageSize) {
        super(directory, user, page, pageSize);
    }

    public void execute() {
        LastfmServiceResponse response = getServiceResponse(user, page, pageSize);
        if (response.getCode() != 200) {
            return;
        }

        persistFileContent(generateFileContent(response.getJson()), ".csv");
        queueChainedCommand(response.getPage(), response.getTotalPages());
        printProgressMessage(response.getTotalPages());
    }

    @Override
    String generateFileContent(String json) {
        return new SanitisedContentGenerator().generateContent("", "", System.lineSeparator(), new ScrobbleCsvFormatter(), json);
    }

    @Override
    void persistFileContent(String fileContent, String fileExtension) {
        try (
                FileWriter fileWriter = new FileWriter(String.format("%s\\lastfm_scrobbles%s", directory, fileExtension), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
        ) {
            printWriter.print(fileContent);
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    @Override
    void queueChainedCommand(int page, int totalPages) {
        if (page < totalPages) {
            setNext(new SanitisedCsvBulkPersistCommand(directory, user, page + 1, pageSize));
        }
    }
}
