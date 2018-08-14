package cz.msebera.android.httpclient.client.params;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.auth.params.AuthPNames;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.config.RequestConfig.Builder;
import cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import cz.msebera.android.httpclient.params.CoreConnectionPNames;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;
import java.util.Collection;

@Deprecated
public final class HttpClientParamConfig {
    private HttpClientParamConfig() {
    }

    public static RequestConfig getRequestConfig(HttpParams params) {
        boolean z;
        Builder redirectsEnabled = RequestConfig.custom().setSocketTimeout(params.getIntParameter(CoreConnectionPNames.SO_TIMEOUT, 0)).setStaleConnectionCheckEnabled(params.getBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true)).setConnectTimeout(params.getIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 0)).setExpectContinueEnabled(params.getBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false)).setProxy((HttpHost) params.getParameter(ConnRoutePNames.DEFAULT_PROXY)).setLocalAddress((InetAddress) params.getParameter(ConnRoutePNames.LOCAL_ADDRESS)).setProxyPreferredAuthSchemes((Collection) params.getParameter(AuthPNames.PROXY_AUTH_PREF)).setTargetPreferredAuthSchemes((Collection) params.getParameter(AuthPNames.TARGET_AUTH_PREF)).setAuthenticationEnabled(params.getBooleanParameter(ClientPNames.HANDLE_AUTHENTICATION, true)).setCircularRedirectsAllowed(params.getBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false)).setConnectionRequestTimeout((int) params.getLongParameter("http.conn-manager.timeout", 0)).setCookieSpec((String) params.getParameter(ClientPNames.COOKIE_POLICY)).setMaxRedirects(params.getIntParameter(ClientPNames.MAX_REDIRECTS, 50)).setRedirectsEnabled(params.getBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true));
        if (params.getBooleanParameter(ClientPNames.REJECT_RELATIVE_REDIRECT, false)) {
            z = false;
        } else {
            z = true;
        }
        return redirectsEnabled.setRelativeRedirectsAllowed(z).build();
    }
}
