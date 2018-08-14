package cz.msebera.android.httpclient.entity.mime;

import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;
import mf.org.apache.xml.serialize.LineSeparator;

abstract class AbstractMultipartForm {
    private static final ByteArrayBuffer CR_LF = encode(MIME.DEFAULT_CHARSET, LineSeparator.Windows);
    private static final ByteArrayBuffer FIELD_SEP = encode(MIME.DEFAULT_CHARSET, ": ");
    private static final ByteArrayBuffer TWO_DASHES = encode(MIME.DEFAULT_CHARSET, "--");
    private final String boundary;
    protected final Charset charset;
    private final String subType;

    protected abstract void formatMultipartHeader(FormBodyPart formBodyPart, OutputStream outputStream) throws IOException;

    public abstract List<FormBodyPart> getBodyParts();

    private static ByteArrayBuffer encode(Charset charset, String string) {
        ByteBuffer encoded = charset.encode(CharBuffer.wrap(string));
        ByteArrayBuffer bab = new ByteArrayBuffer(encoded.remaining());
        bab.append(encoded.array(), encoded.position(), encoded.remaining());
        return bab;
    }

    private static void writeBytes(ByteArrayBuffer b, OutputStream out) throws IOException {
        out.write(b.buffer(), 0, b.length());
    }

    private static void writeBytes(String s, Charset charset, OutputStream out) throws IOException {
        writeBytes(encode(charset, s), out);
    }

    private static void writeBytes(String s, OutputStream out) throws IOException {
        writeBytes(encode(MIME.DEFAULT_CHARSET, s), out);
    }

    protected static void writeField(MinimalField field, OutputStream out) throws IOException {
        writeBytes(field.getName(), out);
        writeBytes(FIELD_SEP, out);
        writeBytes(field.getBody(), out);
        writeBytes(CR_LF, out);
    }

    protected static void writeField(MinimalField field, Charset charset, OutputStream out) throws IOException {
        writeBytes(field.getName(), charset, out);
        writeBytes(FIELD_SEP, out);
        writeBytes(field.getBody(), charset, out);
        writeBytes(CR_LF, out);
    }

    public AbstractMultipartForm(String subType, Charset charset, String boundary) {
        Args.notNull(subType, "Multipart subtype");
        Args.notNull(boundary, "Multipart boundary");
        this.subType = subType;
        if (charset == null) {
            charset = MIME.DEFAULT_CHARSET;
        }
        this.charset = charset;
        this.boundary = boundary;
    }

    public AbstractMultipartForm(String subType, String boundary) {
        this(subType, null, boundary);
    }

    public String getSubType() {
        return this.subType;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getBoundary() {
        return this.boundary;
    }

    void doWriteTo(OutputStream out, boolean writeContent) throws IOException {
        ByteArrayBuffer boundary = encode(this.charset, getBoundary());
        for (FormBodyPart part : getBodyParts()) {
            writeBytes(TWO_DASHES, out);
            writeBytes(boundary, out);
            writeBytes(CR_LF, out);
            formatMultipartHeader(part, out);
            writeBytes(CR_LF, out);
            if (writeContent) {
                part.getBody().writeTo(out);
            }
            writeBytes(CR_LF, out);
        }
        writeBytes(TWO_DASHES, out);
        writeBytes(boundary, out);
        writeBytes(TWO_DASHES, out);
        writeBytes(CR_LF, out);
    }

    public void writeTo(OutputStream out) throws IOException {
        doWriteTo(out, true);
    }

    public long getTotalLength() {
        long contentLen = 0;
        for (FormBodyPart part : getBodyParts()) {
            long len = part.getBody().getContentLength();
            if (len < 0) {
                return -1;
            }
            contentLen += len;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            doWriteTo(out, false);
            return ((long) out.toByteArray().length) + contentLen;
        } catch (IOException e) {
            return -1;
        }
    }
}
