package cz.msebera.android.httpclient.impl.client.cache;

import com.mopub.common.MoPubBrowser;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import cz.msebera.android.httpclient.util.Args;
import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class ManagedHttpCacheStorage implements HttpCacheStorage, Closeable {
    private final AtomicBoolean active = new AtomicBoolean(true);
    private final CacheMap entries;
    private final ReferenceQueue<HttpCacheEntry> morque = new ReferenceQueue();
    private final Set<ResourceReference> resources = new HashSet();

    public ManagedHttpCacheStorage(CacheConfig config) {
        this.entries = new CacheMap(config.getMaxCacheEntries());
    }

    private void ensureValidState() throws IllegalStateException {
        if (!this.active.get()) {
            throw new IllegalStateException("Cache has been shut down");
        }
    }

    private void keepResourceReference(HttpCacheEntry entry) {
        if (entry.getResource() != null) {
            this.resources.add(new ResourceReference(entry, this.morque));
        }
    }

    public void putEntry(String url, HttpCacheEntry entry) throws IOException {
        Args.notNull(url, MoPubBrowser.DESTINATION_URL_KEY);
        Args.notNull(entry, "Cache entry");
        ensureValidState();
        synchronized (this) {
            this.entries.put(url, entry);
            keepResourceReference(entry);
        }
    }

    public HttpCacheEntry getEntry(String url) throws IOException {
        HttpCacheEntry httpCacheEntry;
        Args.notNull(url, MoPubBrowser.DESTINATION_URL_KEY);
        ensureValidState();
        synchronized (this) {
            httpCacheEntry = (HttpCacheEntry) this.entries.get(url);
        }
        return httpCacheEntry;
    }

    public void removeEntry(String url) throws IOException {
        Args.notNull(url, MoPubBrowser.DESTINATION_URL_KEY);
        ensureValidState();
        synchronized (this) {
            this.entries.remove(url);
        }
    }

    public void updateEntry(String url, HttpCacheUpdateCallback callback) throws IOException {
        Args.notNull(url, MoPubBrowser.DESTINATION_URL_KEY);
        Args.notNull(callback, "Callback");
        ensureValidState();
        synchronized (this) {
            HttpCacheEntry existing = (HttpCacheEntry) this.entries.get(url);
            HttpCacheEntry updated = callback.update(existing);
            this.entries.put(url, updated);
            if (existing != updated) {
                keepResourceReference(updated);
            }
        }
    }

    public void cleanResources() {
        if (this.active.get()) {
            while (true) {
                ResourceReference ref = (ResourceReference) this.morque.poll();
                if (ref != null) {
                    synchronized (this) {
                        this.resources.remove(ref);
                    }
                    ref.getResource().dispose();
                } else {
                    return;
                }
            }
        }
    }

    public void shutdown() {
        if (this.active.compareAndSet(true, false)) {
            synchronized (this) {
                this.entries.clear();
                for (ResourceReference ref : this.resources) {
                    ref.getResource().dispose();
                }
                this.resources.clear();
                do {
                } while (this.morque.poll() != null);
            }
        }
    }

    public void close() {
        shutdown();
    }
}
