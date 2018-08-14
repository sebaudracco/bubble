package com.danikula.videocache;

import java.io.ByteArrayInputStream;

public class ByteArraySource implements Source {
    private ByteArrayInputStream arrayInputStream;
    private final byte[] data;

    public ByteArraySource(byte[] data) {
        this.data = data;
    }

    public int read(byte[] buffer) throws ProxyCacheException {
        return this.arrayInputStream.read(buffer, 0, buffer.length);
    }

    public long length() throws ProxyCacheException {
        return (long) this.data.length;
    }

    public void open(long offset) throws ProxyCacheException {
        this.arrayInputStream = new ByteArrayInputStream(this.data);
        this.arrayInputStream.skip(offset);
    }

    public void close() throws ProxyCacheException {
    }
}
