package com.voidworks.lastfm.command.module.prompt.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DownloadOptionType {
    SANITISED_CSV_BULK_PERSIST(1),
    SANITISED_CSV_PERSIST(2),
    SANITISED_JSON_PERSIST(2),
    VERBATIM_PERSIST(4);

    private int code;

    public static DownloadOptionType of(int code) {
        switch (code) {
            case 1: return SANITISED_CSV_BULK_PERSIST;
            case 2: return SANITISED_CSV_PERSIST;
            case 3: return SANITISED_JSON_PERSIST;
            case 4: return VERBATIM_PERSIST;
            default: return null;
        }
    }
}
