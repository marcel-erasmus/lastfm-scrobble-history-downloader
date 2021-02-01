package com.voidworks.lastfm.command.module.prompt.invoker;

import com.voidworks.lastfm.command.module.prompt.AbstractPrompt;

public class PromptInvoker {
    public <T> void execute(AbstractPrompt<T> prompt) {
        do {
            prompt.execute();
        }
        while (!prompt.isValid());
    }
}
