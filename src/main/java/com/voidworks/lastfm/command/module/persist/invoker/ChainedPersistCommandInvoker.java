package com.voidworks.lastfm.command.module.persist.invoker;

import com.voidworks.lastfm.command.module.persist.ChainedPersistCommand;
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
