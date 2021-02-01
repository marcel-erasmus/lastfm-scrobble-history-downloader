package com.voidworks.lastfm.command.module.prompt;

import com.voidworks.lastfm.command.Command;
import com.voidworks.lastfm.communication.Communicator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractPrompt<T> implements Command, Communicator {
    private T data;

    public abstract boolean isValid();
}
