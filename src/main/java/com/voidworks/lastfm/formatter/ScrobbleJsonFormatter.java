package com.voidworks.lastfm.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.voidworks.lastfm.communication.Communicator;
import com.voidworks.lastfm.model.Scrobble;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ScrobbleJsonFormatter implements ScrobbleFormatter {

    public String format(Scrobble data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String result = "";
        try {
            result = mapper.writeValueAsString(data);
        } catch (Exception e) {
            Communicator.printError(e.getMessage());
        }

        return result;
    }

}
