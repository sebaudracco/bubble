package cz.msebera.android.httpclient.impl.client.cache;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Marker;

@Immutable
class CacheKeyGenerator {
    private static final URI BASE_URI = URI.create("http://example.com/");

    CacheKeyGenerator() {
    }

    public String getURI(HttpHost host, HttpRequest req) {
        if (!isRelativeRequest(req)) {
            return canonicalizeUri(req.getRequestLine().getUri());
        }
        return canonicalizeUri(String.format("%s%s", new Object[]{host.toString(), req.getRequestLine().getUri()}));
    }

    public String canonicalizeUri(String uri) {
        try {
            String file;
            URL u = new URL(URIUtils.resolve(BASE_URI, uri).toASCIIString());
            String protocol = u.getProtocol();
            String hostname = u.getHost();
            int port = canonicalizePort(u.getPort(), protocol);
            String path = u.getPath();
            String query = u.getQuery();
            if (query != null) {
                file = path + "?" + query;
            } else {
                file = path;
            }
            uri = new URL(protocol, hostname, port, file).toString();
        } catch (IllegalArgumentException e) {
        } catch (MalformedURLException e2) {
        }
        return uri;
    }

    private int canonicalizePort(int port, String protocol) {
        if (port == -1 && "http".equalsIgnoreCase(protocol)) {
            return 80;
        }
        if (port == -1 && "https".equalsIgnoreCase(protocol)) {
            return 443;
        }
        return port;
    }

    private boolean isRelativeRequest(HttpRequest req) {
        String requestUri = req.getRequestLine().getUri();
        return Marker.ANY_MARKER.equals(requestUri) || requestUri.startsWith(BridgeUtil.SPLIT_MARK);
    }

    protected String getFullHeaderValue(Header[] headers) {
        if (headers == null) {
            return "";
        }
        StringBuilder buf = new StringBuilder("");
        boolean first = true;
        for (Header hdr : headers) {
            if (!first) {
                buf.append(", ");
            }
            buf.append(hdr.getValue().trim());
            first = false;
        }
        return buf.toString();
    }

    public String getVariantURI(HttpHost host, HttpRequest req, HttpCacheEntry entry) {
        if (entry.hasVariants()) {
            return getVariantKey(req, entry) + getURI(host, req);
        }
        return getURI(host, req);
    }

    public String getVariantKey(HttpRequest req, HttpCacheEntry entry) {
        List<String> variantHeaderNames = new ArrayList();
        for (Header varyHdr : entry.getHeaders("Vary")) {
            for (HeaderElement elt : varyHdr.getElements()) {
                variantHeaderNames.add(elt.getName());
            }
        }
        Collections.sort(variantHeaderNames);
        try {
            StringBuilder buf = new StringBuilder("{");
            boolean first = true;
            for (String headerName : variantHeaderNames) {
                if (!first) {
                    buf.append("&");
                }
                buf.append(URLEncoder.encode(headerName, Consts.UTF_8.name()));
                buf.append("=");
                buf.append(URLEncoder.encode(getFullHeaderValue(req.getHeaders(headerName)), Consts.UTF_8.name()));
                first = false;
            }
            buf.append("}");
            return buf.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("couldn't encode to UTF-8", uee);
        }
    }
}
