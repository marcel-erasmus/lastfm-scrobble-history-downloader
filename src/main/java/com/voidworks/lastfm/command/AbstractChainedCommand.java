package com.voidworks.lastfm.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractChainedCommand<T> {
    private AbstractChainedCommand<T> next;

    public abstract void execute();
}
