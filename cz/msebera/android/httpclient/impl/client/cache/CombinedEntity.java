package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import cz.msebera.android.httpclient.util.Args;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

@NotThreadSafe
class CombinedEntity extends AbstractHttpEntity {
    private final InputStream combinedStream;
    private final Resource resource;

    class ResourceStream extends FilterInputStream {
        protected ResourceStream(InputStream in) {
            super(in);
        }

        public void close() throws IOException {
            try {
                super.close();
            } finally {
                CombinedEntity.this.dispose();
            }
        }
    }

    CombinedEntity(Resource resource, InputStream instream) throws IOException {
        this.resource = resource;
        this.combinedStream = new SequenceInputStream(new ResourceStream(resource.getInputStream()), instream);
    }

    public long getContentLength() {
        return -1;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return true;
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return this.combinedStream;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        InputStream instream = getContent();
        try {
            byte[] tmp = new byte[2048];
            while (true) {
                int l = instream.read(tmp);
                if (l == -1) {
                    break;
                }
                outstream.write(tmp, 0, l);
            }
        } finally {
            instream.close();
        }
    }

    private void dispose() {
        this.resource.dispose();
    }
}
