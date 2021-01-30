package com.voidworks.lastfm.command.invoker;

import com.voidworks.lastfm.command.AbstractChainedCommand;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChainedCommandInvoker {
    public <T> void execute(AbstractChainedCommand<T> command) {
        do {
            if (command.getNext() != null) {
                command = command.getNext();
            }

            command.execute();
        } while(command.getNext() != null);
    }
}
