package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import cz.msebera.android.httpclient.client.cache.HttpCacheUpdateException;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class BasicHttpCache implements HttpCache {
    private static final Set<String> safeRequestMethods = new HashSet(Arrays.asList(new String[]{"HEAD", "GET", "OPTIONS", "TRACE"}));
    private final CacheEntryUpdater cacheEntryUpdater;
    private final HttpCacheInvalidator cacheInvalidator;
    public HttpClientAndroidLog log;
    private final long maxObjectSizeBytes;
    private final ResourceFactory resourceFactory;
    private final CachedHttpResponseGenerator responseGenerator;
    private final HttpCacheStorage storage;
    private final CacheKeyGenerator uriExtractor;

    public BasicHttpCache(ResourceFactory resourceFactory, HttpCacheStorage storage, CacheConfig config, CacheKeyGenerator uriExtractor, HttpCacheInvalidator cacheInvalidator) {
        this.log = new HttpClientAndroidLog(getClass());
        this.resourceFactory = resourceFactory;
        this.uriExtractor = uriExtractor;
        this.cacheEntryUpdater = new CacheEntryUpdater(resourceFactory);
        this.maxObjectSizeBytes = config.getMaxObjectSize();
        this.responseGenerator = new CachedHttpResponseGenerator();
        this.storage = storage;
        this.cacheInvalidator = cacheInvalidator;
    }

    public BasicHttpCache(ResourceFactory resourceFactory, HttpCacheStorage storage, CacheConfig config, CacheKeyGenerator uriExtractor) {
        this(resourceFactory, storage, config, uriExtractor, new CacheInvalidator(uriExtractor, storage));
    }

    public BasicHttpCache(ResourceFactory resourceFactory, HttpCacheStorage storage, CacheConfig config) {
        this(resourceFactory, storage, config, new CacheKeyGenerator());
    }

    public BasicHttpCache(CacheConfig config) {
        this(new HeapResourceFactory(), new BasicHttpCacheStorage(config), config);
    }

    public BasicHttpCache() {
        this(CacheConfig.DEFAULT);
    }

    public void flushCacheEntriesFor(HttpHost host, HttpRequest request) throws IOException {
        if (!safeRequestMethods.contains(request.getRequestLine().getMethod())) {
            this.storage.removeEntry(this.uriExtractor.getURI(host, request));
        }
    }

    public void flushInvalidatedCacheEntriesFor(HttpHost host, HttpRequest request, HttpResponse response) {
        if (!safeRequestMethods.contains(request.getRequestLine().getMethod())) {
            this.cacheInvalidator.flushInvalidatedCacheEntries(host, request, response);
        }
    }

    void storeInCache(HttpHost target, HttpRequest request, HttpCacheEntry entry) throws IOException {
        if (entry.hasVariants()) {
            storeVariantEntry(target, request, entry);
        } else {
            storeNonVariantEntry(target, request, entry);
        }
    }

    void storeNonVariantEntry(HttpHost target, HttpRequest req, HttpCacheEntry entry) throws IOException {
        this.storage.putEntry(this.uriExtractor.getURI(target, req), entry);
    }

    void storeVariantEntry(HttpHost target, final HttpRequest req, final HttpCacheEntry entry) throws IOException {
        String parentURI = this.uriExtractor.getURI(target, req);
        final String variantURI = this.uriExtractor.getVariantURI(target, req, entry);
        this.storage.putEntry(variantURI, entry);
        try {
            this.storage.updateEntry(parentURI, new HttpCacheUpdateCallback() {
                public HttpCacheEntry update(HttpCacheEntry existing) throws IOException {
                    return BasicHttpCache.this.doGetUpdatedParentEntry(req.getRequestLine().getUri(), existing, entry, BasicHttpCache.this.uriExtractor.getVariantKey(req, entry), variantURI);
                }
            });
        } catch (HttpCacheUpdateException e) {
            this.log.warn("Could not update key [" + parentURI + "]", e);
        }
    }

    public void reuseVariantEntryFor(HttpHost target, HttpRequest req, Variant variant) throws IOException {
        String parentCacheKey = this.uriExtractor.getURI(target, req);
        final HttpCacheEntry entry = variant.getEntry();
        final String variantKey = this.uriExtractor.getVariantKey(req, entry);
        final String variantCacheKey = variant.getCacheKey();
        final HttpRequest httpRequest = req;
        try {
            this.storage.updateEntry(parentCacheKey, new HttpCacheUpdateCallback() {
                public HttpCacheEntry update(HttpCacheEntry existing) throws IOException {
                    return BasicHttpCache.this.doGetUpdatedParentEntry(httpRequest.getRequestLine().getUri(), existing, entry, variantKey, variantCacheKey);
                }
            });
        } catch (HttpCacheUpdateException e) {
            this.log.warn("Could not update key [" + parentCacheKey + "]", e);
        }
    }

    boolean isIncompleteResponse(HttpResponse resp, Resource resource) {
        int status = resp.getStatusLine().getStatusCode();
        if (status != 200 && status != HttpStatus.SC_PARTIAL_CONTENT) {
            return false;
        }
        Header hdr = resp.getFirstHeader("Content-Length");
        if (hdr == null) {
            return false;
        }
        try {
            if (resource.length() < ((long) Integer.parseInt(hdr.getValue()))) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    CloseableHttpResponse generateIncompleteResponseError(HttpResponse response, Resource resource) {
        int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());
        HttpResponse error = new BasicHttpResponse(HttpVersion.HTTP_1_1, (int) HttpStatus.SC_BAD_GATEWAY, "Bad Gateway");
        error.setHeader("Content-Type", "text/plain;charset=UTF-8");
        byte[] msgBytes = String.format("Received incomplete response with Content-Length %d but actual body length %d", new Object[]{Integer.valueOf(contentLength), Long.valueOf(resource.length())}).getBytes();
        error.setHeader("Content-Length", Integer.toString(msgBytes.length));
        error.setEntity(new ByteArrayEntity(msgBytes));
        return Proxies.enhanceResponse(error);
    }

    HttpCacheEntry doGetUpdatedParentEntry(String requestId, HttpCacheEntry existing, HttpCacheEntry entry, String variantKey, String variantCacheKey) throws IOException {
        HttpCacheEntry src = existing;
        if (src == null) {
            src = entry;
        }
        Resource resource = null;
        if (src.getResource() != null) {
            resource = this.resourceFactory.copy(requestId, src.getResource());
        }
        Map<String, String> variantMap = new HashMap(src.getVariantMap());
        variantMap.put(variantKey, variantCacheKey);
        return new HttpCacheEntry(src.getRequestDate(), src.getResponseDate(), src.getStatusLine(), src.getAllHeaders(), resource, variantMap);
    }

    public HttpCacheEntry updateCacheEntry(HttpHost target, HttpRequest request, HttpCacheEntry stale, HttpResponse originResponse, Date requestSent, Date responseReceived) throws IOException {
        HttpCacheEntry updatedEntry = this.cacheEntryUpdater.updateCacheEntry(request.getRequestLine().getUri(), stale, requestSent, responseReceived, originResponse);
        storeInCache(target, request, updatedEntry);
        return updatedEntry;
    }

    public HttpCacheEntry updateVariantCacheEntry(HttpHost target, HttpRequest request, HttpCacheEntry stale, HttpResponse originResponse, Date requestSent, Date responseReceived, String cacheKey) throws IOException {
        HttpCacheEntry updatedEntry = this.cacheEntryUpdater.updateCacheEntry(request.getRequestLine().getUri(), stale, requestSent, responseReceived, originResponse);
        this.storage.putEntry(cacheKey, updatedEntry);
        return updatedEntry;
    }

    public HttpResponse cacheAndReturnResponse(HttpHost host, HttpRequest request, HttpResponse originResponse, Date requestSent, Date responseReceived) throws IOException {
        return cacheAndReturnResponse(host, request, Proxies.enhanceResponse(originResponse), requestSent, responseReceived);
    }

    public CloseableHttpResponse cacheAndReturnResponse(HttpHost host, HttpRequest request, CloseableHttpResponse originResponse, Date requestSent, Date responseReceived) throws IOException {
        boolean closeOriginResponse = true;
        SizeLimitedResponseReader responseReader = getResponseReader(request, originResponse);
        try {
            CloseableHttpResponse reconstructedResponse;
            responseReader.readResponse();
            if (responseReader.isLimitReached()) {
                closeOriginResponse = false;
                reconstructedResponse = responseReader.getReconstructedResponse();
            } else {
                Resource resource = responseReader.getResource();
                if (isIncompleteResponse(originResponse, resource)) {
                    reconstructedResponse = generateIncompleteResponseError(originResponse, resource);
                    if (1 != null) {
                        originResponse.close();
                    }
                } else {
                    HttpCacheEntry entry = new HttpCacheEntry(requestSent, responseReceived, originResponse.getStatusLine(), originResponse.getAllHeaders(), resource);
                    storeInCache(host, request, entry);
                    reconstructedResponse = this.responseGenerator.generateResponse(entry);
                    if (1 != null) {
                        originResponse.close();
                    }
                }
            }
            return reconstructedResponse;
        } finally {
            if (closeOriginResponse) {
                originResponse.close();
            }
        }
    }

    SizeLimitedResponseReader getResponseReader(HttpRequest request, CloseableHttpResponse backEndResponse) {
        return new SizeLimitedResponseReader(this.resourceFactory, this.maxObjectSizeBytes, request, backEndResponse);
    }

    public HttpCacheEntry getCacheEntry(HttpHost host, HttpRequest request) throws IOException {
        HttpCacheEntry root = this.storage.getEntry(this.uriExtractor.getURI(host, request));
        if (root == null) {
            return null;
        }
        if (!root.hasVariants()) {
            return root;
        }
        String variantCacheKey = (String) root.getVariantMap().get(this.uriExtractor.getVariantKey(request, root));
        if (variantCacheKey == null) {
            return null;
        }
        return this.storage.getEntry(variantCacheKey);
    }

    public void flushInvalidatedCacheEntriesFor(HttpHost host, HttpRequest request) throws IOException {
        this.cacheInvalidator.flushInvalidatedCacheEntries(host, request);
    }

    public Map<String, Variant> getVariantCacheEntriesWithEtags(HttpHost host, HttpRequest request) throws IOException {
        Map<String, Variant> variants = new HashMap();
        HttpCacheEntry root = this.storage.getEntry(this.uriExtractor.getURI(host, request));
        if (root != null && root.hasVariants()) {
            for (Entry<String, String> variant : root.getVariantMap().entrySet()) {
                addVariantWithEtag((String) variant.getKey(), (String) variant.getValue(), variants);
            }
        }
        return variants;
    }

    private void addVariantWithEtag(String variantKey, String variantCacheKey, Map<String, Variant> variants) throws IOException {
        HttpCacheEntry entry = this.storage.getEntry(variantCacheKey);
        if (entry != null) {
            Header etagHeader = entry.getFirstHeader("ETag");
            if (etagHeader != null) {
                variants.put(etagHeader.getValue(), new Variant(variantKey, variantCacheKey, entry));
            }
        }
    }
}
