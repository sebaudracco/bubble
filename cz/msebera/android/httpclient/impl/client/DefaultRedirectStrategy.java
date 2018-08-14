package cz.msebera.android.httpclient.impl.client;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.CircularRedirectException;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpHead;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

@Immutable
public class DefaultRedirectStrategy implements RedirectStrategy {
    public static final DefaultRedirectStrategy INSTANCE = new DefaultRedirectStrategy();
    @Deprecated
    public static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
    private static final String[] REDIRECT_METHODS = new String[]{"GET", "HEAD"};
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        Args.notNull(request, "HTTP request");
        Args.notNull(response, "HTTP response");
        int statusCode = response.getStatusLine().getStatusCode();
        String method = request.getRequestLine().getMethod();
        Header locationHeader = response.getFirstHeader("location");
        switch (statusCode) {
            case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
            case 307:
                return isRedirectable(method);
            case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
                if (!isRedirectable(method) || locationHeader == null) {
                    return false;
                }
                return true;
            case HttpStatus.SC_SEE_OTHER /*303*/:
                return true;
            default:
                return false;
        }
    }

    public URI getLocationURI(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        Args.notNull(request, "HTTP request");
        Args.notNull(response, "HTTP response");
        Args.notNull(context, "HTTP context");
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        Header locationHeader = response.getFirstHeader("location");
        if (locationHeader == null) {
            throw new ProtocolException("Received redirect response " + response.getStatusLine() + " but no location header");
        }
        String location = locationHeader.getValue();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Redirect requested to location '" + location + "'");
        }
        RequestConfig config = clientContext.getRequestConfig();
        URI uri = createLocationURI(location);
        try {
            if (!uri.isAbsolute()) {
                if (config.isRelativeRedirectsAllowed()) {
                    HttpHost target = clientContext.getTargetHost();
                    Asserts.notNull(target, "Target host");
                    uri = URIUtils.resolve(URIUtils.rewriteURI(new URI(request.getRequestLine().getUri()), target, false), uri);
                } else {
                    throw new ProtocolException("Relative redirect location '" + uri + "' not allowed");
                }
            }
            RedirectLocations redirectLocations = (RedirectLocations) clientContext.getAttribute("http.protocol.redirect-locations");
            if (redirectLocations == null) {
                redirectLocations = new RedirectLocations();
                context.setAttribute("http.protocol.redirect-locations", redirectLocations);
            }
            if (config.isCircularRedirectsAllowed() || !redirectLocations.contains(uri)) {
                redirectLocations.add(uri);
                return uri;
            }
            throw new CircularRedirectException("Circular redirect to '" + uri + "'");
        } catch (URISyntaxException ex) {
            throw new ProtocolException(ex.getMessage(), ex);
        }
    }

    protected URI createLocationURI(String location) throws ProtocolException {
        try {
            URIBuilder b = new URIBuilder(new URI(location).normalize());
            String host = b.getHost();
            if (host != null) {
                b.setHost(host.toLowerCase(Locale.ENGLISH));
            }
            if (TextUtils.isEmpty(b.getPath())) {
                b.setPath(BridgeUtil.SPLIT_MARK);
            }
            return b.build();
        } catch (URISyntaxException ex) {
            throw new ProtocolException("Invalid redirect URI: " + location, ex);
        }
    }

    protected boolean isRedirectable(String method) {
        for (String m : REDIRECT_METHODS) {
            if (m.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

    public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        URI uri = getLocationURI(request, response, context);
        String method = request.getRequestLine().getMethod();
        if (method.equalsIgnoreCase("HEAD")) {
            return new HttpHead(uri);
        }
        if (method.equalsIgnoreCase("GET")) {
            return new HttpGet(uri);
        }
        if (response.getStatusLine().getStatusCode() == 307) {
            return RequestBuilder.copy(request).setUri(uri).build();
        }
        return new HttpGet(uri);
    }
}
