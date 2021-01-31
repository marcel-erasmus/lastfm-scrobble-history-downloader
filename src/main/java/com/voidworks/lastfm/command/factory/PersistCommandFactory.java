package com.voidworks.lastfm.command.factory;

import com.voidworks.lastfm.command.*;
import com.voidworks.lastfm.command.bean.PersistCommandBean;
import com.voidworks.lastfm.prompt.enumerator.DownloadOptionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class PersistCommandFactory {

    public static ChainedPersistCommand getPersistCommand(DownloadOptionType downloadOption, PersistCommandBean persistCommandBean) {
        switch (downloadOption) {
            case SANITISED_CSV_BULK_PERSIST:
                return new SanitisedCsvBulkPersistCommand(persistCommandBean);
            case SANITISED_CSV_PERSIST:
                return new SanitisedCsvPersistCommand(persistCommandBean);
            case SANITISED_JSON_PERSIST:
                return new SanitisedJsonPersistCommand(persistCommandBean);
            case VERBATIM_PERSIST:
                return new VerbatimPersistCommand(persistCommandBean);
            default:
                return null;
        }
    }

}
