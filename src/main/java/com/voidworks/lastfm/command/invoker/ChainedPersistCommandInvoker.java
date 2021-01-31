package com.voidworks.lastfm.command.invoker;

import com.voidworks.lastfm.command.ChainedPersistCommand;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChainedPersistCommandInvoker {
    public void execute(ChainedPersistCommand command) {
        do {
            if (command.getNext() != null) {
                command = command.getNext();
            }

            command.execute();
        } while(command.getNext() != null);
    }
}
