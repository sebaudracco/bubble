package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import java.io.File;

public class CachingHttpClientBuilder extends HttpClientBuilder {
    private CacheConfig cacheConfig;
    private File cacheDir;
    private HttpCacheInvalidator httpCacheInvalidator;
    private ResourceFactory resourceFactory;
    private SchedulingStrategy schedulingStrategy;
    private HttpCacheStorage storage;

    public static CachingHttpClientBuilder create() {
        return new CachingHttpClientBuilder();
    }

    protected CachingHttpClientBuilder() {
    }

    public final CachingHttpClientBuilder setResourceFactory(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
        return this;
    }

    public final CachingHttpClientBuilder setHttpCacheStorage(HttpCacheStorage storage) {
        this.storage = storage;
        return this;
    }

    public final CachingHttpClientBuilder setCacheDir(File cacheDir) {
        this.cacheDir = cacheDir;
        return this;
    }

    public final CachingHttpClientBuilder setCacheConfig(CacheConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
        return this;
    }

    public final CachingHttpClientBuilder setSchedulingStrategy(SchedulingStrategy schedulingStrategy) {
        this.schedulingStrategy = schedulingStrategy;
        return this;
    }

    public final CachingHttpClientBuilder setHttpCacheInvalidator(HttpCacheInvalidator cacheInvalidator) {
        this.httpCacheInvalidator = cacheInvalidator;
        return this;
    }

    protected ClientExecChain decorateMainExec(ClientExecChain mainExec) {
        CacheConfig config = this.cacheConfig != null ? this.cacheConfig : CacheConfig.DEFAULT;
        ResourceFactory resourceFactory = this.resourceFactory;
        if (resourceFactory == null) {
            if (this.cacheDir == null) {
                resourceFactory = new HeapResourceFactory();
            } else {
                resourceFactory = new FileResourceFactory(this.cacheDir);
            }
        }
        HttpCacheStorage storage = this.storage;
        if (storage == null) {
            if (this.cacheDir == null) {
                storage = new BasicHttpCacheStorage(config);
            } else {
                ManagedHttpCacheStorage managedStorage = new ManagedHttpCacheStorage(config);
                addCloseable(managedStorage);
                Object storage2 = managedStorage;
            }
        }
        AsynchronousValidator revalidator = createAsynchronousRevalidator(config);
        CacheKeyGenerator uriExtractor = new CacheKeyGenerator();
        HttpCacheInvalidator cacheInvalidator = this.httpCacheInvalidator;
        if (cacheInvalidator == null) {
            cacheInvalidator = new CacheInvalidator(uriExtractor, storage);
        }
        return new CachingExec(mainExec, new BasicHttpCache(resourceFactory, storage, config, uriExtractor, cacheInvalidator), config, revalidator);
    }

    private AsynchronousValidator createAsynchronousRevalidator(CacheConfig config) {
        if (config.getAsynchronousWorkersMax() <= 0) {
            return null;
        }
        AsynchronousValidator revalidator = new AsynchronousValidator(createSchedulingStrategy(config));
        addCloseable(revalidator);
        return revalidator;
    }

    private SchedulingStrategy createSchedulingStrategy(CacheConfig config) {
        return this.schedulingStrategy != null ? this.schedulingStrategy : new ImmediateSchedulingStrategy(config);
    }
}
