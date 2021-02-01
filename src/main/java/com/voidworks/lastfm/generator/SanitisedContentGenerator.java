package com.voidworks.lastfm.generator;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.voidworks.lastfm.formatter.ScrobbleFormatter;
import com.voidworks.lastfm.model.Scrobble;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class SanitisedContentGenerator {
    public String generateContent(String contentPrefix, String contentSuffix, String contentSeparator, ScrobbleFormatter formatter, String json) {
        DocumentContext documentContext = JsonPath.parse(json);
        JSONArray jsonScrobbles = documentContext.read("$['recenttracks']['track'][*]");

        StringBuilder content = new StringBuilder();
        content.append(contentPrefix);
        jsonScrobbles.forEach(jsonScrobble -> {
            DocumentContext scrobbleContext = JsonPath.parse(jsonScrobble);

            Scrobble scrobble = Scrobble.builder()
                    .title(scrobbleContext.read("$['name']"))
                    .artist(scrobbleContext.read("$['artist']['#text']"))
                    .album(scrobbleContext.read("$['album']['#text']"))
                    .scrobbleDate(LocalDateTime.parse(scrobbleContext.read("$['date']['#text']"), DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")))
                    .build();

            content.append(formatter.format(scrobble));
            content.append(contentSeparator);
        });
        content.delete(content.lastIndexOf(contentSeparator), content.length());
        content.append(contentSuffix);

        return content.toString();
    }
}
