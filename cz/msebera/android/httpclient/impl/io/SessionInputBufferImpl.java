package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.MessageConstraintException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

@NotThreadSafe
public class SessionInputBufferImpl implements SessionInputBuffer, BufferInfo {
    private final byte[] buffer;
    private int bufferlen;
    private int bufferpos;
    private CharBuffer cbuf;
    private final MessageConstraints constraints;
    private final CharsetDecoder decoder;
    private InputStream instream;
    private final ByteArrayBuffer linebuffer;
    private final HttpTransportMetricsImpl metrics;
    private final int minChunkLimit;

    public SessionInputBufferImpl(HttpTransportMetricsImpl metrics, int buffersize, int minChunkLimit, MessageConstraints constraints, CharsetDecoder chardecoder) {
        Args.notNull(metrics, "HTTP transport metrcis");
        Args.positive(buffersize, "Buffer size");
        this.metrics = metrics;
        this.buffer = new byte[buffersize];
        this.bufferpos = 0;
        this.bufferlen = 0;
        if (minChunkLimit < 0) {
            minChunkLimit = 512;
        }
        this.minChunkLimit = minChunkLimit;
        if (constraints == null) {
            constraints = MessageConstraints.DEFAULT;
        }
        this.constraints = constraints;
        this.linebuffer = new ByteArrayBuffer(buffersize);
        this.decoder = chardecoder;
    }

    public SessionInputBufferImpl(HttpTransportMetricsImpl metrics, int buffersize) {
        this(metrics, buffersize, buffersize, null, null);
    }

    public void bind(InputStream instream) {
        this.instream = instream;
    }

    public boolean isBound() {
        return this.instream != null;
    }

    public int capacity() {
        return this.buffer.length;
    }

    public int length() {
        return this.bufferlen - this.bufferpos;
    }

    public int available() {
        return capacity() - length();
    }

    private int streamRead(byte[] b, int off, int len) throws IOException {
        Asserts.notNull(this.instream, "Input stream");
        return this.instream.read(b, off, len);
    }

