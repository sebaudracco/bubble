package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;

class AsynchronousValidator implements Closeable {
    private final CacheKeyGenerator cacheKeyGenerator;
    private final FailureCache failureCache;
    public HttpClientAndroidLog log;
    private final Set<String> queued;
    private final SchedulingStrategy schedulingStrategy;

    public AsynchronousValidator(CacheConfig config) {
        this(new ImmediateSchedulingStrategy(config));
    }

    AsynchronousValidator(SchedulingStrategy schedulingStrategy) {
        this.log = new HttpClientAndroidLog(getClass());
        this.schedulingStrategy = schedulingStrategy;
        this.queued = new HashSet();
        this.cacheKeyGenerator = new CacheKeyGenerator();
        this.failureCache = new DefaultFailureCache();
    }

    public void close() throws IOException {
        this.schedulingStrategy.close();
    }

    public synchronized void revalidateCacheEntry(CachingExec cachingExec, HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry entry) {
        String uri = this.cacheKeyGenerator.getVariantURI(context.getTargetHost(), request, entry);
        if (!this.queued.contains(uri)) {
            try {
                this.schedulingStrategy.schedule(new AsynchronousValidationRequest(this, cachingExec, route, request, context, execAware, entry, uri, this.failureCache.getErrorCount(uri)));
                this.queued.add(uri);
            } catch (RejectedExecutionException ree) {
                this.log.debug("Revalidation for [" + uri + "] not scheduled: " + ree);
            }
        }
    }

    synchronized void markComplete(String identifier) {
        this.queued.remove(identifier);
    }

    void jobSuccessful(String identifier) {
        this.failureCache.resetErrorCount(identifier);
    }

    void jobFailed(String identifier) {
        this.failureCache.increaseErrorCount(identifier);
    }

    Set<String> getScheduledIdentifiers() {
        return Collections.unmodifiableSet(this.queued);
    }
}
