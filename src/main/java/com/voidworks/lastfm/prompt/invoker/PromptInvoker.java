package com.voidworks.lastfm.prompt.invoker;

import com.voidworks.lastfm.prompt.AbstractPrompt;

public class PromptInvoker {
    public <T> void execute(AbstractPrompt<T> prompt) {
        do {
            prompt.execute();
        }
        while (!prompt.isValid());
    }
}
