package cz.msebera.android.httpclient.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class DeflateInputStream extends InputStream {
    private InputStream sourceStream;

    static class DeflateStream extends InflaterInputStream {
        private boolean closed = false;

        public DeflateStream(InputStream in, Inflater inflater) {
            super(in, inflater);
        }

        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                this.inf.end();
                super.close();
            }
        }
    }

    public DeflateInputStream(InputStream wrapped) throws IOException {
        byte[] peeked = new byte[6];
        PushbackInputStream pushback = new PushbackInputStream(wrapped, peeked.length);
        int headerLength = pushback.read(peeked);
        if (headerLength == -1) {
            throw new IOException("Unable to read the response");
        }
        int n;
        byte[] dummy = new byte[1];
        Inflater inf = new Inflater();
        while (true) {
            try {
                n = inf.inflate(dummy);
                if (n != 0) {
                    break;
                } else if (inf.finished()) {
                    throw new IOException("Unable to read the response");
                } else if (inf.needsDictionary()) {
                    break;
                } else if (inf.needsInput()) {
                    inf.setInput(peeked);
                }
            } catch (DataFormatException e) {
                pushback.unread(peeked, 0, headerLength);
                this.sourceStream = new DeflateStream(pushback, new Inflater(true));
            } finally {
                inf.end();
            }
        }
        if (n == -1) {
            throw new IOException("Unable to read the response");
        }
        pushback.unread(peeked, 0, headerLength);
        this.sourceStream = new DeflateStream(pushback, new Inflater());
    }

    public int read() throws IOException {
        return this.sourceStream.read();
    }

    public int read(byte[] b) throws IOException {
        return this.sourceStream.read(b);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        return this.sourceStream.read(b, off, len);
    }

    public long skip(long n) throws IOException {
        return this.sourceStream.skip(n);
    }

    public int available() throws IOException {
        return this.sourceStream.available();
    }

    public void mark(int readLimit) {
        this.sourceStream.mark(readLimit);
    }

    public void reset() throws IOException {
        this.sourceStream.reset();
    }

    public boolean markSupported() {
        return this.sourceStream.markSupported();
    }

    public void close() throws IOException {
        this.sourceStream.close();
    }
}
