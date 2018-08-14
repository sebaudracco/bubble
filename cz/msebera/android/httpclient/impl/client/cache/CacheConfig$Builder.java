package cz.msebera.android.httpclient.impl.client.cache;

import android.support.v4.media.session.PlaybackStateCompat;

public class CacheConfig$Builder {
    private boolean allow303Caching = false;
    private int asynchronousWorkerIdleLifetimeSecs = 60;
    private int asynchronousWorkersCore = 1;
    private int asynchronousWorkersMax = 1;
    private boolean heuristicCachingEnabled = false;
    private float heuristicCoefficient = 0.1f;
    private long heuristicDefaultLifetime = 0;
    private boolean isSharedCache = true;
    private int maxCacheEntries = 1000;
    private long maxObjectSize = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
    private int maxUpdateRetries = 1;
    private boolean neverCacheHTTP10ResponsesWithQuery;
    private int revalidationQueueSize = 100;
    private boolean weakETagOnPutDeleteAllowed = false;

    CacheConfig$Builder() {
    }

    public CacheConfig$Builder setMaxObjectSize(long maxObjectSize) {
        this.maxObjectSize = maxObjectSize;
        return this;
    }

    public CacheConfig$Builder setMaxCacheEntries(int maxCacheEntries) {
        this.maxCacheEntries = maxCacheEntries;
        return this;
    }

    public CacheConfig$Builder setMaxUpdateRetries(int maxUpdateRetries) {
        this.maxUpdateRetries = maxUpdateRetries;
        return this;
    }

    public CacheConfig$Builder setAllow303Caching(boolean allow303Caching) {
        this.allow303Caching = allow303Caching;
        return this;
    }

    public CacheConfig$Builder setWeakETagOnPutDeleteAllowed(boolean weakETagOnPutDeleteAllowed) {
        this.weakETagOnPutDeleteAllowed = weakETagOnPutDeleteAllowed;
        return this;
    }

    public CacheConfig$Builder setHeuristicCachingEnabled(boolean heuristicCachingEnabled) {
        this.heuristicCachingEnabled = heuristicCachingEnabled;
        return this;
    }

    public CacheConfig$Builder setHeuristicCoefficient(float heuristicCoefficient) {
        this.heuristicCoefficient = heuristicCoefficient;
        return this;
    }

    public CacheConfig$Builder setHeuristicDefaultLifetime(long heuristicDefaultLifetime) {
        this.heuristicDefaultLifetime = heuristicDefaultLifetime;
        return this;
    }

    public CacheConfig$Builder setSharedCache(boolean isSharedCache) {
        this.isSharedCache = isSharedCache;
        return this;
    }

    public CacheConfig$Builder setAsynchronousWorkersMax(int asynchronousWorkersMax) {
        this.asynchronousWorkersMax = asynchronousWorkersMax;
        return this;
    }

    public CacheConfig$Builder setAsynchronousWorkersCore(int asynchronousWorkersCore) {
        this.asynchronousWorkersCore = asynchronousWorkersCore;
        return this;
    }

    public CacheConfig$Builder setAsynchronousWorkerIdleLifetimeSecs(int asynchronousWorkerIdleLifetimeSecs) {
        this.asynchronousWorkerIdleLifetimeSecs = asynchronousWorkerIdleLifetimeSecs;
        return this;
    }

    public CacheConfig$Builder setRevalidationQueueSize(int revalidationQueueSize) {
        this.revalidationQueueSize = revalidationQueueSize;
        return this;
    }

    public CacheConfig$Builder setNeverCacheHTTP10ResponsesWithQueryString(boolean neverCacheHTTP10ResponsesWithQuery) {
        this.neverCacheHTTP10ResponsesWithQuery = neverCacheHTTP10ResponsesWithQuery;
        return this;
    }

    public CacheConfig build() {
        return new CacheConfig(this.maxObjectSize, this.maxCacheEntries, this.maxUpdateRetries, this.allow303Caching, this.weakETagOnPutDeleteAllowed, this.heuristicCachingEnabled, this.heuristicCoefficient, this.heuristicDefaultLifetime, this.isSharedCache, this.asynchronousWorkersMax, this.asynchronousWorkersCore, this.asynchronousWorkerIdleLifetimeSecs, this.revalidationQueueSize, this.neverCacheHTTP10ResponsesWithQuery);
    }
}
