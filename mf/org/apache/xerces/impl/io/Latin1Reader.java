package mf.org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public final class Latin1Reader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected final byte[] fBuffer;
    protected final InputStream fInputStream;

    public Latin1Reader(InputStream inputStream) {
        this(inputStream, 2048);
    }

    public Latin1Reader(InputStream inputStream, int size) {
        this(inputStream, new byte[size]);
    }

    public Latin1Reader(InputStream inputStream, byte[] buffer) {
        this.fInputStream = inputStream;
        this.fBuffer = buffer;
    }

    public int read() throws IOException {
        return this.fInputStream.read();
    }

    public int read(char[] ch, int offset, int length) throws IOException {
        if (length > this.fBuffer.length) {
            length = this.fBuffer.length;
        }
        int count = this.fInputStream.read(this.fBuffer, 0, length);
        for (int i = 0; i < count; i++) {
            ch[offset + i] = (char) (this.fBuffer[i] & 255);
        }
        return count;
    }

    public long skip(long n) throws IOException {
        return this.fInputStream.skip(n);
    }

    public boolean ready() throws IOException {
        return false;
    }

    public boolean markSupported() {
        return this.fInputStream.markSupported();
    }

    public void mark(int readAheadLimit) throws IOException {
        this.fInputStream.mark(readAheadLimit);
    }

    public void reset() throws IOException {
        this.fInputStream.reset();
    }

    public void close() throws IOException {
        this.fInputStream.close();
    }
}
