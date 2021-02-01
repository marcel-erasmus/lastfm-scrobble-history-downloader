package com.voidworks.lastfm.command.module.persist;

import com.voidworks.lastfm.command.module.persist.bean.PersistCommandBean;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerbatimPersistCommand extends ChainedPersistCommand {

    public VerbatimPersistCommand(PersistCommandBean data) {
        super(data);
    }

    @Override
    public void execute() {
        LastfmServiceResponse response = getServiceResponse(data);
        if (response.getCode() != 200) {
            return;
        }

        persistContent(data, generateContent(response.getJson()), ".json");
        queueNext(response.getPage(), response.getTotalPages());
        printProgressMessage(data.getPage(), response.getTotalPages());
    }

    @Override
    public String generateContent(String json) {
        return json;
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

            setNext(new VerbatimPersistCommand(commandBean));
        }
    }

}
