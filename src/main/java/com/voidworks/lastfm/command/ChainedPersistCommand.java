package com.voidworks.lastfm.command;

import com.voidworks.lastfm.command.bean.PersistCommandBean;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ChainedPersistCommand implements PersistCommand, Chainable<ChainedPersistCommand> {

    protected PersistCommandBean data;
    protected ChainedPersistCommand next;

    ChainedPersistCommand(PersistCommandBean data) {
        this.data = data;
    }

    protected abstract void queueNext(int page, int totalPages);

}
