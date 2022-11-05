package com.voidworks.lastfm.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Scrobble {

    private String title;
    private String artist;
    private String album;
    private LocalDateTime scrobbleDate;

}
