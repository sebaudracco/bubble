package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Immutable
public class HeapResource implements Resource {
    private static final long serialVersionUID = -2078599905620463394L;
    private final byte[] f12618b;

    public HeapResource(byte[] b) {
        this.f12618b = b;
    }

    byte[] getByteArray() {
        return this.f12618b;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.f12618b);
    }

    public long length() {
        return (long) this.f12618b.length;
    }

    public void dispose() {
    }
}
