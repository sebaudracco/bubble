package mf.org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.MessageFormatter;

public final class UTF8Reader extends Reader {
    private static final boolean DEBUG_READ = false;
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected final byte[] fBuffer;
    private final MessageFormatter fFormatter;
    protected final InputStream fInputStream;
    private final Locale fLocale;
    protected int fOffset;
    private int fSurrogate;

    public UTF8Reader(InputStream inputStream) {
        this(inputStream, 2048, new XMLMessageFormatter(), Locale.getDefault());
    }

    public UTF8Reader(InputStream inputStream, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, 2048, messageFormatter, locale);
    }

    public UTF8Reader(InputStream inputStream, int size, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, new byte[size], messageFormatter, locale);
    }

    public UTF8Reader(InputStream inputStream, byte[] buffer, MessageFormatter messageFormatter, Locale locale) {
        this.fSurrogate = -1;
        this.fInputStream = inputStream;
        this.fBuffer = buffer;
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
    }

    public int read() throws IOException {
        int c = this.fSurrogate;
        if (this.fSurrogate == -1) {
            int b0;
            int index;
            if (0 == this.fOffset) {
                b0 = this.fInputStream.read();
                index = 0;
            } else {
                index = 0 + 1;
                b0 = this.fBuffer[0] & 255;
            }
            if (b0 == -1) {
                return -1;
            }
            if (b0 < 128) {
                c = (char) b0;
            } else if ((b0 & 224) == 192 && (b0 & 30) != 0) {
                if (index == this.fOffset) {
                    b1 = this.fInputStream.read();
                    r6 = index;
                } else {
                    r6 = index + 1;
                    b1 = this.fBuffer[index] & 255;
                }
                if (b1 == -1) {
                    expectedByte(2, 2);
                }
                if ((b1 & 192) != 128) {
                    invalidByte(2, 2, b1);
                }
                c = ((b0 << 6) & 1984) | (b1 & 63);
            } else if ((b0 & 240) == 224) {
                if (index == this.fOffset) {
                    b1 = this.fInputStream.read();
                } else {
                    b1 = this.fBuffer[index] & 255;
                    index++;
                }
                if (b1 == -1) {
                    expectedByte(2, 3);
                }
                if ((b1 & 192) != 128 || ((b0 == 237 && b1 >= 160) || ((b0 & 15) == 0 && (b1 & 32) == 0))) {
                    invalidByte(2, 3, b1);
                }
                if (index == this.fOffset) {
                    b2 = this.fInputStream.read();
                    r6 = index;
                } else {
                    r6 = index + 1;
                    b2 = this.fBuffer[index] & 255;
                }
                if (b2 == -1) {
                    expectedByte(3, 3);
                }
                if ((b2 & 192) != 128) {
                    invalidByte(3, 3, b2);
                }
                c = (((b0 << 12) & 61440) | ((b1 << 6) & 4032)) | (b2 & 63);
            } else if ((b0 & 248) == 240) {
                int b3;
                if (index == this.fOffset) {
                    b1 = this.fInputStream.read();
                } else {
                    b1 = this.fBuffer[index] & 255;
                    index++;
                }
                if (b1 == -1) {
                    expectedByte(2, 4);
                }
                if ((b1 & 192) != 128 || ((b1 & 48) == 0 && (b0 & 7) == 0)) {
                    invalidByte(2, 3, b1);
                }
                if (index == this.fOffset) {
                    b2 = this.fInputStream.read();
                } else {
                    b2 = this.fBuffer[index] & 255;
                    index++;
                }
                if (b2 == -1) {
                    expectedByte(3, 4);
                }
                if ((b2 & 192) != 128) {
                    invalidByte(3, 3, b2);
                }
                if (index == this.fOffset) {
                    b3 = this.fInputStream.read();
                    r6 = index;
                } else {
                    r6 = index + 1;
                    b3 = this.fBuffer[index] & 255;
                }
                if (b3 == -1) {
                    expectedByte(4, 4);
                }
                if ((b3 & 192) != 128) {
                    invalidByte(4, 4, b3);
                }
                int uuuuu = ((b0 << 2) & 28) | ((b1 >> 4) & 3);
                if (uuuuu > 16) {
                    invalidSurrogate(uuuuu);
                }
                c = ((55296 | (((uuuuu - 1) << 6) & 960)) | ((b1 << 2) & 60)) | ((b2 >> 4) & 3);
                this.fSurrogate = (56320 | ((b2 << 6) & 960)) | (b3 & 63);
            } else {
                invalidByte(1, 1, b0);
            }
        } else {
            this.fSurrogate = -1;
        }
        return c;
    }

    public int read(char[] ch, int offset, int length) throws IOException {
        int out;
        int count;
        int out2 = offset;
        if (this.fOffset == 0) {
            if (length > this.fBuffer.length) {
                length = this.fBuffer.length;
            }
            if (this.fSurrogate != -1) {
                out = out2 + 1;
                ch[out2] = (char) this.fSurrogate;
                this.fSurrogate = -1;
                length--;
                out2 = out;
            }
            count = this.fInputStream.read(this.fBuffer, 0, length);
            if (count == -1) {
                return -1;
            }
            count += out2 - offset;
        } else {
            count = this.fOffset;
            this.fOffset = 0;
        }
        int total = count;
        int in = 0;
        out = out2;
        while (in < total) {
            byte byte1 = this.fBuffer[in];
            if (byte1 < (byte) 0) {
                break;
            }
            out2 = out + 1;
            ch[out] = (char) byte1;
            in++;
            out = out2;
        }
        while (in < total) {
            byte1 = this.fBuffer[in];
            if (byte1 >= (byte) 0) {
                out2 = out + 1;
                ch[out] = (char) byte1;
            } else {
                int b0 = byte1 & 255;
                int b1;
                if ((b0 & 224) == 192 && (b0 & 30) != 0) {
                    in++;
                    if (in < total) {
                        b1 = this.fBuffer[in] & 255;
                    } else {
                        b1 = this.fInputStream.read();
                        if (b1 == -1) {
                            if (out > offset) {
                                this.fBuffer[0] = (byte) b0;
                                this.fOffset = 1;
                                out2 = out;
                                return out - offset;
                            }
                            expectedByte(2, 2);
                        }
                        count++;
                    }
                    if ((b1 & 192) != 128) {
                        if (out > offset) {
                            this.fBuffer[0] = (byte) b0;
                            this.fBuffer[1] = (byte) b1;
                            this.fOffset = 2;
                            out2 = out;
                            return out - offset;
                        }
                        invalidByte(2, 2, b1);
                    }
                    out2 = out + 1;
                    ch[out] = (char) (((b0 << 6) & 1984) | (b1 & 63));
                    count--;
                } else if ((b0 & 240) == 224) {
                    in++;
                    if (in < total) {
                        b1 = this.fBuffer[in] & 255;
                    } else {
                        b1 = this.fInputStream.read();
                        if (b1 == -1) {
                            if (out > offset) {
                                this.fBuffer[0] = (byte) b0;
                                this.fOffset = 1;
                                out2 = out;
                                return out - offset;
                            }
                            expectedByte(2, 3);
                        }
                        count++;
                    }
                    if ((b1 & 192) != 128 || ((b0 == 237 && b1 >= 160) || ((b0 & 15) == 0 && (b1 & 32) == 0))) {
                        if (out > offset) {
                            this.fBuffer[0] = (byte) b0;
                            this.fBuffer[1] = (byte) b1;
                            this.fOffset = 2;
                            out2 = out;
                            return out - offset;
                        }
                        invalidByte(2, 3, b1);
                    }
                    in++;
                    if (in < total) {
                        b2 = this.fBuffer[in] & 255;
                    } else {
                        b2 = this.fInputStream.read();
                        if (b2 == -1) {
                            if (out > offset) {
                                this.fBuffer[0] = (byte) b0;
                                this.fBuffer[1] = (byte) b1;
                                this.fOffset = 2;
                                out2 = out;
                                return out - offset;
                            }
                            expectedByte(3, 3);
                        }
                        count++;
                    }
                    if ((b2 & 192) != 128) {
                        if (out > offset) {
                            this.fBuffer[0] = (byte) b0;
                            this.fBuffer[1] = (byte) b1;
                            this.fBuffer[2] = (byte) b2;
                            this.fOffset = 3;
                            out2 = out;
                            return out - offset;
                        }
                        invalidByte(3, 3, b2);
                    }
                    out2 = out + 1;
                    ch[out] = (char) ((((b0 << 12) & 61440) | ((b1 << 6) & 4032)) | (b2 & 63));
                    count -= 2;
                } else if ((b0 & 248) == 240) {
                    int b3;
                    in++;
                    if (in < total) {
                        b1 = this.fBuffer[in] & 255;
                    } else {
                        b1 = this.fInputStream.read();
                        if (b1 == -1) {
                            if (out > offset) {
                                this.fBuffer[0] = (byte) b0;
                                this.fOffset = 1;
                                out2 = out;
                                return out - offset;
                            }
                            expectedByte(2, 4);
                        }
                        count++;
                    }
                    if ((b1 & 192) != 128 || ((b1 & 48) == 0 && (b0 & 7) == 0)) {
                        if (out > offset) {
                            this.fBuffer[0] = (byte) b0;
                            this.fBuffer[1] = (byte) b1;
                            this.fOffset = 2;
                            out2 = out;
                            return out - offset;
                        }
                        invalidByte(2, 4, b1);
                    }
                    in++;
                    if (in < total) {
                        b2 = this.fBuffer[in] & 255;
                    } else {
                        b2 = this.fInputStream.read();
                        if (b2 == -1) {
                            if (out > offset) {
                                this.fBuffer[0] = (byte) b0;
                                this.fBuffer[1] = (byte) b1;
                                this.fOffset = 2;
                                out2 = out;
                                return out - offset;
                            }
                            expectedByte(3, 4);
                        }
                        count++;
                    }
                    if ((b2 & 192) != 128) {
                        if (out > offset) {
                            this.fBuffer[0] = (byte) b0;
                            this.fBuffer[1] = (byte) b1;
                            this.fBuffer[2] = (byte) b2;
                            this.fOffset = 3;
                            out2 = out;
                            return out - offset;
                        }
                        invalidByte(3, 4, b2);
                    }
                    in++;
                    if (in < total) {
                        b3 = this.fBuffer[in] & 255;
                    } else {
                        b3 = this.fInputStream.read();
                        if (b3 == -1) {
                            if (out > offset) {
                                this.fBuffer[0] = (byte) b0;
                                this.fBuffer[1] = (byte) b1;
                                this.fBuffer[2] = (byte) b2;
                                this.fOffset = 3;
                                out2 = out;
                                return out - offset;
                            }
                            expectedByte(4, 4);
                        }
                        count++;
                    }
                    if ((b3 & 192) != 128) {
                        if (out > offset) {
                            this.fBuffer[0] = (byte) b0;
                            this.fBuffer[1] = (byte) b1;
                            this.fBuffer[2] = (byte) b2;
                            this.fBuffer[3] = (byte) b3;
                            this.fOffset = 4;
                            out2 = out;
                            return out - offset;
                        }
                        invalidByte(4, 4, b2);
                    }
                    int uuuuu = ((b0 << 2) & 28) | ((b1 >> 4) & 3);
                    if (uuuuu > 16) {
                        invalidSurrogate(uuuuu);
                    }
                    int yyyyyy = b2 & 63;
                    int ls = (56320 | ((yyyyyy << 6) & 960)) | (b3 & 63);
                    out2 = out + 1;
                    ch[out] = (char) (((55296 | (((uuuuu - 1) << 6) & 960)) | ((b1 & 15) << 2)) | (yyyyyy >> 4));
                    count -= 2;
                    if (count <= length) {
                        out = out2 + 1;
                        ch[out2] = (char) ls;
                        out2 = out;
                    } else {
                        this.fSurrogate = ls;
                        count--;
                    }
                } else if (out > offset) {
                    this.fBuffer[0] = (byte) b0;
                    this.fOffset = 1;
                    out2 = out;
                    return out - offset;
                } else {
                    invalidByte(1, 1, b0);
                    out2 = out;
                }
            }
            in++;
            out = out2;
        }
        out2 = out;
        return count;
    }

    public long skip(long n) throws IOException {
        long remaining = n;
        char[] ch = new char[this.fBuffer.length];
        do {
            int count = read(ch, 0, ((long) ch.length) < remaining ? ch.length : (int) remaining);
            if (count <= 0) {
                break;
            }
            remaining -= (long) count;
        } while (remaining > 0);
        return n - remaining;
    }

    public boolean ready() throws IOException {
        return false;
    }

    public boolean markSupported() {
        return false;
    }

    public void mark(int readAheadLimit) throws IOException {
        throw new IOException(this.fFormatter.formatMessage(this.fLocale, "OperationNotSupported", new Object[]{"mark()", "UTF-8"}));
    }

    public void reset() throws IOException {
        this.fOffset = 0;
        this.fSurrogate = -1;
    }

    public void close() throws IOException {
        this.fInputStream.close();
    }

    private void expectedByte(int position, int count) throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "ExpectedByte", new Object[]{Integer.toString(position), Integer.toString(count)});
    }

    private void invalidByte(int position, int count, int c) throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidByte", new Object[]{Integer.toString(position), Integer.toString(count)});
    }

    private void invalidSurrogate(int uuuuu) throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidHighSurrogate", new Object[]{Integer.toHexString(uuuuu)});
    }
}
