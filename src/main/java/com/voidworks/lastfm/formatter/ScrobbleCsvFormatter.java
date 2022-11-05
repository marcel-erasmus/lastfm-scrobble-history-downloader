package com.voidworks.lastfm.formatter;

import com.voidworks.lastfm.model.Scrobble;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ScrobbleCsvFormatter implements ScrobbleFormatter {

    @Override
    public String format(Scrobble data) {
        return "\"" + data.getTitle() + "\"," +
               "\"" + data.getArtist() + "\"," +
               "\"" + data.getAlbum() + "\"," +
               "\"" + data.getScrobbleDate() + "\"";
    }

}
