package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import java.util.Date;

@Immutable
class CachedHttpResponseGenerator {
    private final CacheValidityPolicy validityStrategy;

    CachedHttpResponseGenerator(CacheValidityPolicy validityStrategy) {
        this.validityStrategy = validityStrategy;
    }

    CachedHttpResponseGenerator() {
        this(new CacheValidityPolicy());
    }

    CloseableHttpResponse generateResponse(HttpCacheEntry entry) {
        Date now = new Date();
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, entry.getStatusCode(), entry.getReasonPhrase());
        response.setHeaders(entry.getAllHeaders());
        if (entry.getResource() != null) {
            HttpEntity entity = new CacheEntity(entry);
            addMissingContentLengthHeader(response, entity);
            response.setEntity(entity);
        }
        long age = this.validityStrategy.getCurrentAgeSecs(entry, now);
        if (age > 0) {
            if (age >= 2147483647L) {
                response.setHeader("Age", "2147483648");
            } else {
                response.setHeader("Age", "" + ((int) age));
            }
        }
        return Proxies.enhanceResponse(response);
    }

    CloseableHttpResponse generateNotModifiedResponse(HttpCacheEntry entry) {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, (int) HttpStatus.SC_NOT_MODIFIED, "Not Modified");
        Header dateHeader = entry.getFirstHeader("Date");
        if (dateHeader == null) {
            dateHeader = new BasicHeader("Date", DateUtils.formatDate(new Date()));
        }
        response.addHeader(dateHeader);
        Header etagHeader = entry.getFirstHeader("ETag");
        if (etagHeader != null) {
            response.addHeader(etagHeader);
        }
        Header contentLocationHeader = entry.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
        if (contentLocationHeader != null) {
            response.addHeader(contentLocationHeader);
        }
        Header expiresHeader = entry.getFirstHeader("Expires");
        if (expiresHeader != null) {
            response.addHeader(expiresHeader);
        }
        Header cacheControlHeader = entry.getFirstHeader("Cache-Control");
        if (cacheControlHeader != null) {
            response.addHeader(cacheControlHeader);
        }
        Header varyHeader = entry.getFirstHeader("Vary");
        if (varyHeader != null) {
            response.addHeader(varyHeader);
        }
        return Proxies.enhanceResponse(response);
    }

    private void addMissingContentLengthHeader(HttpResponse response, HttpEntity entity) {
        if (!transferEncodingIsPresent(response) && response.getFirstHeader("Content-Length") == null) {
            response.setHeader(new BasicHeader("Content-Length", Long.toString(entity.getContentLength())));
        }
    }

    private boolean transferEncodingIsPresent(HttpResponse response) {
        return response.getFirstHeader("Transfer-Encoding") != null;
    }
}
