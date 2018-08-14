package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.cache.CacheResponseStatus;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.cache.HttpCacheContext;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.VersionInfo;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.slf4j.Marker;

@ThreadSafe
public class CachingExec implements ClientExecChain {
    private static final boolean SUPPORTS_RANGE_AND_CONTENT_RANGE_HEADERS = false;
    private final AsynchronousValidator asynchRevalidator;
    private final ClientExecChain backend;
    private final CacheConfig cacheConfig;
    private final AtomicLong cacheHits;
    private final AtomicLong cacheMisses;
    private final AtomicLong cacheUpdates;
    private final CacheableRequestPolicy cacheableRequestPolicy;
    private final ConditionalRequestBuilder conditionalRequestBuilder;
    public HttpClientAndroidLog log;
    private final RequestProtocolCompliance requestCompliance;
    private final HttpCache responseCache;
    private final ResponseCachingPolicy responseCachingPolicy;
    private final ResponseProtocolCompliance responseCompliance;
    private final CachedHttpResponseGenerator responseGenerator;
    private final CachedResponseSuitabilityChecker suitabilityChecker;
    private final CacheValidityPolicy validityPolicy;
    private final Map<ProtocolVersion, String> viaHeaders;

    public CachingExec(ClientExecChain backend, HttpCache cache, CacheConfig config) {
        this(backend, cache, config, null);
    }

    public CachingExec(ClientExecChain backend, HttpCache cache, CacheConfig config, AsynchronousValidator asynchRevalidator) {
        this.cacheHits = new AtomicLong();
        this.cacheMisses = new AtomicLong();
        this.cacheUpdates = new AtomicLong();
        this.viaHeaders = new HashMap(4);
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(backend, "HTTP backend");
        Args.notNull(cache, "HttpCache");
        if (config == null) {
            config = CacheConfig.DEFAULT;
        }
        this.cacheConfig = config;
        this.backend = backend;
        this.responseCache = cache;
        this.validityPolicy = new CacheValidityPolicy();
        this.responseGenerator = new CachedHttpResponseGenerator(this.validityPolicy);
        this.cacheableRequestPolicy = new CacheableRequestPolicy();
        this.suitabilityChecker = new CachedResponseSuitabilityChecker(this.validityPolicy, this.cacheConfig);
        this.conditionalRequestBuilder = new ConditionalRequestBuilder();
        this.responseCompliance = new ResponseProtocolCompliance();
        this.requestCompliance = new RequestProtocolCompliance(this.cacheConfig.isWeakETagOnPutDeleteAllowed());
        this.responseCachingPolicy = new ResponseCachingPolicy(this.cacheConfig.getMaxObjectSize(), this.cacheConfig.isSharedCache(), this.cacheConfig.isNeverCacheHTTP10ResponsesWithQuery(), this.cacheConfig.is303CachingEnabled());
        this.asynchRevalidator = asynchRevalidator;
    }

    public CachingExec(ClientExecChain backend, ResourceFactory resourceFactory, HttpCacheStorage storage, CacheConfig config) {
        this(backend, new BasicHttpCache(resourceFactory, storage, config), config);
    }

    public CachingExec(ClientExecChain backend) {
        this(backend, new BasicHttpCache(), CacheConfig.DEFAULT);
    }

    CachingExec(ClientExecChain backend, HttpCache responseCache, CacheValidityPolicy validityPolicy, ResponseCachingPolicy responseCachingPolicy, CachedHttpResponseGenerator responseGenerator, CacheableRequestPolicy cacheableRequestPolicy, CachedResponseSuitabilityChecker suitabilityChecker, ConditionalRequestBuilder conditionalRequestBuilder, ResponseProtocolCompliance responseCompliance, RequestProtocolCompliance requestCompliance, CacheConfig config, AsynchronousValidator asynchRevalidator) {
        this.cacheHits = new AtomicLong();
        this.cacheMisses = new AtomicLong();
        this.cacheUpdates = new AtomicLong();
        this.viaHeaders = new HashMap(4);
        this.log = new HttpClientAndroidLog(getClass());
        if (config == null) {
            config = CacheConfig.DEFAULT;
        }
        this.cacheConfig = config;
        this.backend = backend;
        this.responseCache = responseCache;
        this.validityPolicy = validityPolicy;
        this.responseCachingPolicy = responseCachingPolicy;
        this.responseGenerator = responseGenerator;
        this.cacheableRequestPolicy = cacheableRequestPolicy;
        this.suitabilityChecker = suitabilityChecker;
        this.conditionalRequestBuilder = conditionalRequestBuilder;
        this.responseCompliance = responseCompliance;
        this.requestCompliance = requestCompliance;
        this.asynchRevalidator = asynchRevalidator;
    }

