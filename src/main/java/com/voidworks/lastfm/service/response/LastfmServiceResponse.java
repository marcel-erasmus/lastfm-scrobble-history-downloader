package com.voidworks.lastfm.service.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LastfmServiceResponse {
    private int code;
    private int page;
    private int totalPages;
    private String json;
}
