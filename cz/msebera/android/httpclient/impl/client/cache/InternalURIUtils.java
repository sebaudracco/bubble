package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.conn.routing.RouteInfo;
import java.net.URI;
import java.net.URISyntaxException;

@Immutable
class InternalURIUtils {
    public static URI rewriteURIForRoute(URI uri, RouteInfo route) throws URISyntaxException {
        if (uri == null) {
            return null;
        }
        if (route.getProxyHost() == null || route.isTunnelled()) {
            if (uri.isAbsolute()) {
                return URIUtils.rewriteURI(uri, null, true);
            }
            return URIUtils.rewriteURI(uri);
        } else if (uri.isAbsolute()) {
            return URIUtils.rewriteURI(uri);
        } else {
            return URIUtils.rewriteURI(uri, route.getTargetHost(), true);
        }
    }

    private InternalURIUtils() {
    }
}
