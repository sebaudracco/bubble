package cz.msebera.android.httpclient.entity.mime.content;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.MIME;
import cz.msebera.android.httpclient.util.Args;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileBody extends AbstractContentBody {
    private final File file;
    private final String filename;

    @Deprecated
    public FileBody(File file, String filename, String mimeType, String charset) {
        this(file, ContentType.create(mimeType, charset), filename);
    }

    @Deprecated
    public FileBody(File file, String mimeType, String charset) {
        this(file, null, mimeType, charset);
    }

    @Deprecated
    public FileBody(File file, String mimeType) {
        this(file, ContentType.create(mimeType), null);
    }

    public FileBody(File file) {
        this(file, ContentType.DEFAULT_BINARY, file != null ? file.getName() : null);
    }

    public FileBody(File file, ContentType contentType, String filename) {
        super(contentType);
        Args.notNull(file, "File");
        this.file = file;
        this.filename = filename;
    }

    public FileBody(File file, ContentType contentType) {
        this(file, contentType, null);
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public void writeTo(OutputStream out) throws IOException {
        Args.notNull(out, "Output stream");
        InputStream in = new FileInputStream(this.file);
        try {
            byte[] tmp = new byte[4096];
            while (true) {
                int l = in.read(tmp);
                if (l == -1) {
                    break;
                }
                out.write(tmp, 0, l);
            }
            out.flush();
        } finally {
            in.close();
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public long getContentLength() {
        return this.file.length();
    }

    public String getFilename() {
        return this.filename;
    }

    public File getFile() {
        return this.file;
    }
}
