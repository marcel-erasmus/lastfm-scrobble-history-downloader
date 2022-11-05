package com.voidworks.lastfm.communication;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Communicator {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printError(String error) {
        System.out.println(">>>> ERROR: " + error + "\n");
    }

}
