package com.voidworks.lastfm.prompt.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DownloadOptionType {
    VERBATIM_PERSIST(1),
    SANITISED_JSON_PERSIST(2),
    SANITISED_CSV_PERSIST(3);

    private int code;

    public static DownloadOptionType of(int code) {
        switch (code) {
            case 1: return VERBATIM_PERSIST;
            case 2: return SANITISED_JSON_PERSIST;
            case 3: return SANITISED_CSV_PERSIST;
            default: return null;
        }
    }
}
