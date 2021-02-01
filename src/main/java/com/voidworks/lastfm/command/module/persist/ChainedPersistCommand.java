package com.voidworks.lastfm.command.module.persist;

import com.voidworks.lastfm.command.module.persist.bean.PersistCommandBean;
import com.voidworks.lastfm.common.Chainable;
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
