package cz.msebera.android.httpclient.impl.client.cache;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Marker;

@Immutable
class ResponseCachingPolicy {
    private static final String[] AUTH_CACHEABLE_PARAMS = new String[]{"s-maxage", HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE, HeaderConstants.PUBLIC};
    private static final Set<Integer> cacheableStatuses = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(200), Integer.valueOf(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION), Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES), Integer.valueOf(HttpStatus.SC_MOVED_PERMANENTLY), Integer.valueOf(410)}));
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final long maxObjectSizeBytes;
    private final boolean neverCache1_0ResponsesWithQueryString;
    private final boolean sharedCache;
    private final Set<Integer> uncacheableStatuses;

    public ResponseCachingPolicy(long maxObjectSizeBytes, boolean sharedCache, boolean neverCache1_0ResponsesWithQueryString, boolean allow303Caching) {
        this.maxObjectSizeBytes = maxObjectSizeBytes;
        this.sharedCache = sharedCache;
        this.neverCache1_0ResponsesWithQueryString = neverCache1_0ResponsesWithQueryString;
        if (allow303Caching) {
            this.uncacheableStatuses = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(HttpStatus.SC_PARTIAL_CONTENT)}));
            return;
        }
        this.uncacheableStatuses = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(HttpStatus.SC_PARTIAL_CONTENT), Integer.valueOf(HttpStatus.SC_SEE_OTHER)}));
    }

    public boolean isResponseCacheable(String httpMethod, HttpResponse response) {
        boolean cacheable = false;
        if ("GET".equals(httpMethod)) {
            int status = response.getStatusLine().getStatusCode();
            if (cacheableStatuses.contains(Integer.valueOf(status))) {
                cacheable = true;
            } else if (this.uncacheableStatuses.contains(Integer.valueOf(status))) {
                return false;
            } else {
                if (unknownStatusCode(status)) {
                    return false;
                }
            }
            Header contentLength = response.getFirstHeader("Content-Length");
            if (contentLength != null && ((long) Integer.parseInt(contentLength.getValue())) > this.maxObjectSizeBytes) {
                return false;
            }
            if (response.getHeaders("Age").length > 1) {
                return false;
            }
            if (response.getHeaders("Expires").length > 1) {
                return false;
            }
            Header[] dateHeaders = response.getHeaders("Date");
            if (dateHeaders.length != 1) {
                return false;
            }
            if (DateUtils.parseDate(dateHeaders[0].getValue()) == null) {
                return false;
            }
            for (Header varyHdr : response.getHeaders("Vary")) {
                for (HeaderElement elem : varyHdr.getElements()) {
                    if (Marker.ANY_MARKER.equals(elem.getName())) {
                        return false;
                    }
                }
            }
            if (isExplicitlyNonCacheable(response)) {
                return false;
            }
            return cacheable || isExplicitlyCacheable(response);
        } else {
            this.log.debug("Response was not cacheable.");
            return false;
        }
    }

    private boolean unknownStatusCode(int status) {
        if (status >= 100 && status <= 101) {
            return false;
        }
        if (status >= 200 && status <= HttpStatus.SC_PARTIAL_CONTENT) {
            return false;
        }
        if (status >= HttpStatus.SC_MULTIPLE_CHOICES && status <= 307) {
            return false;
        }
        if (status >= HttpStatus.SC_BAD_REQUEST && status <= HttpStatus.SC_EXPECTATION_FAILED) {
            return false;
        }
        if (status < HttpStatus.SC_INTERNAL_SERVER_ERROR || status > HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED) {
            return true;
        }
        return false;
    }

    protected boolean isExplicitlyNonCacheable(HttpResponse response) {
        for (Header header : response.getHeaders("Cache-Control")) {
            for (HeaderElement elem : header.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_NO_STORE.equals(elem.getName()) || HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(elem.getName()) || (this.sharedCache && HeaderConstants.PRIVATE.equals(elem.getName()))) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean hasCacheControlParameterFrom(HttpMessage msg, String[] params) {
        for (Header header : msg.getHeaders("Cache-Control")) {
            for (HeaderElement elem : header.getElements()) {
                for (String param : params) {
                    if (param.equalsIgnoreCase(elem.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean isExplicitlyCacheable(HttpResponse response) {
        if (response.getFirstHeader("Expires") != null) {
            return true;
        }
        return hasCacheControlParameterFrom(response, new String[]{"max-age", "s-maxage", HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE, HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE, HeaderConstants.PUBLIC});
    }

    public boolean isResponseCacheable(HttpRequest request, HttpResponse response) {
        if (requestProtocolGreaterThanAccepted(request)) {
            this.log.debug("Response was not cacheable.");
            return false;
        }
        if (hasCacheControlParameterFrom(request, new String[]{HeaderConstants.CACHE_CONTROL_NO_STORE})) {
            return false;
        }
        if (request.getRequestLine().getUri().contains("?")) {
            if (this.neverCache1_0ResponsesWithQueryString && from1_0Origin(response)) {
                this.log.debug("Response was not cacheable as it had a query string.");
                return false;
            } else if (!isExplicitlyCacheable(response)) {
                this.log.debug("Response was not cacheable as it is missing explicit caching headers.");
                return false;
            }
        }
        if (expiresHeaderLessOrEqualToDateHeaderAndNoCacheControl(response)) {
            return false;
        }
        if (this.sharedCache) {
            Header[] authNHeaders = request.getHeaders("Authorization");
            if (!(authNHeaders == null || authNHeaders.length <= 0 || hasCacheControlParameterFrom(response, AUTH_CACHEABLE_PARAMS))) {
                return false;
            }
        }
        return isResponseCacheable(request.getRequestLine().getMethod(), response);
    }

    private boolean expiresHeaderLessOrEqualToDateHeaderAndNoCacheControl(HttpResponse response) {
        if (response.getFirstHeader("Cache-Control") != null) {
            return false;
        }
        Header expiresHdr = response.getFirstHeader("Expires");
        Header dateHdr = response.getFirstHeader("Date");
        if (expiresHdr == null || dateHdr == null) {
            return false;
        }
        Date expires = DateUtils.parseDate(expiresHdr.getValue());
        Date date = DateUtils.parseDate(dateHdr.getValue());
        if (expires == null || date == null) {
            return false;
        }
        if (expires.equals(date) || expires.before(date)) {
            return true;
        }
        return false;
    }

    private boolean from1_0Origin(HttpResponse response) {
        Header via = response.getFirstHeader("Via");
        if (via != null) {
            HeaderElement[] elements = via.getElements();
            if (0 < elements.length) {
                String proto = elements[0].toString().split("\\s")[0];
                if (proto.contains(BridgeUtil.SPLIT_MARK)) {
                    return proto.equals("HTTP/1.0");
                }
                return proto.equals("1.0");
            }
        }
        return HttpVersion.HTTP_1_0.equals(response.getProtocolVersion());
    }

    private boolean requestProtocolGreaterThanAccepted(HttpRequest req) {
        return req.getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) > 0;
    }
}
