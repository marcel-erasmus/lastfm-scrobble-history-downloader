package com.voidworks.lastfm.command.module.persist.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PersistCommandBean {
    private String directory;
    private String user;
    private int page;
    private int pageSize;
}
