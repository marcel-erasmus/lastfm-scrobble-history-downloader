package com.voidworks.lastfm.common;

public interface Chainable<T> {
    T getNext();
    void setNext(T next);
}
