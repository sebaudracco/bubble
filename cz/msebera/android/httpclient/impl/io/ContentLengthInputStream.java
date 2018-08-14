package cz.msebera.android.httpclient.impl.io;

import android.support.v4.media.session.PlaybackStateCompat;
import cz.msebera.android.httpclient.ConnectionClosedException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;

@NotThreadSafe
public class ContentLengthInputStream extends InputStream {
    private static final int BUFFER_SIZE = 2048;
    private boolean closed = false;
    private final long contentLength;
    private SessionInputBuffer in = null;
    private long pos = 0;

    public ContentLengthInputStream(SessionInputBuffer in, long contentLength) {
        this.in = (SessionInputBuffer) Args.notNull(in, "Session input buffer");
        this.contentLength = Args.notNegative(contentLength, "Content length");
    }

    public void close() throws IOException {
        if (!this.closed) {
            try {
                if (this.pos < this.contentLength) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
                this.closed = true;
            } catch (Throwable th) {
                this.closed = true;
            }
        }
    }

    public int available() throws IOException {
        if (this.in instanceof BufferInfo) {
            return Math.min(((BufferInfo) this.in).length(), (int) (this.contentLength - this.pos));
        }
        return 0;
    }

    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            int b = this.in.read();
            if (b != -1) {
                this.pos++;
                return b;
            } else if (this.pos >= this.contentLength) {
                return b;
            } else {
                throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
            }
        }
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            int chunk = len;
            if (this.pos + ((long) len) > this.contentLength) {
                chunk = (int) (this.contentLength - this.pos);
            }
            int count = this.in.read(b, off, chunk);
            if (count == -1 && this.pos < this.contentLength) {
                throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
            } else if (count <= 0) {
                return count;
            } else {
                this.pos += (long) count;
                return count;
            }
        }
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public long skip(long n) throws IOException {
        if (n <= 0) {
            return 0;
        }
        byte[] buffer = new byte[2048];
        long remaining = Math.min(n, this.contentLength - this.pos);
        long count = 0;
        while (remaining > 0) {
            int l = read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, remaining));
            if (l == -1) {
                return count;
            }
            count += (long) l;
            remaining -= (long) l;
        }
        return count;
    }
}
