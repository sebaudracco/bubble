package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import java.util.Map;

@Immutable
class ConditionalRequestBuilder {
    ConditionalRequestBuilder() {
    }

    public HttpRequestWrapper buildConditionalRequest(HttpRequestWrapper request, HttpCacheEntry cacheEntry) throws ProtocolException {
        HttpRequestWrapper newRequest = HttpRequestWrapper.wrap(request.getOriginal());
        newRequest.setHeaders(request.getAllHeaders());
        Header eTag = cacheEntry.getFirstHeader("ETag");
        if (eTag != null) {
            newRequest.setHeader("If-None-Match", eTag.getValue());
        }
        Header lastModified = cacheEntry.getFirstHeader("Last-Modified");
        if (lastModified != null) {
            newRequest.setHeader("If-Modified-Since", lastModified.getValue());
        }
        boolean mustRevalidate = false;
        for (Header h : cacheEntry.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE.equalsIgnoreCase(elt.getName()) || HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE.equalsIgnoreCase(elt.getName())) {
                    mustRevalidate = true;
                    break;
                }
            }
        }
        if (mustRevalidate) {
            newRequest.addHeader("Cache-Control", "max-age=0");
        }
        return newRequest;
    }

    public HttpRequestWrapper buildConditionalRequestFromVariants(HttpRequestWrapper request, Map<String, Variant> variants) {
        HttpRequestWrapper newRequest = HttpRequestWrapper.wrap(request.getOriginal());
        newRequest.setHeaders(request.getAllHeaders());
        StringBuilder etags = new StringBuilder();
        boolean first = true;
        for (String etag : variants.keySet()) {
            if (!first) {
                etags.append(",");
            }
            first = false;
            etags.append(etag);
        }
        newRequest.setHeader("If-None-Match", etags.toString());
        return newRequest;
    }

    public HttpRequestWrapper buildUnconditionalRequest(HttpRequestWrapper request, HttpCacheEntry entry) {
        HttpRequestWrapper newRequest = HttpRequestWrapper.wrap(request.getOriginal());
        newRequest.setHeaders(request.getAllHeaders());
        newRequest.addHeader("Cache-Control", HeaderConstants.CACHE_CONTROL_NO_CACHE);
        newRequest.addHeader("Pragma", HeaderConstants.CACHE_CONTROL_NO_CACHE);
        newRequest.removeHeaders("If-Range");
        newRequest.removeHeaders("If-Match");
        newRequest.removeHeaders("If-None-Match");
        newRequest.removeHeaders("If-Unmodified-Since");
        newRequest.removeHeaders("If-Modified-Since");
        return newRequest;
    }
}
