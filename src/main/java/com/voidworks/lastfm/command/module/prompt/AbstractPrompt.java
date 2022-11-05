package com.voidworks.lastfm.command.module.prompt;

import com.voidworks.lastfm.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractPrompt<T> implements Command {

    private T data;

    public abstract boolean isValid();

}
