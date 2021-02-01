package com.voidworks.lastfm;

import com.voidworks.lastfm.formatter.ScrobbleCsvFormatter;
import com.voidworks.lastfm.formatter.ScrobbleJsonFormatter;
import com.voidworks.lastfm.generator.SanitisedContentGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

class LastfmApplicationTests {

    @Test
    void generateSanitisedContent_AndExpect_JsonFormat() throws IOException {
        String jsonContent = String.join("", Files.readAllLines(Paths.get("src/test/resources/lastfm-response.json")));

        SanitisedContentGenerator generator = new SanitisedContentGenerator();
        String generatedContent = generator.generateContent("{\"scrobbles\":[", "]}", System.lineSeparator(), new ScrobbleJsonFormatter(), jsonContent);

        String expectedContent = "{\"scrobbles\":[{\"title\":\"The Cosmic Tide\",\"artist\":\"Shylmagoghnar\",\"album\":\"Emergence\",\"scrobbleDate\":\"2021-01-30T07:22:00\"}]}";

        assertEquals(expectedContent, generatedContent);
    }

    @Test
    void generateSanitisedContent_AndExpect_CsvFormat() throws IOException {
        String jsonContent = String.join("", Files.readAllLines(Paths.get("src/test/resources/lastfm-response.json")));

        SanitisedContentGenerator generator = new SanitisedContentGenerator();
        String generatedContent = generator.generateContent("", "", "\n", new ScrobbleCsvFormatter(), jsonContent);

        String expectedContent = "\"The Cosmic Tide\",\"Shylmagoghnar\",\"Emergence\",\"2021-01-30T07:22\"";

        assertEquals(expectedContent, generatedContent);
    }

}
