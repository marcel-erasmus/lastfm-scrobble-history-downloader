package com.voidworks.lastfm.command.factory;

import com.voidworks.lastfm.command.AbstractPersistCommand;
import com.voidworks.lastfm.command.SanitisedCsvPersistCommand;
import com.voidworks.lastfm.command.SanitisedJsonPersistCommand;
import com.voidworks.lastfm.command.VerbatimPersistCommand;
import com.voidworks.lastfm.prompt.enumerator.DownloadOptionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class PersistCommandFactory {

    public static AbstractPersistCommand getPersistCommand(DownloadOptionType downloadOption, String directory, String user, int page, int pageSize) {
        switch (downloadOption) {
            case VERBATIM_PERSIST:
                return new VerbatimPersistCommand(directory, user, page, pageSize);
            case SANITISED_JSON_PERSIST:
                return new SanitisedJsonPersistCommand(directory, user, page, pageSize);
            case SANITISED_CSV_PERSIST:
                return new SanitisedCsvPersistCommand(directory, user, page, pageSize);
            default:
                return null;
        }
    }

}
