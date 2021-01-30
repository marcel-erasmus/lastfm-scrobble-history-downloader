package com.voidworks.lastfm.formatter;

import com.voidworks.lastfm.model.Scrobble;

public interface ScrobbleFormatter {
    String format(Scrobble data);
}
