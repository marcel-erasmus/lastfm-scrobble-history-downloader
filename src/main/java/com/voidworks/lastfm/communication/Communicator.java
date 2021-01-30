package com.voidworks.lastfm.communication;

public interface Communicator {
    default void printMessage(String message) {
        System.out.println(message);
    }

    default void printError(String error) {
        System.out.println(">>>> ERROR: " + error + "\n");
    }
}