    public int fillBuffer() throws IOException {
        if (this.bufferpos > 0) {
            int len = this.bufferlen - this.bufferpos;
            if (len > 0) {
                System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, len);
            }
            this.bufferpos = 0;
            this.bufferlen = len;
        }
        int off = this.bufferlen;
        int l = streamRead(this.buffer, off, this.buffer.length - off);
        if (l == -1) {
            return -1;
        }
        this.bufferlen = off + l;
        this.metrics.incrementBytesTransferred((long) l);
        return l;
    }

    public boolean hasBufferedData() {
        return this.bufferpos < this.bufferlen;
    }

    public void clear() {
        this.bufferpos = 0;
        this.bufferlen = 0;
    }

    public int read() throws IOException {
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buffer;
        int i = this.bufferpos;
        this.bufferpos = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            return 0;
        }
        int chunk;
        if (hasBufferedData()) {
            chunk = Math.min(len, this.bufferlen - this.bufferpos);
            System.arraycopy(this.buffer, this.bufferpos, b, off, chunk);
            this.bufferpos += chunk;
            return chunk;
        } else if (len > this.minChunkLimit) {
            int read = streamRead(b, off, len);
            if (read > 0) {
                this.metrics.incrementBytesTransferred((long) read);
            }
            return read;
        } else {
            while (!hasBufferedData()) {
                if (fillBuffer() == -1) {
                    return -1;
                }
            }
            chunk = Math.min(len, this.bufferlen - this.bufferpos);
            System.arraycopy(this.buffer, this.bufferpos, b, off, chunk);
            this.bufferpos += chunk;
            return chunk;
        }
    }

    public int read(byte[] b) throws IOException {
        if (b == null) {
            return 0;
        }
        return read(b, 0, b.length);
    }

    private int locateLF() {
        for (int i = this.bufferpos; i < this.bufferlen; i++) {
            if (this.buffer[i] == (byte) 10) {
                return i;
            }
        }
        return -1;
    }

    public int readLine(CharArrayBuffer charbuffer) throws IOException {
        Args.notNull(charbuffer, "Char array buffer");
        int noRead = 0;
        boolean retry = true;
        while (retry) {
            int i = locateLF();
            if (i == -1) {
                if (hasBufferedData()) {
                    this.linebuffer.append(this.buffer, this.bufferpos, this.bufferlen - this.bufferpos);
                    this.bufferpos = this.bufferlen;
                }
                noRead = fillBuffer();
                if (noRead == -1) {
                    retry = false;
                }
            } else if (this.linebuffer.isEmpty()) {
                return lineFromReadBuffer(charbuffer, i);
            } else {
                retry = false;
                this.linebuffer.append(this.buffer, this.bufferpos, (i + 1) - this.bufferpos);
                this.bufferpos = i + 1;
            }
            int maxLineLen = this.constraints.getMaxLineLength();
            if (maxLineLen > 0 && this.linebuffer.length() >= maxLineLen) {
                throw new MessageConstraintException("Maximum line length limit exceeded");
            }
        }
        if (noRead == -1 && this.linebuffer.isEmpty()) {
            return -1;
        }
        return lineFromLineBuffer(charbuffer);
    }

    private int lineFromLineBuffer(CharArrayBuffer charbuffer) throws IOException {
        int len = this.linebuffer.length();
        if (len > 0) {
            if (this.linebuffer.byteAt(len - 1) == 10) {
                len--;
            }
            if (len > 0 && this.linebuffer.byteAt(len - 1) == 13) {
                len--;
            }
        }
        if (this.decoder == null) {
            charbuffer.append(this.linebuffer, 0, len);
        } else {
            len = appendDecoded(charbuffer, ByteBuffer.wrap(this.linebuffer.buffer(), 0, len));
        }
        this.linebuffer.clear();
        return len;
    }

    private int lineFromReadBuffer(CharArrayBuffer charbuffer, int position) throws IOException {
        int pos = position;
        int off = this.bufferpos;
        this.bufferpos = pos + 1;
        if (pos > off && this.buffer[pos - 1] == (byte) 13) {
            pos--;
        }
        int len = pos - off;
        if (this.decoder != null) {
            return appendDecoded(charbuffer, ByteBuffer.wrap(this.buffer, off, len));
        }
        charbuffer.append(this.buffer, off, len);
        return len;
    }

    private int appendDecoded(CharArrayBuffer charbuffer, ByteBuffer bbuf) throws IOException {
        if (!bbuf.hasRemaining()) {
            return 0;
        }
        if (this.cbuf == null) {
            this.cbuf = CharBuffer.allocate(1024);
        }
        this.decoder.reset();
        int len = 0;
        while (bbuf.hasRemaining()) {
            len += handleDecodingResult(this.decoder.decode(bbuf, this.cbuf, true), charbuffer, bbuf);
        }
        len += handleDecodingResult(this.decoder.flush(this.cbuf), charbuffer, bbuf);
        this.cbuf.clear();
        return len;
    }

    private int handleDecodingResult(CoderResult result, CharArrayBuffer charbuffer, ByteBuffer bbuf) throws IOException {
        if (result.isError()) {
            result.throwException();
        }
        this.cbuf.flip();
        int len = this.cbuf.remaining();
        while (this.cbuf.hasRemaining()) {
            charbuffer.append(this.cbuf.get());
        }
        this.cbuf.compact();
        return len;
    }

    public String readLine() throws IOException {
        CharArrayBuffer charbuffer = new CharArrayBuffer(64);
        if (readLine(charbuffer) != -1) {
            return charbuffer.toString();
        }
        return null;
    }

    public boolean isDataAvailable(int timeout) throws IOException {
        return hasBufferedData();
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}
