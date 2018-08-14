package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import java.io.IOException;

@ThreadSafe
public class BasicHttpCacheStorage implements HttpCacheStorage {
    private final CacheMap entries;

    public BasicHttpCacheStorage(CacheConfig config) {
        this.entries = new CacheMap(config.getMaxCacheEntries());
    }

    public synchronized void putEntry(String url, HttpCacheEntry entry) throws IOException {
        this.entries.put(url, entry);
    }

    public synchronized HttpCacheEntry getEntry(String url) throws IOException {
        return (HttpCacheEntry) this.entries.get(url);
    }

    public synchronized void removeEntry(String url) throws IOException {
        this.entries.remove(url);
    }

    public synchronized void updateEntry(String url, HttpCacheUpdateCallback callback) throws IOException {
        this.entries.put(url, callback.update((HttpCacheEntry) this.entries.get(url)));
    }
}
