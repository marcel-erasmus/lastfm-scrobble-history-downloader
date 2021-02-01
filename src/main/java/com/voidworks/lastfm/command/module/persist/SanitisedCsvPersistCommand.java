package com.voidworks.lastfm.command.module.persist;

import com.voidworks.lastfm.command.module.persist.bean.PersistCommandBean;
import com.voidworks.lastfm.formatter.ScrobbleCsvFormatter;
import com.voidworks.lastfm.generator.SanitisedContentGenerator;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanitisedCsvPersistCommand extends ChainedPersistCommand {

    public SanitisedCsvPersistCommand(PersistCommandBean data) {
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
    protected void queueNext(int page, int totalPages) {
        if (page < totalPages) {
            PersistCommandBean commandBean = PersistCommandBean.builder()
                    .directory(data.getDirectory())
                    .user(data.getUser())
                    .page(data.getPage() + 1)
                    .pageSize(data.getPageSize())
                    .build();

            setNext(new SanitisedCsvPersistCommand(commandBean));
        }
    }

}
