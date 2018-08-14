package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

@Immutable
class CacheableRequestPolicy {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    CacheableRequestPolicy() {
    }

    public boolean isServableFromCache(HttpRequest request) {
        String method = request.getRequestLine().getMethod();
        if (HttpVersion.HTTP_1_1.compareToVersion(request.getRequestLine().getProtocolVersion()) != 0) {
            this.log.trace("non-HTTP/1.1 request was not serveable from cache");
            return false;
        } else if (!method.equals("GET")) {
            this.log.trace("non-GET request was not serveable from cache");
            return false;
        } else if (request.getHeaders("Pragma").length > 0) {
            this.log.trace("request with Pragma header was not serveable from cache");
            return false;
        } else {
            for (Header cacheControl : request.getHeaders("Cache-Control")) {
                HeaderElement[] elements = cacheControl.getElements();
                int length = elements.length;
                int i = 0;
                while (i < length) {
                    HeaderElement cacheControlElement = elements[i];
                    if (HeaderConstants.CACHE_CONTROL_NO_STORE.equalsIgnoreCase(cacheControlElement.getName())) {
                        this.log.trace("Request with no-store was not serveable from cache");
                        return false;
                    } else if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equalsIgnoreCase(cacheControlElement.getName())) {
                        this.log.trace("Request with no-cache was not serveable from cache");
                        return false;
                    } else {
                        i++;
                    }
                }
            }
            this.log.trace("Request was serveable from cache");
            return true;
        }
    }
}
