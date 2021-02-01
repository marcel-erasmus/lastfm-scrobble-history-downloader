package com.voidworks.lastfm.command.module.persist;

import com.voidworks.lastfm.command.module.persist.bean.PersistCommandBean;
import com.voidworks.lastfm.formatter.ScrobbleCsvFormatter;
import com.voidworks.lastfm.generator.SanitisedContentGenerator;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

@Getter
@Setter
public class SanitisedCsvBulkPersistCommand extends ChainedPersistCommand {

    public SanitisedCsvBulkPersistCommand(PersistCommandBean data) {
        super(data);
    }

    public void execute() {
        LastfmServiceResponse response = getServiceResponse(data);
        if (response.getCode() != 200) {
            return;
        }

        persistContent(data, generateContent(response.getJson()), ".csv");
        queueNext(response.getPage(), response.getTotalPages());
        printProgressMessage(data.getPage(), response.getTotalPages());
    }

    @Override
    public String generateContent(String json) {
        return new SanitisedContentGenerator().generateContent("", "", System.lineSeparator(), new ScrobbleCsvFormatter(), json);
    }

    @Override
    public void persistContent(PersistCommandBean data, String fileContent, String fileExtension) {
        try (
                FileWriter fileWriter = new FileWriter(String.format("%s\\lastfm_scrobbles%s", data.getDirectory(), fileExtension), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
        ) {
            printWriter.print(fileContent);
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    @Override
    protected void queueNext(int page, int totalPages) {
        if (page < totalPages) {
            PersistCommandBean commandBean = PersistCommandBean.builder()
                    .directory(data.getDirectory())
                    .user(data.getUser())
                    .page(data.getPage() + 1)
                    .pageSize(data.getPageSize())
                    .build();

            setNext(new SanitisedCsvBulkPersistCommand(commandBean));
        }
    }

}
