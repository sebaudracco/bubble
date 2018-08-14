package com.adcolony.sdk;

import java.io.IOException;
import java.io.InputStream;

class as extends InputStream {
    InputStream f670a;
    int f671b;

    as(InputStream inputStream, int i, int i2) throws IOException {
        this.f670a = inputStream;
        while (i > 0) {
            i -= (int) inputStream.skip((long) i);
        }
        this.f671b = i2;
    }

    public int available() throws IOException {
        int available = this.f670a.available();
        return available <= this.f671b ? available : this.f671b;
    }

    public void close() throws IOException {
        this.f670a.close();
    }

    public int read() throws IOException {
        if (this.f671b == 0) {
            return -1;
        }
        this.f671b--;
        return this.f670a.read();
    }

    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    public int read(byte[] buffer, int offset, int count) throws IOException {
        if (this.f671b == 0) {
            return -1;
        }
        if (count > this.f671b) {
            count = this.f671b;
        }
        int read = this.f670a.read(buffer, offset, count);
        if (read == -1) {
            return -1;
        }
        this.f671b -= read;
        return read;
    }

    public long skip(long count) throws IOException {
        int i = (int) count;
        if (i <= 0) {
            return 0;
        }
        if (i > this.f671b) {
            i = this.f671b;
        }
        this.f671b -= i;
        while (i > 0) {
            i -= (int) this.f670a.skip(count);
        }
        return count;
    }
}
