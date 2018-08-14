package cz.msebera.android.httpclient.client.protocol;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.conn.routing.RouteInfo;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.cookie.SetCookie2;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Immutable
public class RequestAddCookies implements HttpRequestInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        Args.notNull(context, "HTTP context");
        if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            CookieStore cookieStore = clientContext.getCookieStore();
            if (cookieStore == null) {
                this.log.debug("Cookie store not specified in HTTP context");
                return;
            }
            Lookup<CookieSpecProvider> registry = clientContext.getCookieSpecRegistry();
            if (registry == null) {
                this.log.debug("CookieSpec registry not specified in HTTP context");
                return;
            }
            HttpHost targetHost = clientContext.getTargetHost();
            if (targetHost == null) {
                this.log.debug("Target host not set in the context");
                return;
            }
            RouteInfo route = clientContext.getHttpRoute();
            if (route == null) {
                this.log.debug("Connection route not set in the context");
                return;
            }
            String policy = clientContext.getRequestConfig().getCookieSpec();
            if (policy == null) {
                policy = "best-match";
            }
            if (this.log.isDebugEnabled()) {
                this.log.debug("CookieSpec selected: " + policy);
            }
            URI requestURI = null;
            if (request instanceof HttpUriRequest) {
                requestURI = ((HttpUriRequest) request).getURI();
            } else {
                try {
                    requestURI = new URI(request.getRequestLine().getUri());
                } catch (URISyntaxException e) {
                }
            }
            String path = requestURI != null ? requestURI.getPath() : null;
            String hostName = targetHost.getHostName();
            int port = targetHost.getPort();
            if (port < 0) {
                port = route.getTargetHost().getPort();
            }
            if (port < 0) {
                port = 0;
            }
            if (TextUtils.isEmpty(path)) {
                path = BridgeUtil.SPLIT_MARK;
            }
            CookieOrigin cookieOrigin = new CookieOrigin(hostName, port, path, route.isSecure());
            CookieSpecProvider provider = (CookieSpecProvider) registry.lookup(policy);
            if (provider == null) {
                throw new HttpException("Unsupported cookie policy: " + policy);
            }
            Header header;
            CookieSpec cookieSpec = provider.create(clientContext);
            List<Cookie> cookies = new ArrayList(cookieStore.getCookies());
            List<Cookie> matchedCookies = new ArrayList();
            Date now = new Date();
            for (Cookie cookie : cookies) {
                if (cookie.isExpired(now)) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Cookie " + cookie + " expired");
                    }
                } else if (cookieSpec.match(cookie, cookieOrigin)) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Cookie " + cookie + " match " + cookieOrigin);
                    }
                    matchedCookies.add(cookie);
                }
            }
            if (!matchedCookies.isEmpty()) {
                for (Header header2 : cookieSpec.formatCookies(matchedCookies)) {
                    request.addHeader(header2);
                }
            }
            int ver = cookieSpec.getVersion();
            if (ver > 0) {
                boolean needVersionHeader = false;
                for (Cookie cookie2 : matchedCookies) {
                    if (ver != cookie2.getVersion() || !(cookie2 instanceof SetCookie2)) {
                        needVersionHeader = true;
                    }
                }
                if (needVersionHeader) {
                    header2 = cookieSpec.getVersionHeader();
                    if (header2 != null) {
                        request.addHeader(header2);
                    }
                }
            }
            context.setAttribute("http.cookie-spec", cookieSpec);
            context.setAttribute("http.cookie-origin", cookieOrigin);
        }
    }
}
