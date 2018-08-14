package mf.org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public final class UCSReader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final short UCS2BE = (short) 2;
    public static final short UCS2LE = (short) 1;
    public static final short UCS4BE = (short) 8;
    public static final short UCS4LE = (short) 4;
    protected final byte[] fBuffer;
    protected final short fEncoding;
    protected final InputStream fInputStream;

    public UCSReader(InputStream inputStream, short encoding) {
        this(inputStream, 8192, encoding);
    }

    public UCSReader(InputStream inputStream, int size, short encoding) {
        this(inputStream, new byte[size], encoding);
    }

    public UCSReader(InputStream inputStream, byte[] buffer, short encoding) {
        this.fInputStream = inputStream;
        this.fBuffer = buffer;
        this.fEncoding = encoding;
    }

    public int read() throws IOException {
        int b0 = this.fInputStream.read() & 255;
        if (b0 == 255) {
            return -1;
        }
        int b1 = this.fInputStream.read() & 255;
        if (b1 == 255) {
            return -1;
        }
        if (this.fEncoding >= (short) 4) {
            int b2 = this.fInputStream.read() & 255;
            if (b2 == 255) {
                return -1;
            }
            int b3 = this.fInputStream.read() & 255;
            if (b3 == 255) {
                return -1;
            }
            if (this.fEncoding == (short) 8) {
                return (((b0 << 24) + (b1 << 16)) + (b2 << 8)) + b3;
            }
            return (((b3 << 24) + (b2 << 16)) + (b1 << 8)) + b0;
        } else if (this.fEncoding == (short) 2) {
            return (b0 << 8) + b1;
        } else {
            return (b1 << 8) + b0;
        }
    }

    public int read(char[] ch, int offset, int length) throws IOException {
        int byteLength = length << (this.fEncoding >= (short) 4 ? 2 : 1);
        if (byteLength > this.fBuffer.length) {
            byteLength = this.fBuffer.length;
        }
        int count = this.fInputStream.read(this.fBuffer, 0, byteLength);
        if (count == -1) {
            return -1;
        }
        int i;
        int charRead;
        if (this.fEncoding >= (short) 4) {
            int numToRead = (4 - (count & 3)) & 3;
            i = 0;
            while (i < numToRead) {
                charRead = this.fInputStream.read();
                if (charRead == -1) {
                    for (int j = i; j < numToRead; j++) {
                        this.fBuffer[count + j] = (byte) 0;
                    }
                    count += numToRead;
                } else {
                    this.fBuffer[count + i] = (byte) charRead;
                    i++;
                }
            }
            count += numToRead;
        } else if ((count & 1) != 0) {
            count++;
            charRead = this.fInputStream.read();
            if (charRead == -1) {
                this.fBuffer[count] = (byte) 0;
            } else {
                this.fBuffer[count] = (byte) charRead;
            }
        }
        int numChars = count >> (this.fEncoding >= (short) 4 ? 2 : 1);
        i = 0;
        int curPos = 0;
        while (i < numChars) {
            int curPos2 = curPos + 1;
            int b0 = this.fBuffer[curPos] & 255;
            curPos = curPos2 + 1;
            int b1 = this.fBuffer[curPos2] & 255;
            if (this.fEncoding >= (short) 4) {
                curPos2 = curPos + 1;
                int b2 = this.fBuffer[curPos] & 255;
                curPos = curPos2 + 1;
                int b3 = this.fBuffer[curPos2] & 255;
                if (this.fEncoding == (short) 8) {
                    ch[offset + i] = (char) ((((b0 << 24) + (b1 << 16)) + (b2 << 8)) + b3);
                    curPos2 = curPos;
                } else {
                    ch[offset + i] = (char) ((((b3 << 24) + (b2 << 16)) + (b1 << 8)) + b0);
                    curPos2 = curPos;
                }
            } else if (this.fEncoding == (short) 2) {
                ch[offset + i] = (char) ((b0 << 8) + b1);
                curPos2 = curPos;
            } else {
                ch[offset + i] = (char) ((b1 << 8) + b0);
                curPos2 = curPos;
            }
            i++;
            curPos = curPos2;
        }
        return numChars;
    }

    public long skip(long n) throws IOException {
        int charWidth = this.fEncoding >= (short) 4 ? 2 : 1;
        long bytesSkipped = this.fInputStream.skip(n << charWidth);
        if ((((long) (charWidth | 1)) & bytesSkipped) == 0) {
            return bytesSkipped >> charWidth;
        }
        return (bytesSkipped >> charWidth) + 1;
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
