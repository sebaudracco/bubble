package mf.org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.MessageFormatter;

public final class UTF16Reader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    protected final byte[] fBuffer;
    private final MessageFormatter fFormatter;
    protected final InputStream fInputStream;
    protected final boolean fIsBigEndian;
    private final Locale fLocale;

    public UTF16Reader(InputStream inputStream, boolean isBigEndian) {
        this(inputStream, 4096, isBigEndian, new XMLMessageFormatter(), Locale.getDefault());
    }

    public UTF16Reader(InputStream inputStream, boolean isBigEndian, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, 4096, isBigEndian, messageFormatter, locale);
    }

    public UTF16Reader(InputStream inputStream, int size, boolean isBigEndian, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, new byte[size], isBigEndian, messageFormatter, locale);
    }

    public UTF16Reader(InputStream inputStream, byte[] buffer, boolean isBigEndian, MessageFormatter messageFormatter, Locale locale) {
        this.fInputStream = inputStream;
        this.fBuffer = buffer;
        this.fIsBigEndian = isBigEndian;
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
    }

    public int read() throws IOException {
        int b0 = this.fInputStream.read();
        if (b0 == -1) {
            return -1;
        }
        int b1 = this.fInputStream.read();
        if (b1 == -1) {
            expectedTwoBytes();
        }
        if (this.fIsBigEndian) {
            return (b0 << 8) | b1;
        }
        return (b1 << 8) | b0;
    }

    public int read(char[] ch, int offset, int length) throws IOException {
        int i = -1;
        int byteLength = length << 1;
        if (byteLength > this.fBuffer.length) {
            byteLength = this.fBuffer.length;
        }
        int byteCount = this.fInputStream.read(this.fBuffer, 0, byteLength);
        if (byteCount != -1) {
            if ((byteCount & 1) != 0) {
                int b = this.fInputStream.read();
                if (b == -1) {
                    expectedTwoBytes();
                }
                int byteCount2 = byteCount + 1;
                this.fBuffer[byteCount] = (byte) b;
                byteCount = byteCount2;
            }
            i = byteCount >> 1;
            if (this.fIsBigEndian) {
                processBE(ch, offset, i);
            } else {
                processLE(ch, offset, i);
            }
        }
        return i;
    }

    public long skip(long n) throws IOException {
        long bytesSkipped = this.fInputStream.skip(n << 1);
        if ((bytesSkipped & 1) != 0) {
            if (this.fInputStream.read() == -1) {
                expectedTwoBytes();
            }
            bytesSkipped++;
        }
        return bytesSkipped >> 1;
    }

    public boolean ready() throws IOException {
        return false;
    }

    public boolean markSupported() {
        return false;
    }

    public void mark(int readAheadLimit) throws IOException {
        throw new IOException(this.fFormatter.formatMessage(this.fLocale, "OperationNotSupported", new Object[]{"mark()", "UTF-16"}));
    }

    public void reset() throws IOException {
    }

    public void close() throws IOException {
        this.fInputStream.close();
    }

    private void processBE(char[] ch, int offset, int count) {
        int i = 0;
        int curPos = 0;
        int offset2 = offset;
        while (i < count) {
            int curPos2 = curPos + 1;
            int b0 = this.fBuffer[curPos] & 255;
            curPos = curPos2 + 1;
            offset = offset2 + 1;
            ch[offset2] = (char) ((b0 << 8) | (this.fBuffer[curPos2] & 255));
            i++;
            offset2 = offset;
        }
    }

    private void processLE(char[] ch, int offset, int count) {
        int i = 0;
        int curPos = 0;
        int offset2 = offset;
        while (i < count) {
            int curPos2 = curPos + 1;
            int b0 = this.fBuffer[curPos] & 255;
            curPos = curPos2 + 1;
            offset = offset2 + 1;
            ch[offset2] = (char) (((this.fBuffer[curPos2] & 255) << 8) | b0);
            i++;
            offset2 = offset;
        }
    }

    private void expectedTwoBytes() throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "ExpectedByte", new Object[]{"2", "2"});
    }
}
