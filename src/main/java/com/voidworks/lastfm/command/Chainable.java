package com.voidworks.lastfm.command;

public interface Chainable<T> {
    T getNext();
    void setNext(T next);
}