    public long getCacheHits() {
        return this.cacheHits.get();
    }

    public long getCacheMisses() {
        return this.cacheMisses.get();
    }

    public long getCacheUpdates() {
        return this.cacheUpdates.get();
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request) throws IOException, HttpException {
        return execute(route, request, HttpClientContext.create(), null);
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context) throws IOException, HttpException {
        return execute(route, request, context, null);
    }

    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        HttpHost target = context.getTargetHost();
        String via = generateViaHeader(request.getOriginal());
        setResponseStatus(context, CacheResponseStatus.CACHE_MISS);
        if (clientRequestsOurOptions(request)) {
            setResponseStatus(context, CacheResponseStatus.CACHE_MODULE_RESPONSE);
            return Proxies.enhanceResponse(new OptionsHttp11Response());
        }
        HttpResponse fatalErrorResponse = getFatallyNoncompliantResponse(request, context);
        if (fatalErrorResponse != null) {
            return Proxies.enhanceResponse(fatalErrorResponse);
        }
        this.requestCompliance.makeRequestCompliant(request);
        request.addHeader("Via", via);
        flushEntriesInvalidatedByRequest(context.getTargetHost(), request);
        if (this.cacheableRequestPolicy.isServableFromCache(request)) {
            HttpCacheEntry entry = satisfyFromCache(target, request);
            if (entry != null) {
                return handleCacheHit(route, request, context, execAware, entry);
            }
            this.log.debug("Cache miss");
            return handleCacheMiss(route, request, context, execAware);
        }
        this.log.debug("Request is not servable from cache");
        return callBackend(route, request, context, execAware);
    }

    private CloseableHttpResponse handleCacheHit(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry entry) throws IOException, HttpException {
        CloseableHttpResponse out;
        HttpHost target = context.getTargetHost();
        recordCacheHit(target, request);
        Date now = getCurrentDate();
        if (this.suitabilityChecker.canCachedResponseBeUsed(target, request, entry, now)) {
            this.log.debug("Cache hit");
            out = generateCachedResponse(request, context, entry, now);
        } else if (!mayCallBackend(request)) {
            this.log.debug("Cache entry not suitable but only-if-cached requested");
            out = generateGatewayTimeout(context);
        } else if (entry.getStatusCode() != HttpStatus.SC_NOT_MODIFIED || this.suitabilityChecker.isConditional(request)) {
            this.log.debug("Revalidating cache entry");
            return revalidateCacheEntry(route, request, context, execAware, entry, now);
        } else {
            this.log.debug("Cache entry not usable; calling backend");
            return callBackend(route, request, context, execAware);
        }
        context.setAttribute("http.route", route);
        context.setAttribute("http.target_host", target);
        context.setAttribute("http.request", request);
        context.setAttribute("http.response", out);
        context.setAttribute("http.request_sent", Boolean.TRUE);
        return out;
    }

    private CloseableHttpResponse revalidateCacheEntry(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry entry, Date now) throws HttpException {
        try {
            if (this.asynchRevalidator == null || staleResponseNotAllowed(request, entry, now) || !this.validityPolicy.mayReturnStaleWhileRevalidating(entry, now)) {
                return revalidateCacheEntry(route, request, context, execAware, entry);
            }
            this.log.trace("Serving stale with asynchronous revalidation");
            CloseableHttpResponse resp = generateCachedResponse(request, context, entry, now);
            this.asynchRevalidator.revalidateCacheEntry(this, route, request, context, execAware, entry);
            return resp;
        } catch (IOException e) {
            return handleRevalidationFailure(request, context, entry, now);
        }
    }

    private CloseableHttpResponse handleCacheMiss(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        HttpHost target = context.getTargetHost();
        recordCacheMiss(target, request);
        if (!mayCallBackend(request)) {
            return Proxies.enhanceResponse(new BasicHttpResponse(HttpVersion.HTTP_1_1, (int) HttpStatus.SC_GATEWAY_TIMEOUT, "Gateway Timeout"));
        }
        Map<String, Variant> variants = getExistingCacheVariants(target, request);
        if (variants == null || variants.size() <= 0) {
            return callBackend(route, request, context, execAware);
        }
        return negotiateResponseFromVariants(route, request, context, execAware, variants);
    }

    private HttpCacheEntry satisfyFromCache(HttpHost target, HttpRequestWrapper request) {
        HttpCacheEntry entry = null;
        try {
            entry = this.responseCache.getCacheEntry(target, request);
        } catch (IOException ioe) {
            this.log.warn("Unable to retrieve entries from cache", ioe);
        }
        return entry;
    }

    private HttpResponse getFatallyNoncompliantResponse(HttpRequestWrapper request, HttpContext context) {
        HttpResponse fatalErrorResponse = null;
        for (RequestProtocolError error : this.requestCompliance.requestIsFatallyNonCompliant(request)) {
            setResponseStatus(context, CacheResponseStatus.CACHE_MODULE_RESPONSE);
            fatalErrorResponse = this.requestCompliance.getErrorForRequest(error);
        }
        return fatalErrorResponse;
    }

    private Map<String, Variant> getExistingCacheVariants(HttpHost target, HttpRequestWrapper request) {
        Map<String, Variant> variants = null;
        try {
            variants = this.responseCache.getVariantCacheEntriesWithEtags(target, request);
        } catch (IOException ioe) {
            this.log.warn("Unable to retrieve variant entries from cache", ioe);
        }
        return variants;
    }

    private void recordCacheMiss(HttpHost target, HttpRequestWrapper request) {
        this.cacheMisses.getAndIncrement();
        if (this.log.isTraceEnabled()) {
            this.log.trace("Cache miss [host: " + target + "; uri: " + request.getRequestLine().getUri() + "]");
        }
    }

    private void recordCacheHit(HttpHost target, HttpRequestWrapper request) {
        this.cacheHits.getAndIncrement();
        if (this.log.isTraceEnabled()) {
            this.log.trace("Cache hit [host: " + target + "; uri: " + request.getRequestLine().getUri() + "]");
        }
    }

    private void recordCacheUpdate(HttpContext context) {
        this.cacheUpdates.getAndIncrement();
        setResponseStatus(context, CacheResponseStatus.VALIDATED);
    }

    private void flushEntriesInvalidatedByRequest(HttpHost target, HttpRequestWrapper request) {
        try {
            this.responseCache.flushInvalidatedCacheEntriesFor(target, request);
        } catch (IOException ioe) {
            this.log.warn("Unable to flush invalidated entries from cache", ioe);
        }
    }

    private CloseableHttpResponse generateCachedResponse(HttpRequestWrapper request, HttpContext context, HttpCacheEntry entry, Date now) {
        CloseableHttpResponse cachedResponse;
        if (request.containsHeader("If-None-Match") || request.containsHeader("If-Modified-Since")) {
            cachedResponse = this.responseGenerator.generateNotModifiedResponse(entry);
        } else {
            cachedResponse = this.responseGenerator.generateResponse(entry);
        }
        setResponseStatus(context, CacheResponseStatus.CACHE_HIT);
        if (this.validityPolicy.getStalenessSecs(entry, now) > 0) {
            cachedResponse.addHeader("Warning", "110 localhost \"Response is stale\"");
        }
        return cachedResponse;
    }

    private CloseableHttpResponse handleRevalidationFailure(HttpRequestWrapper request, HttpContext context, HttpCacheEntry entry, Date now) {
        if (staleResponseNotAllowed(request, entry, now)) {
            return generateGatewayTimeout(context);
        }
        return unvalidatedCacheHit(context, entry);
    }

    private CloseableHttpResponse generateGatewayTimeout(HttpContext context) {
        setResponseStatus(context, CacheResponseStatus.CACHE_MODULE_RESPONSE);
        return Proxies.enhanceResponse(new BasicHttpResponse(HttpVersion.HTTP_1_1, (int) HttpStatus.SC_GATEWAY_TIMEOUT, "Gateway Timeout"));
    }

    private CloseableHttpResponse unvalidatedCacheHit(HttpContext context, HttpCacheEntry entry) {
        CloseableHttpResponse cachedResponse = this.responseGenerator.generateResponse(entry);
        setResponseStatus(context, CacheResponseStatus.CACHE_HIT);
        cachedResponse.addHeader("Warning", "111 localhost \"Revalidation failed\"");
        return cachedResponse;
    }

    private boolean staleResponseNotAllowed(HttpRequestWrapper request, HttpCacheEntry entry, Date now) {
        return this.validityPolicy.mustRevalidate(entry) || ((this.cacheConfig.isSharedCache() && this.validityPolicy.proxyRevalidate(entry)) || explicitFreshnessRequest(request, entry, now));
    }

    private boolean mayCallBackend(HttpRequestWrapper request) {
        for (Header h : request.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if ("only-if-cached".equals(elt.getName())) {
                    this.log.trace("Request marked only-if-cached");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean explicitFreshnessRequest(HttpRequestWrapper request, HttpCacheEntry entry, Date now) {
        for (Header h : request.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_MAX_STALE.equals(elt.getName())) {
                    try {
                        if (this.validityPolicy.getCurrentAgeSecs(entry, now) - this.validityPolicy.getFreshnessLifetimeSecs(entry) > ((long) Integer.parseInt(elt.getValue()))) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        return true;
                    }
                } else if (HeaderConstants.CACHE_CONTROL_MIN_FRESH.equals(elt.getName()) || "max-age".equals(elt.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String generateViaHeader(HttpMessage msg) {
        ProtocolVersion pv = msg.getProtocolVersion();
        String existingEntry = (String) this.viaHeaders.get(pv);
        if (existingEntry != null) {
            return existingEntry;
        }
        String value;
        VersionInfo vi = VersionInfo.loadVersionInfo("cz.msebera.android.httpclient.client", getClass().getClassLoader());
        String release = vi != null ? vi.getRelease() : VersionInfo.UNAVAILABLE;
        if ("http".equalsIgnoreCase(pv.getProtocol())) {
            value = String.format("%d.%d localhost (Apache-HttpClient/%s (cache))", new Object[]{Integer.valueOf(pv.getMajor()), Integer.valueOf(pv.getMinor()), release});
        } else {
            value = String.format("%s/%d.%d localhost (Apache-HttpClient/%s (cache))", new Object[]{pv.getProtocol(), Integer.valueOf(pv.getMajor()), Integer.valueOf(pv.getMinor()), release});
        }
        this.viaHeaders.put(pv, value);
        return value;
    }

    private void setResponseStatus(HttpContext context, CacheResponseStatus value) {
        if (context != null) {
            context.setAttribute(HttpCacheContext.CACHE_RESPONSE_STATUS, value);
        }
    }

    public boolean supportsRangeAndContentRangeHeaders() {
        return false;
    }

    Date getCurrentDate() {
        return new Date();
    }

    boolean clientRequestsOurOptions(HttpRequest request) {
        RequestLine line = request.getRequestLine();
        if ("OPTIONS".equals(line.getMethod()) && Marker.ANY_MARKER.equals(line.getUri()) && SchemaSymbols.ATTVAL_FALSE_0.equals(request.getFirstHeader("Max-Forwards").getValue())) {
            return true;
        }
        return false;
    }

    CloseableHttpResponse callBackend(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware) throws IOException, HttpException {
        Date requestDate = getCurrentDate();
        this.log.trace("Calling the backend");
        CloseableHttpResponse backendResponse = this.backend.execute(route, request, context, execAware);
        try {
            backendResponse.addHeader("Via", generateViaHeader(backendResponse));
            return handleBackendResponse(route, request, context, execAware, requestDate, getCurrentDate(), backendResponse);
        } catch (IOException ex) {
            backendResponse.close();
            throw ex;
        } catch (RuntimeException ex2) {
            backendResponse.close();
            throw ex2;
        }
    }

    private boolean revalidationResponseIsTooOld(HttpResponse backendResponse, HttpCacheEntry cacheEntry) {
        Header entryDateHeader = cacheEntry.getFirstHeader("Date");
        Header responseDateHeader = backendResponse.getFirstHeader("Date");
        if (entryDateHeader == null || responseDateHeader == null) {
            return false;
        }
        Date entryDate = DateUtils.parseDate(entryDateHeader.getValue());
        Date respDate = DateUtils.parseDate(responseDateHeader.getValue());
        if (entryDate == null || respDate == null || !respDate.before(entryDate)) {
            return false;
        }
        return true;
    }

    CloseableHttpResponse negotiateResponseFromVariants(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, Map<String, Variant> variants) throws IOException, HttpException {
        HttpRequestWrapper conditionalRequest = this.conditionalRequestBuilder.buildConditionalRequestFromVariants(request, variants);
        Date requestDate = getCurrentDate();
        CloseableHttpResponse backendResponse = this.backend.execute(route, conditionalRequest, context, execAware);
        try {
            Date responseDate = getCurrentDate();
            backendResponse.addHeader("Via", generateViaHeader(backendResponse));
            if (backendResponse.getStatusLine().getStatusCode() != HttpStatus.SC_NOT_MODIFIED) {
                return handleBackendResponse(route, request, context, execAware, requestDate, responseDate, backendResponse);
            }
            Header resultEtagHeader = backendResponse.getFirstHeader("ETag");
            if (resultEtagHeader == null) {
                this.log.warn("304 response did not contain ETag");
                IOUtils.consume(backendResponse.getEntity());
                backendResponse.close();
                return callBackend(route, request, context, execAware);
            }
            Variant matchingVariant = (Variant) variants.get(resultEtagHeader.getValue());
            if (matchingVariant == null) {
                this.log.debug("304 response did not contain ETag matching one sent in If-None-Match");
                IOUtils.consume(backendResponse.getEntity());
                backendResponse.close();
                return callBackend(route, request, context, execAware);
            }
            HttpCacheEntry matchedEntry = matchingVariant.getEntry();
            if (revalidationResponseIsTooOld(backendResponse, matchedEntry)) {
                IOUtils.consume(backendResponse.getEntity());
                backendResponse.close();
                return retryRequestUnconditionally(route, request, context, execAware, matchedEntry);
            }
            recordCacheUpdate(context);
            HttpCacheEntry responseEntry = getUpdatedVariantEntry(context.getTargetHost(), conditionalRequest, requestDate, responseDate, backendResponse, matchingVariant, matchedEntry);
            backendResponse.close();
            CloseableHttpResponse resp = this.responseGenerator.generateResponse(responseEntry);
            tryToUpdateVariantMap(context.getTargetHost(), request, matchingVariant);
            if (shouldSendNotModifiedResponse(request, responseEntry)) {
                return this.responseGenerator.generateNotModifiedResponse(responseEntry);
            }
            return resp;
        } catch (IOException ex) {
            backendResponse.close();
            throw ex;
        } catch (RuntimeException ex2) {
            backendResponse.close();
            throw ex2;
        }
    }

    private CloseableHttpResponse retryRequestUnconditionally(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry matchedEntry) throws IOException, HttpException {
        return callBackend(route, this.conditionalRequestBuilder.buildUnconditionalRequest(request, matchedEntry), context, execAware);
    }

    private HttpCacheEntry getUpdatedVariantEntry(HttpHost target, HttpRequestWrapper conditionalRequest, Date requestDate, Date responseDate, CloseableHttpResponse backendResponse, Variant matchingVariant, HttpCacheEntry matchedEntry) throws IOException {
        HttpCacheEntry responseEntry = matchedEntry;
        try {
            responseEntry = this.responseCache.updateVariantCacheEntry(target, conditionalRequest, matchedEntry, backendResponse, requestDate, responseDate, matchingVariant.getCacheKey());
        } catch (IOException ioe) {
            this.log.warn("Could not update cache entry", ioe);
        } finally {
            backendResponse.close();
        }
        return responseEntry;
    }

    private void tryToUpdateVariantMap(HttpHost target, HttpRequestWrapper request, Variant matchingVariant) {
        try {
            this.responseCache.reuseVariantEntryFor(target, request, matchingVariant);
        } catch (IOException ioe) {
            this.log.warn("Could not update cache entry to reuse variant", ioe);
        }
    }

    private boolean shouldSendNotModifiedResponse(HttpRequestWrapper request, HttpCacheEntry responseEntry) {
        return this.suitabilityChecker.isConditional(request) && this.suitabilityChecker.allConditionalsMatch(request, responseEntry, new Date());
    }

    CloseableHttpResponse revalidateCacheEntry(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, HttpCacheEntry cacheEntry) throws IOException, HttpException {
        HttpRequestWrapper conditionalRequest = this.conditionalRequestBuilder.buildConditionalRequest(request, cacheEntry);
        URI uri = conditionalRequest.getURI();
        if (uri != null) {
            try {
                conditionalRequest.setURI(InternalURIUtils.rewriteURIForRoute(uri, route));
            } catch (Throwable ex) {
                throw new ProtocolException("Invalid URI: " + uri, ex);
            }
        }
        Date requestDate = getCurrentDate();
        CloseableHttpResponse backendResponse = this.backend.execute(route, conditionalRequest, context, execAware);
        Date responseDate = getCurrentDate();
        if (revalidationResponseIsTooOld(backendResponse, cacheEntry)) {
            backendResponse.close();
            HttpRequestWrapper unconditional = this.conditionalRequestBuilder.buildUnconditionalRequest(request, cacheEntry);
            requestDate = getCurrentDate();
            backendResponse = this.backend.execute(route, unconditional, context, execAware);
            responseDate = getCurrentDate();
        }
        backendResponse.addHeader("Via", generateViaHeader(backendResponse));
        int statusCode = backendResponse.getStatusLine().getStatusCode();
        if (statusCode == 304 || statusCode == 200) {
            recordCacheUpdate(context);
        }
        if (statusCode == 304) {
            HttpCacheEntry updatedEntry = this.responseCache.updateCacheEntry(context.getTargetHost(), request, cacheEntry, backendResponse, requestDate, responseDate);
            if (this.suitabilityChecker.isConditional(request)) {
                if (this.suitabilityChecker.allConditionalsMatch(request, updatedEntry, new Date())) {
                    return this.responseGenerator.generateNotModifiedResponse(updatedEntry);
                }
            }
            return this.responseGenerator.generateResponse(updatedEntry);
        }
        if (staleIfErrorAppliesTo(statusCode)) {
            if (!staleResponseNotAllowed(request, cacheEntry, getCurrentDate()) && this.validityPolicy.mayReturnStaleIfError(request, cacheEntry, responseDate)) {
                try {
                    CloseableHttpResponse cachedResponse = this.responseGenerator.generateResponse(cacheEntry);
                    cachedResponse.addHeader("Warning", "110 localhost \"Response is stale\"");
                    return cachedResponse;
                } finally {
                    backendResponse.close();
                }
            }
        }
        return handleBackendResponse(route, conditionalRequest, context, execAware, requestDate, responseDate, backendResponse);
    }

    private boolean staleIfErrorAppliesTo(int statusCode) {
        return statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR || statusCode == HttpStatus.SC_BAD_GATEWAY || statusCode == HttpStatus.SC_SERVICE_UNAVAILABLE || statusCode == HttpStatus.SC_GATEWAY_TIMEOUT;
    }

    CloseableHttpResponse handleBackendResponse(HttpRoute route, HttpRequestWrapper request, HttpClientContext context, HttpExecutionAware execAware, Date requestDate, Date responseDate, CloseableHttpResponse backendResponse) throws IOException {
        this.log.trace("Handling Backend response");
        this.responseCompliance.ensureProtocolCompliance(request, backendResponse);
        HttpHost target = context.getTargetHost();
        boolean cacheable = this.responseCachingPolicy.isResponseCacheable((HttpRequest) request, (HttpResponse) backendResponse);
        this.responseCache.flushInvalidatedCacheEntriesFor(target, request, backendResponse);
        if (cacheable && !alreadyHaveNewerCacheEntry(target, request, backendResponse)) {
            storeRequestIfModifiedSinceFor304Response(request, backendResponse);
            return this.responseCache.cacheAndReturnResponse(target, (HttpRequest) request, backendResponse, requestDate, responseDate);
        } else if (cacheable) {
            return backendResponse;
        } else {
            try {
                this.responseCache.flushCacheEntriesFor(target, request);
                return backendResponse;
            } catch (IOException ioe) {
                this.log.warn("Unable to flush invalid cache entries", ioe);
                return backendResponse;
            }
        }
    }

    private void storeRequestIfModifiedSinceFor304Response(HttpRequest request, HttpResponse backendResponse) {
        if (backendResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_MODIFIED) {
            Header h = request.getFirstHeader("If-Modified-Since");
            if (h != null) {
                backendResponse.addHeader("Last-Modified", h.getValue());
            }
        }
    }

    private boolean alreadyHaveNewerCacheEntry(HttpHost target, HttpRequestWrapper request, HttpResponse backendResponse) {
        HttpCacheEntry existing = null;
        try {
            existing = this.responseCache.getCacheEntry(target, request);
        } catch (IOException e) {
        }
        if (existing == null) {
            return false;
        }
        Header entryDateHeader = existing.getFirstHeader("Date");
        if (entryDateHeader == null) {
            return false;
        }
        Header responseDateHeader = backendResponse.getFirstHeader("Date");
        if (responseDateHeader == null) {
            return false;
        }
        Date entryDate = DateUtils.parseDate(entryDateHeader.getValue());
        Date responseDate = DateUtils.parseDate(responseDateHeader.getValue());
        if (entryDate == null || responseDate == null) {
            return false;
        }
        return responseDate.before(entryDate);
    }
}
