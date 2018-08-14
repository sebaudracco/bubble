package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntrySerializationException;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntrySerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

@Immutable
public class DefaultHttpCacheEntrySerializer implements HttpCacheEntrySerializer {
    public void writeTo(HttpCacheEntry cacheEntry, OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        try {
            oos.writeObject(cacheEntry);
        } finally {
            oos.close();
        }
    }

    public HttpCacheEntry readFrom(InputStream is) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(is);
        try {
            HttpCacheEntry httpCacheEntry = (HttpCacheEntry) ois.readObject();
            ois.close();
            return httpCacheEntry;
        } catch (ClassNotFoundException ex) {
            throw new HttpCacheEntrySerializationException("Class not found: " + ex.getMessage(), ex);
        } catch (Throwable th) {
            ois.close();
        }
    }
}
