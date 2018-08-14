package com.bgjd.ici.p028c;

public interface C1434d {
    boolean IsConnected();

    void close();

    <T, O> T getMapper(Class<T> cls, Class<O> cls2);

    void open();
}
