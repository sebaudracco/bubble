package cz.msebera.android.httpclient.entity;

import android.support.v4.media.session.PlaybackStateCompat;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@NotThreadSafe
public class InputStreamEntity extends AbstractHttpEntity {
    private final InputStream content;
    private final long length;

    public InputStreamEntity(InputStream instream) {
        this(instream, -1);
    }

    public InputStreamEntity(InputStream instream, long length) {
        this(instream, length, null);
    }

    public InputStreamEntity(InputStream instream, ContentType contentType) {
        this(instream, -1, contentType);
    }

    public InputStreamEntity(InputStream instream, long length, ContentType contentType) {
        this.content = (InputStream) Args.notNull(instream, "Source input stream");
        this.length = length;
        if (contentType != null) {
            setContentType(contentType.toString());
        }
    }

    public boolean isRepeatable() {
        return false;
    }

    public long getContentLength() {
        return this.length;
    }

    public InputStream getContent() throws IOException {
        return this.content;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        InputStream instream = this.content;
        try {
            byte[] buffer = new byte[4096];
            int l;
            if (this.length < 0) {
                while (true) {
                    l = instream.read(buffer);
                    if (l == -1) {
                        break;
                    }
                    outstream.write(buffer, 0, l);
                }
            } else {
                long remaining = this.length;
                while (remaining > 0) {
                    l = instream.read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, remaining));
                    if (l == -1) {
                        break;
                    }
                    outstream.write(buffer, 0, l);
                    remaining -= (long) l;
                }
            }
            instream.close();
        } catch (Throwable th) {
            instream.close();
        }
    }

    public boolean isStreaming() {
        return true;
    }
}
