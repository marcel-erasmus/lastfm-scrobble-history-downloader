package com.voidworks.lastfm.prompt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractPrompt<T> {
    private T data;

    public abstract void execute();
    public abstract boolean isValid();
}
